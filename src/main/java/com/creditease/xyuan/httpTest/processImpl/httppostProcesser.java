package com.creditease.xyuan.httpTest.processImpl;

import java.util.Date;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Helper.AssertHelper;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.Util.HttpClientUtil;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.object.ConfigData;
import com.creditease.xyuan.httpTest.process.IExecute;

public class httppostProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public void execute(Element config,boolean bAssert) throws Exception{
		String response = null;
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + PublicDataHelper.getIns().getCasedata().getModelName());
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		loger.info("取得业务数据:" + PublicDataHelper.getIns().getCasedata().getCaseName());
		DataHelper dh = new DataHelper();
		String body = dh.getJsonBody();
		
		//执行
		loger.info("执行HTTP请求");
		response = httpExecute(cd.getUrl(), cd.getHeaders(), body);
		
		System.out.println(body);
		System.out.println(response);
		//验证
		loger.info("结果验证");
		if(bAssert == true)
			AssertHelper.asserting(response);
		
		//清理数据
		dh = null;
		cd = null;
	}
	
	private String httpExecute(String url, Map<String, String> headers,
			Object body) throws Exception {
		String res = null;
		CloseableHttpClient httpclient = HttpClientUtil.getClient();
		HttpPost post = new HttpPost(url);
		
		try{
			//设置HEADER
			loger.info("设置HTTP的HEADER信息");
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	                 post.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}
			
			//设置ENTITY
			loger.info("设置HTTP的ENTITY");
			StringEntity entity = new StringEntity((String)body,PropUtil.getCharSet());  
			post.setEntity(entity);
			
			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(post);
			long time = new Date().getTime() - d1.getTime();
			
			PublicDataHelper.getIns().getCasedata().setResponsecode(String.valueOf(response.getStatusLine().getStatusCode()));
			PublicDataHelper.getIns().getCasedata().setExectime(String.valueOf(time));
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, PropUtil.getCharSet());
		}
		catch(Exception ex){}
		finally{
			post.releaseConnection();
		}
		
		return res;
	}
}
