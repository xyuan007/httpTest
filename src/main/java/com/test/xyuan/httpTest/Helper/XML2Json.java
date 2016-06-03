package com.test.xyuan.httpTest.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Util.MyLog;

//XML转为JSON
public class XML2Json {
	private static MyLog loger = MyLog.getLoger();
	
	private JSONArray getArray(String str){
		if(str.equals("null"))
			return null;
		
		if(str.length() == 0)
			return new JSONArray();
		
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
	
	public JSONObject getJSONObject(Element e) throws Exception{
		List<Element> sub = e.elements();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
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
						if(temp.getTextTrim().equals("null"))
							map.put(temp.getName(), null);
						else
							map.put(temp.getName(), temp.getTextTrim());
					}
					else if(type.equals("array")){
							map.put(temp.getName(),getArray(temp.getTextTrim()));
					}
				}
			}
		}catch(Exception ex){
			loger.error("元素转JSON时发生异常 ");
			ex.printStackTrace();
			throw new Exception("元素转JSON时发生异常 ");
		}
				
		return JSONObject.fromObject(map);
	}
	
}
