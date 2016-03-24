package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.process.HttpProcesser;
import com.creditease.xyuan.httpTest.process.SocketProcesser;

public class testngRun{
	private static MyLog  loger = MyLog.getLoger();
	@DataProvider(name = "test")
	public Object[][] getData() throws Exception {
		Element eleConfig = (new ConfigHelper()).getConfigElement();
		loger.info("取得用例配置");
		
		return new Object[][] {
			{ eleConfig},
		};
	}
	 
	@Test(dataProvider = "test")
	public void runTestCase(Element config) throws Exception {
		String protocol = config.elementText("protocol");
		
		//不同协议执行不同流程
		if(protocol.contains("http"))
			HttpProcesser.execute(config);
		else if(protocol.contains("socket"))
			SocketProcesser.execute(config);
	
	} 
}