package com.creditease.xyuan.httpTest.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Util.MyXMLUtil;

public class XML2Json {
	
	private JSONArray getArray(String str){
		JSONArray json = null;
		String[] array = str.split(",");
		
		json = new JSONArray();
		for(int i=0;i<array.length;i++){
			json.add(i,array[i]);
		}
		return json;
	}
	
	private String getType(Element temp){
		String type = temp.attributeValue("type");
		if(type == null)
			type = "string";
		
		return type;
	}
	
	public JSONObject getJSONObject(Element e){
		List<Element> sub = e.elements();
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(int i=0;i<sub.size();i++){
			Element temp = sub.get(i);
			String  type = getType(temp);
			
			if(temp.elements().size() > 0){
				JSONObject body = getJSONObject(temp);
				map.put(temp.getName(), body);
			}
			else{
				if(type.equals("number")){
					int num = Integer.parseInt(temp.getTextTrim());
					map.put(temp.getName(), num);
				}
				else if(type.equals("string")){
					map.put(temp.getName(), temp.getTextTrim());
				}
				else if(type.equals("array")){
					map.put(temp.getName(),getArray(temp.getTextTrim()));
				}
			}
		}
				
		return JSONObject.fromObject(map);
	}
	
	public static void main(String[] args) throws Exception{
		Element root = MyXMLUtil.getRootElement("E:\\eclipse\\workspace\\httpTest\\data\\push\\queryAppList.xml");
		Element ele = (Element) root.selectSingleNode("/TestSuite/TestCase");
		XML2Json x = new XML2Json();
		
		System.out.println(x.getJSONObject(ele).toString());
	}
}
