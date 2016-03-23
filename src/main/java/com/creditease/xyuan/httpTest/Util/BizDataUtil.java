package com.creditease.xyuan.httpTest.Util;

public class BizDataUtil {
	static private String modelName = null;
	static private String caseName = null;
	static private String outputField = null;
	static private String httpStatus = null;
	static private String httpInvokeTime = null;
	
	public static void init(){
		BizDataUtil.modelName = null;
		BizDataUtil.caseName = null;
		BizDataUtil.httpStatus = null;
		BizDataUtil.httpInvokeTime = null;
		BizDataUtil.outputField = null;
	}
	
	public static void init(String modelName,String caseName){
		BizDataUtil.modelName = modelName;
		BizDataUtil.caseName = caseName;
		BizDataUtil.httpStatus = null;
		BizDataUtil.httpInvokeTime = null;
		BizDataUtil.outputField = null;
	}
	
	public static void init(String modelName,String caseName,String outputField){
		BizDataUtil.modelName = modelName;
		BizDataUtil.caseName = caseName;
		BizDataUtil.outputField = outputField;
		BizDataUtil.httpStatus = null;
		BizDataUtil.httpInvokeTime = null;
		
	}
	
	
	public static String getOutputField() {
		return outputField;
	}

	public static void setOutputField(String outputField) {
		BizDataUtil.outputField = outputField;
	}

	public static String getModelName() {
		return modelName;
	}

	public static void setModelName(String modelName) {
		BizDataUtil.modelName = modelName;
	}

	public static String getCaseName() {
		return caseName;
	}

	public static void setCaseName(String caseName) {
		BizDataUtil.caseName = caseName;
	}

	public static String getHttpStatus() {
		return httpStatus;
	}

	public static void setHttpStatus(String httpStatus) {
		BizDataUtil.httpStatus = httpStatus;
	}

	public static String getHttpInvokeTime() {
		return httpInvokeTime;
	}

	public static void setHttpInvokeTime(String httpInvokeTime) {
		BizDataUtil.httpInvokeTime = httpInvokeTime;
	}

	
	
}
