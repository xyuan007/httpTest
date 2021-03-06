package com.test.xyuan.httpTest.Util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientUtil {
	private static CloseableHttpClient httpclient = null;
	
	public static CloseableHttpClient getClient(){
		if(httpclient == null){		
			if(ProjectPropUtil.getHttpProxy().equals("true")){
				HttpHost proxy = new HttpHost(ProjectPropUtil.getProxyIP(), ProjectPropUtil.getProxyPort());  
				httpclient = HttpClientBuilder.create().setProxy(proxy).build();
			}
			else
				httpclient = HttpClientBuilder.create().build();
		}
		return httpclient;
	}
	
	public static void closeClient() throws IOException{
		if(httpclient != null){
			httpclient.close();
		}
	}
}
