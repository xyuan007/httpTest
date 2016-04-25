package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.process.ExecuteFactory;
import com.creditease.xyuan.httpTest.processImpl.HttpProcesser;
import com.creditease.xyuan.httpTest.processImpl.SocketProcesser;

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
		loger.info("协议：" + protocol);
		
		try{
			//不同协议执行不同流程
			ExecuteFactory.createExecute(protocol, config);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	} 
}