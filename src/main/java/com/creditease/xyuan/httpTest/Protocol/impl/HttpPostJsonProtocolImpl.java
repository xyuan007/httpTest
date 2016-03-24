package com.creditease.xyuan.httpTest.Protocol.impl;

import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.creditease.xyuan.httpTest.Protocol.IHttpProtocol;
import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.OutputUtil;
import com.creditease.xyuan.httpTest.Util.HttpClientUtil;

public class HttpPostJsonProtocolImpl  implements IHttpProtocol {
	private static MyLog loger = MyLog.getLoger();

	public String httpExecute(String url, Map<String, String> headers,
			Object body) throws Exception {
		String res = null;
		CloseableHttpClient httpclient = HttpClientUtil.getClient();
		HttpPost post = new HttpPost(url);
		
		try{
			//设置HEADER
			loger.info("设置HTTP的HEADER信息");
			post.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8");  
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	                 post.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}
			
			//设置ENTITY
			loger.info("设置HTTP的ENTITY");
			StringEntity entity = new StringEntity((String)body,"utf-8");  
			post.setEntity(entity);
			
			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(post);
			long time = new Date().getTime() - d1.getTime();
			
	        BizDataUtil.setHttpStatus(String.valueOf(response.getStatusLine().getStatusCode()));
	        BizDataUtil.setHttpInvokeTime(String.valueOf(time));
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, "utf-8");
		}
		catch(Exception ex){}
		finally{
			post.releaseConnection();
		}
		
		return res;
	}

}
