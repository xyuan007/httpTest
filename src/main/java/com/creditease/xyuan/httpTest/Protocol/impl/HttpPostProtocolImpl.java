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
import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.HttpClientUtil;
import com.creditease.xyuan.httpTest.object.BizData;

public class HttpPostProtocolImpl  implements IHttpProtocol {

	public String httpExecute(Map<String, String> headers,Map<String, String> param,String body){
		String res = null;
		CloseableHttpClient httpclient = HttpClientUtil.getClient();
		BizData bd = BizDataUtil.getBizData();
		HttpPost post = new HttpPost();
		
		try{
			post.setHeader(HTTP.CONTENT_TYPE, "application/json");  
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	                 post.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}
			StringEntity entity = new StringEntity(body,"utf-8");  
			post.setEntity(entity);
			
			CloseableHttpResponse response = httpclient.execute(post);
	        System.out.println(response.toString());
	          
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, "utf-8");
	        System.out.println(res);
		}
		catch(Exception ex){}
		finally{
			post.releaseConnection();
		}
		
		return res;
	}

}