package com.creditease.xyuan.httpTest.Util;

public class ProtocolUtil {
	private static String httpjson = "com.creditease.xyuan.httpTest.Protocol.impl.HttpPostJsonProtocolImpl";
	private static String httpget = "com.creditease.xyuan.httpTest.Protocol.impl.HttpGetProtocolImpl";
	private static String httppost = "com.creditease.xyuan.httpTest.Protocol.impl.HttpPostProtocolImpl";

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
