package com.creditease.xyuan.httpTest.Util;

public class BizDataUtil {
	static private String modelName = null;
	static private String caseName = null;
	static private String httpStatus = null;
	static private String httpInvokeTime = null;
	
	public static void init(){
		BizDataUtil.modelName = null;
		BizDataUtil.caseName = null;
		BizDataUtil.httpStatus = null;
		BizDataUtil.httpInvokeTime = null;
	}
	
	public static void init(String modelName,String caseName){
		BizDataUtil.modelName = modelName;
		BizDataUtil.caseName = caseName;
		BizDataUtil.httpStatus = null;
		BizDataUtil.httpInvokeTime = null;
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
