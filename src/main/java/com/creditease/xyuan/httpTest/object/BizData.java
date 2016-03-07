package com.creditease.xyuan.httpTest.object;

import java.util.HashMap;
import java.util.Map;

public class BizData {
	private String modelName = null;
	private String caseName = null;
	private Map<String,String> output = null;
	
	public BizData(String modelName,String caseName){
		this.modelName = modelName;
		this.caseName = caseName;
	}
	
	public BizData(String modelName,String caseName,String output){
		this.modelName = modelName;
		this.caseName = caseName;
		String[] res = output.split(",");
		this.output = new HashMap<String,String>();
		
		for(int i=0;i<res.length;i++){
			this.output.put(res[i], null);
		}
	}
	
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Map<String, String> getOutput() {
		return this.output;
	}

	public void setOutput(Map<String, String> output) {
		this.output = output;
	}
}
