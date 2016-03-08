package com.creditease.xyuan.httpTest.Protocol;

import java.util.Map;

public interface IHttpProtocol {
	public String httpExecute(String url,Map<String,String> headers,Object body) throws Exception;
}
