package com.creditease.xyuan.httpTest.Protocol.impl;

import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import com.creditease.xyuan.httpTest.Protocol.IHttpProtocol;
import com.creditease.xyuan.httpTest.Util.HttpClientUtil;

public class HttpPostProtocolImpl  implements IHttpProtocol {

	public String httpExecute(String url, Map<String, String> headers,Object body) throws Exception {
		return null;
	}

}