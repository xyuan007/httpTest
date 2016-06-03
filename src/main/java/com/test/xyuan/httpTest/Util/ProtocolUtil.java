package com.test.xyuan.httpTest.Util;

public class ProtocolUtil {
	private static String httpjson = "com.test.xyuan.httpTest.Protocol.impl.HttpPostJsonProtocolImpl";
	private static String httpget = "com.test.xyuan.httpTest.Protocol.impl.HttpGetProtocolImpl";
	private static String httppost = "com.test.xyuan.httpTest.Protocol.impl.HttpPostProtocolImpl";

	public static String getHttpjson() {
		return httpjson;
	}
	public static String getHttpget() {
		return httpget;
	}
	public static String getHttppost() {
		return httppost;
	}

}
