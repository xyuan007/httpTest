package com.test.xyuan.httpTest.processImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Helper.AssertHelper;
import com.test.xyuan.httpTest.Helper.ConfigHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Util.HttpClientUtil;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.casedata.ITestcaseData;
import com.test.xyuan.httpTest.casedata.impl.PostData;
import com.test.xyuan.httpTest.object.ConfigData;
import com.test.xyuan.httpTest.process.IExecute;

public class httppostProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public void execute(Element config,boolean bAssert) throws Exception{
		String response = null;
		ITestcaseData itd = null;
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + PublicDataHelper.getIns().getCasedata().getModelName());
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		itd = new PostData();
		UrlEncodedFormEntity body = (UrlEncodedFormEntity)itd.getCaseData(config);
		
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
		cd = null;
}
	
	private String httpExecute(String url, Map<String, String> headers,
			UrlEncodedFormEntity body) throws Exception {
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
			post.setEntity(body);
			
			//执行并返回结果
			loger.info("执行并返回结果，记录下调用时间和返回码");
			Date d1 = new Date();
			CloseableHttpResponse response = httpclient.execute(post);
			long time = new Date().getTime() - d1.getTime();
			
	        HttpEntity resEntity = response.getEntity();
	        res = EntityUtils.toString(resEntity, ProjectPropUtil.getCharSet());

			//记录请求
			PublicDataHelper.getIns().getCasedata().setRequestURL(url);
			PublicDataHelper.getIns().getCasedata().setRequestData(convertIS2String(body.getContent()));
			PublicDataHelper.getIns().getCasedata().setResponsecode(String.valueOf(response.getStatusLine().getStatusCode()));
			PublicDataHelper.getIns().getCasedata().setExectime(String.valueOf(time));
	        PublicDataHelper.getIns().getCasedata().setResponseData(res);
		}
		catch(Exception ex){}
		finally{
			post.releaseConnection();
		}
		
		return res;
	}
	
	private String convertIS2String(InputStream is){
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
      
        String line = null;    
        
        try {      
             while ((line = reader.readLine()) != null) {      
                  sb.append(line + "\n");      
              }      
          } catch (IOException e) {      
              e.printStackTrace();      
          } finally {      
            try {      
                 is.close();      
              } catch (IOException e) {      
                  e.printStackTrace();      
             }      
          }      
         return sb.toString();  
	}
}
