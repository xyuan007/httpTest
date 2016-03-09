package com.creditease.xyuan.httpTest.Util;

import java.util.HashMap;
import java.util.Map;

public class OutputUtil {
	private static Map<String,Object> output = new HashMap<String, Object>();

	public static Map<String, Object> getOutput() {
		return output;
	}

	public static void setValue(String key,Object value) {
		if( output.get(key) != null)
			output.remove(key);
		output.put(key, value);
	}
	
	public static Object  getValue(String key){
		return output.get(key);
	}
	
}
