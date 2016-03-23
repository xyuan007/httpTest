package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.process.HttpProcesser;
import com.creditease.xyuan.httpTest.process.SocketProcesser;


public class testngRun{
	@DataProvider(name = "test")
	public Object[][] getData() throws Exception {
		Element eleConfig = (new ConfigHelper()).getConfigElement();
		
		return new Object[][] {
			{ eleConfig},
		};
	}
	 
	@Test(dataProvider = "test")
	public void verifyData1(Element config) throws Exception {
		String protocol = config.elementText("protocol");
		if(protocol.contains("http"))
			HttpProcesser.execute(config);
		else if(protocol.contains("socket"))
			SocketProcesser.execute(config);
	
	} 
}