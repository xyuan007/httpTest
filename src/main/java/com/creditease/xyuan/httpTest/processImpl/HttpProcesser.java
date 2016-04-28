package com.creditease.xyuan.httpTest.processImpl;

import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Helper.AssertHelper;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.Protocol.IHttpProtocol;
import com.creditease.xyuan.httpTest.Protocol.impl.HttpPostJsonProtocolImpl;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.object.ConfigData;
import com.creditease.xyuan.httpTest.process.IExecute;

public class HttpProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	
	public void execute(Element config) throws Exception{
		String response = null;
		IHttpProtocol http = null;
		
		loger.info("开始执行HTTP处理流程");
		//配置数据
		loger.info("取得配置数据数据" + PublicDataHelper.getInstance().getCasedata().getModelName());
		ConfigData cd = ConfigHelper.getConfigData(config);
		
		//业务数据
		loger.info("取得业务数据:" + PublicDataHelper.getInstance().getCasedata().getCaseName());
		DataHelper dh = new DataHelper();
		String body = dh.getJsonBody();
		
		//执行类
		if(cd.getProtocol().equals("httpjson")){
			http = new HttpPostJsonProtocolImpl();
		}
		
		//执行
		loger.info("执行HTTP请求");
		response = http.httpExecute(cd.getUrl(), cd.getHeaders(), body);
		
		System.out.println(body);
		System.out.println(response);
		//验证
		loger.info("结果验证");
		AssertHelper.asserting(response);
		
		//清理数据
		dh = null;
		cd = null;
	}
}
