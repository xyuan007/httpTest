package com.test.xyuan.httpTest.Helper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Util.*;

public class DataHelper {
	private static MyLog logger = MyLog.getLoger();
	String projectName = null;
	Element ele = null;
	
	public DataHelper()  {
		this.projectName = PropUtil.getProjectName();
		String configFile = null;
		Element root = null;
		try{
			configFile = String.format("data\\%s\\%s.xml", this.projectName,PublicDataHelper.getIns().getCasedata().getModelName());
			root = MyXMLUtil.getRootElement(configFile);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			logger.info("路径：" + configFile + "下未找到数据文件，返回空数据");
			ele = null;}

		try{
			ele = (Element)root.selectSingleNode(String.format("/TestSuite/TestCase[@name=\"%s\"]",PublicDataHelper.getIns().getCasedata().getCaseName()));
		}catch(Exception ex){
			logger.error(ex.getMessage());
			logger.info("查找数据结点时出错，返回空数据");
			ele = null;}


		
//		if(ele == null){
//			logger.error("数据结点未进行配置，用例名：" + PublicDataHelper.getIns().getCasedata().getCaseName());
//			throw new Exception("数据结点未进行配置，用例名：" + PublicDataHelper.getIns().getCasedata().getCaseName());
//		}
//		processElement(ele);
	}
	
	public String getDataByField(String field){
		Element eleField = ele.element(field);
		
		if(eleField != null)
			return eleField.getTextTrim();
		else
			return null;
	}
	
	//将原始XML数据处理成为可以转换成JSON的XML数据（1、处理动态数据，2，写入INPUT数据）
	private void processElement(Element element) throws Exception{
		List<Element> list = element.elements();
		
		try{
			for(int i=0;i<list.size();i++){
				Element temp = list.get(i);
				
				if(temp.elements().size() > 0)
					processElement(temp);
				
				String param = temp.attributeValue("param");
				String result = temp.attributeValue("result");
				String text = temp.getTextTrim();
				
				if(param != null){
					if(param.equals("input")){
						logger.info("处理INPUT参数，从OUTPUT中得到数据");
						Map<String,Object> map = PublicDataHelper.getIns().getOutput().getOutput();
						Element newEle = element.addElement(temp.getName());
						Object obj = map.get(text);
						if(obj == null)
							newEle.addText("");
						else
							newEle.addText(obj.toString());
						
						if(result != null && result.equals("number")){
							newEle.addAttribute("type", "number");
						}
					}
					else if (param.equals("func")){
						logger.info("处理FUNC参数，动态生成数据");
						Class clazz = Class.forName(PropUtil.getFuncClass());
						String input = temp.attributeValue("input");
						Method method = null;
						Object obj = null;
						if(input!=null){
							method = clazz.getMethod(text, String.class);
							obj = method.invoke(null,input);
						}
						else{
							method = clazz.getMethod(text, null);
							obj = method.invoke(null);
						}
						
						Element ele = element.addElement(temp.getName());
						ele.addText(String.valueOf(obj));
						
						if(result != null && result.equals("number")){
							ele.addAttribute("type", "number");
						}
					}
					
					element.remove(temp);
				}
			}
		}
		catch(Exception e){
			logger.error("处理数据结点时失败：" + e.getMessage());
			e.printStackTrace();
			throw new Exception("处理数据结点时失败：" + e.getMessage());
		}
	}
	
	public Element getDataElement(){
		return this.ele;
	}
	
	public String getJsonBody() throws Exception{
		XML2Json xj = new XML2Json();
		return xj.getJSONObject(this.ele).toString();
	}
	
	public Map<String,String> getPostParam(){
		return null;
	}
	
	public String getHttpGetString(){
		return null;
	}
	
}
