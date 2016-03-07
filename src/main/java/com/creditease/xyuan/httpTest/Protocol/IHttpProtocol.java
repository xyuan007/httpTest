package com.creditease.xyuan.httpTest.Protocol;

import java.io.IOException;
import java.util.Map;

public interface IHttpProtocol {
	public String httpExecute(Map<String,String> header,Map<String,String> param,String body) throws Exception;
}
