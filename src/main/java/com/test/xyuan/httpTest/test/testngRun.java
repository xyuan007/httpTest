package com.test.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xyuan.httpTest.Helper.ConfigHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Util.MyLog;

public class testngRun{
	private static MyLog  loger = MyLog.getLoger();
	@DataProvider(name = "test")
	public Object[][] getTestData() throws Exception {
		Element eleConfig = (new ConfigHelper()).getConfigElement();
		PublicDataHelper.getIns().getCasedata().setApitype(eleConfig.element("protocol").getTextTrim());
		loger.info("取得用例配置");
		
		return new Object[][] {
			{ eleConfig},
		};
	}
	 
	@Test(dataProvider = "test")
	public void runTestCase(Element config) throws Exception {
//		String protocol = config.elementText("protocol");
		loger.info("协议：" + config.elementText("protocol"));
		boolean bAssert = Reporter.getCurrentTestResult().getTestContext().getName().equals("true") == true? true:false;

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