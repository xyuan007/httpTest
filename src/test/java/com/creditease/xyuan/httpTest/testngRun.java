package com.creditease.xyuan.httpTest;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;


public class testngRun{
	@DataProvider(name = "test")
	public Object[][] getData() throws Exception {
		Element eleConfig = (new ConfigHelper()).getConfigElement();
		Element eleData = (new DataHelper()).getDataElement();
		
		return new Object[][] {
			{ eleConfig, eleData },
		};
	}
	 
	//named "test1"
	@Test(dataProvider = "test")
	public void verifyData1(Element config, Element data) {
		
	
	} 
}