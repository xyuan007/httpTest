package com.test.xyuan.httpTest.Helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.MyXMLUtil;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.Util.SystemPropUtil;
import com.test.xyuan.httpTest.object.ConfigData;

public class ConfigHelper {
	private static MyLog loger = MyLog.getLoger();
	Element ele = null;
	
	public ConfigHelper() throws Exception {
		String configFile = String.format("config\\%s.xml", ProjectPropUtil.getProjectName());
		Element root = MyXMLUtil.getRootElement(configFile);
		
		ele = (Element)root.selectSingleNode(String.format("/Config/model[@name=\"%s\"]",PublicDataHelper.getIns().getCasedata().getModelName()));
		if(ele == null){
			String logInfo = "config文件中，模块：" + PublicDataHelper.getIns().getCasedata().getModelName() + "未进行配置";
			loger.error(logInfo);
			throw new Exception(logInfo);
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
	
	public String getCycle(){
		return ele.elementText("cycle");
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
			
//			Element eleParam = ele.element("params");
//			Map<String,String> param = null;
//			if(eleParam!=null){
//				param = new HashMap<String,String>();
//				for(int i=0;i<eleParam.elements().size();i++){
//					Element temp = (Element) eleHeader.elements().get(i);
//					param.put(temp.getName(), temp.getTextTrim());
//				}
//			}
			
			cd.setHeaders(header);
//			cd.setParams(param);
		}
		catch(Exception e){
			String logInfo = "获得模块" + PublicDataHelper.getIns().getCasedata().getModelName() + "的配置数据时出错：" + e.getMessage();
			loger.error(logInfo);
			throw new Exception(logInfo);
		}
		return cd;
	}
	
	public static void main(String[] args) throws Exception{
		ConfigHelper ch = new ConfigHelper();
		System.out.println(ch.getProtocol());
	}
}
