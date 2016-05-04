package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.Util.MyLog;

public class testngRun{
	private static MyLog  loger = MyLog.getLoger();
	@DataProvider(name = "test")
	public Object[][] getTestData() throws Exception {
		boolean bAssert = Reporter.getCurrentTestResult().getTestContext().getName().equals("true") == true? true:false;
		Element eleConfig = (new ConfigHelper()).getConfigElement();
		PublicDataHelper.getIns().getCasedata().setApitype( eleConfig.element("protocol").getTextTrim());
		loger.info("取得用例配置");
		
		return new Object[][] {
			{ eleConfig,bAssert},
		};
	}
	 
	@Test(dataProvider = "test")
	public void runTestCase(Element config,boolean bAssert) throws Exception {
//		String protocol = config.elementText("protocol");
		loger.info("协议：" + config.elementText("protocol"));
		
		try{
			//不同协议执行不同流程
			ExecuteFactory.createExecute(config,bAssert);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	} 
}