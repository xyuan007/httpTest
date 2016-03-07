package com.creditease.xyuan.httpTest.Util;

import org.dom4j.Element;

public class TestFileUtil {
	public static Element getSingleTests(String projectName) throws Exception{
		Element root = XMLUtil.getRootElement("AllTests.xml");
		return (Element) root.selectSingleNode("/AllTests/SingleTest");
	}
	
	public static Element getSequenceTests(String projectName) throws Exception{
		Element root = XMLUtil.getRootElement("AllTests.xml");
		return (Element) root.selectSingleNode("/AllTests/SequenceTests");
	}
	
	public static Element getAppointedSingleTests(String projectName) throws Exception{
		Element root = XMLUtil.getRootElement("AppointedTest.xml");
		return (Element) root.selectSingleNode("/AllTests/SingleTest");
	}
	
	public static Element getAppointedSequenceTests(String projectName) throws Exception{
		Element root = XMLUtil.getRootElement("AppointedTest.xml");
		return (Element) root.selectSingleNode("/AllTests/SequenceTest");
	} 
	
	
	
}