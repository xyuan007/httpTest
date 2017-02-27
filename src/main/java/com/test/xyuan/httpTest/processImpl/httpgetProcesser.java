package com.test.xyuan.httpTest.processImpl;

import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Helper.AssertHelper;
import com.test.xyuan.httpTest.Helper.ConfigHelper;
import com.test.xyuan.httpTest.Helper.DataHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Util.HttpClientUtil;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.Util.SystemPropUtil;
import com.test.xyuan.httpTest.casedata.ITestcaseData;
import com.test.xyuan.httpTest.casedata.impl.GetData;
import com.test.xyuan.httpTest.object.ConfigData;
import com.test.xyuan.httpTest.process.IExecute;

public class httpgetProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public void execute(Element config,boolean bAssert) throws Exception{
		String response = null;
		ITestcaseData itd = null;
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + PublicDataHelper.getIns().getCasedata().getModelName());
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		loger.info("取得业务数据:" + PublicDataHelper.getIns().getCasedata().getCaseName());
		itd = new GetData();
		String body = (String)itd.getCaseData(config);
		
		String url = cd.getUrl();
		if(body != null && body.length() > 0)
			url = url + body;
		
		//执行
		loger.info("执行HTTP请求");
		response = httpExecute(url, cd.getHeaders());

		System.out.println(response);
		//验证
		loger.info("结果验证");
		if(bAssert == true)
			AssertHelper.asserting(response);
		
		//清理数据
		cd = null;
	}
	
	private String httpExecute(String url, Map<String, String> headers) throws Exception {
		String res = null;
		CloseableHttpClient httpclient = HttpClientUtil.getClient();
		HttpGet request = new HttpGet(urlConvert(url));

		try{
			//设置HEADER
			loger.info("设置HTTP的HEADER信息");
			if (headers != null) {  
	             for (Map.Entry<String,String> entry : headers.entrySet()) { 
	            	 request.addHeader(entry.getKey(), entry.getValue());  
	             }  
	      	}

			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(request);
			long time = new Date().getTime() - d1.getTime();
			
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, ProjectPropUtil.getCharSet());
	        
			//记录请求
			PublicDataHelper.getIns().getCasedata().setRequestURL(url);
			PublicDataHelper.getIns().getCasedata().setResponsecode(String.valueOf(response.getStatusLine().getStatusCode()));
			PublicDataHelper.getIns().getCasedata().setExectime(String.valueOf(time));
			PublicDataHelper.getIns().getCasedata().setResponseData(res);
		}
		catch(Exception ex){
			throw new Exception(ex);
		}
		finally{
			request.releaseConnection();
		}
		
		return res;
	}
	
	private String urlConvert(String url){
		url = url.replaceAll("\\{", "%7b");
		url = url.replaceAll("\\}", "%7d");
		url = url.replaceAll("\\\"", "%22");
		return url;
	}
	
}
