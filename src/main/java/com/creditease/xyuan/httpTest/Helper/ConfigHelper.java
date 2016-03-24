package com.creditease.xyuan.httpTest.Helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Element;
import org.testng.log4testng.Logger;

import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.MyXMLUtil;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.object.ConfigData;

public class ConfigHelper {
	private static MyLog loger = MyLog.getLoger();
	String projectName = null;
	Element ele = null;
	
	public ConfigHelper() throws Exception {
		try{
			this.projectName = PropUtil.getProjectName();
			String configFile = String.format("config\\%s.xml", this.projectName);
			Element root = MyXMLUtil.getRootElement(configFile);
			
			ele = (Element)root.selectSingleNode(String.format("/Config/model[@name=\"%s\"]",BizDataUtil.getModelName()));
			if(ele == null){
				loger.error("模块：" + BizDataUtil.getModelName() + "未进行配置");
				throw new Exception("模块：" + BizDataUtil.getModelName() + "未进行配置");
			}
		}catch(Exception e){loger.error("处理配置数据时失败：" + e.getMessage());
			throw new Exception("处理配置数据时失败：" + e.getMessage());
		}
	}
	
	public Element getConfigElement(){
		return this.ele;
	}
	
	public  String getUrl(){
		return ele.elementText("url");
	}
	
	public String getProtocol(){
		return ele.elementText("protocol");
	}
	
	//取得配置数据
	public static ConfigData getConfigData(Element ele) throws Exception {
		ConfigData cd = new ConfigData();
		cd.setProtocol(ele.elementText("protocol"));
		cd.setUrl(ele.elementText("url"));
		
		try{
			Element eleHeader = ele.element("headers");
			Map<String,String> header = null;
			if(eleHeader!=null){
				header = new HashMap<String,String>();
				for(int i=0;i<eleHeader.elements().size();i++){
					Element temp = (Element) eleHeader.elements().get(i);
					if(temp.attributeValue("func") == null)
						header.put(temp.getName(), temp.getTextTrim());
					else{
						Class clazz = Class.forName(temp.attributeValue("func"));
						Method method = clazz.getMethod(temp.getTextTrim(), null);  
						Object obj = method.invoke(null);
						
						header.put(temp.getName(), (String)obj);
					}
				}
			}
			
			Element eleParam = ele.element("params");
			Map<String,String> param = null;
			if(eleParam!=null){
				param = new HashMap<String,String>();
				for(int i=0;i<eleParam.elements().size();i++){
					Element temp = (Element) eleHeader.elements().get(i);
					param.put(temp.getName(), temp.getTextTrim());
				}
			}
			
			cd.setHeaders(header);
			cd.setParams(param);
		}
		catch(Exception e){
			loger.error("获得模块" + BizDataUtil.getModelName() + "的配置数据时出错：" + e.getMessage());
			throw new Exception("获得模块" + BizDataUtil.getModelName() + "的配置数据时出错：" + e.getMessage());
		}
		return cd;
	}
	
	public static void main(String[] args) throws Exception{
		ConfigHelper ch = new ConfigHelper();
		System.out.println(ch.getProtocol());
	}
}
