package com.creditease.xyuan.httpTest.Helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Util.*;
import com.creditease.xyuan.httpTest.object.BizData;

public class DataHelper {
	String projectName = null;
	Element ele = null;
	
	public DataHelper() throws Exception{
		this.projectName = PropUtil.getProjectName();
		String configFile = String.format("data\\%s\\%s.xml", this.projectName,BizDataUtil.getBizData().getModelName());
		Element root = MyXMLUtil.getRootElement(configFile);
		
		ele = (Element)root.selectSingleNode(String.format("/TestSuite/TestCase[@name=\"%s\"]",BizDataUtil.getBizData().getCaseName()));
		
		processElement(ele);
	}
	
	//将原始XML数据处理成为可以转换成JSON的XML数据（1、处理动态数据，2，写入INPUT数据）
	private void processElement(Element element) throws Exception{
		List<Element> list = element.elements();
		
		for(int i=0;i<list.size();i++){
			Element temp = list.get(i);
			
			if(temp.elements().size() > 0)
				processElement(temp);
			
			String param = temp.attributeValue("param");
			String result = temp.attributeValue("result");
			String text = temp.getTextTrim();
			
			if(param != null){
				if(param.equals("input")){
					Map<String,String> map = BizDataUtil.getBizData().getOutput();
					Element ele = element.addElement(temp.getName());
					ele.addText(map.get(text));
					if(result.equals("number")){
						ele.addAttribute("type", "number");
					}
					element.remove(temp);
				}
				else if (param.equals("func")){
					Class clazz = Class.forName(temp.attributeValue("class"));
					Method method = clazz.getMethod(text, null);  
					Object obj = method.invoke(null);
					
					Element ele = element.addElement(temp.getName());
					ele.addText((String)obj);
					if(result.equals("number")){
						ele.addAttribute("type", "number");
					}
					element.remove(temp);
				}
			}
		}
	}
	
	public Element getDataElement(){
		return this.ele;
	}
	
	public String getJsonBody(){
		XML2Json xj = new XML2Json();
		return xj.getJSONObject(this.ele).toString();
	}
	
	public Map<String,String> getPostParam(){
		return null;
	}
	
	public String getHttpGetString(){
		return null;
	}
	
	public static void main(String[] args) throws Exception{
		DataHelper dh = new DataHelper();
		
	}
}
