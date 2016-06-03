package com.test.xyuan.httpTest.dm;

import java.util.HashMap;
import java.util.Map;

public class OutputData {
	private Map<String,Object> output = new HashMap<String, Object>();
	
	public Map<String, Object> getOutput() {
		return this.output;
	}
	
	public void setValue(String key,Object value) {
		if(output.containsKey(key))
			output.remove(key);
		output.put(key, value);
	}
	
	public Object  getValue(String key){
		return output.get(key);
	}
	
}
