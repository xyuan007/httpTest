package com.creditease.xyuan.httpTest.process;

import org.dom4j.Element;

import com.creditease.xyuan.httpTest.Helper.AssertHelper;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Protocol.IHttpProtocol;
import com.creditease.xyuan.httpTest.Protocol.impl.HttpPostJsonProtocolImpl;
import com.creditease.xyuan.httpTest.object.ConfigData;

public class HttpProcesser {
	
	public static void execute(Element config) throws Exception{
		String response = null;
		IHttpProtocol http = null;
		
		//配置数据
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		DataHelper dh = new DataHelper();
		String body = dh.getJsonBody();
		
		//执行类
		if(cd.getProtocol().equals("httpjson")){
			http = new HttpPostJsonProtocolImpl();
		}
		
		//执行
		response = http.httpExecute(cd.getUrl(), cd.getHeaders(), body);
		
		System.out.println(body);
		System.out.println(response);
		//验证
		AssertHelper.asserting(response);
		

		//验证通过，获取OUTPUT
		
		
		
		//清理数据
		dh = null;
		cd = null;
	}
}
