package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.process.HttpProcesser;


public class testngRun{
	@DataProvider(name = "test")
	public Object[][] getData() throws Exception {
		Element eleConfig = (new ConfigHelper()).getConfigElement();
//		Element eleData = (new DataHelper()).getDataElement();
		
		return new Object[][] {
			{ eleConfig},
		};
	}
	 
	//named "test1"
	@Test(dataProvider = "test")
	public void verifyData1(Element config) throws Exception {
		
		HttpProcesser.execute(config);
	
	} 
}