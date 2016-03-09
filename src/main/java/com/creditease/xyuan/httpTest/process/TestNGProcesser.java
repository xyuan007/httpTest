package com.creditease.xyuan.httpTest.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.Util.TestFileUtil;

public class TestNGProcesser {
	public static void main(String[] args) throws Exception{
		TestNGProcesser tp = new TestNGProcesser();
		tp.test();
	}
	
	public void test() throws Exception{
		Element eleSingle = null;
		Element eleSequence = null;
		String modelName = null;
		
		//取得运行模式
		String runMode = PropUtil.getProp("RunMode");
		String projectName = PropUtil.getProjectName();
		
		//根据运行模式，到相应的配置文件 中取得测试用例
		if(runMode.equals("1")){
			eleSingle = TestFileUtil.getSingleTests(projectName);
			eleSequence = TestFileUtil.getSequenceTests(projectName);
		}
		
		//SingleTest的用例执行
		for(int i=0;i<eleSingle.elements().size();i++){
			Element eleCur = (Element) eleSingle.elements().get(i);
			modelName  = eleCur.attributeValue("model");
			String[] tests = eleCur.getTextTrim().split(",");
			
			for(int j=0;j<tests.length;j++){
				BizDataUtil.init(modelName,tests[j]);
				//执行TESTNG CASE
				runTestNG();
				
			}
		}
		
		//SequenceTest的用例执行
		for(int i=0;i<eleSequence.elements().size();i++){
			Element eleCur = (Element) eleSingle.elements().get(i);
			String seqName = eleCur.attributeValue("name");
			for(int j=0;j<eleCur.elements().size();j++){
				Element eleStep = (Element)eleCur.selectSingleNode(String.format("/SequenceTest/step[@index=\"%d\"]",j+1));
				modelName  = eleStep.attributeValue("model");
				String caseName = eleStep.getTextTrim();
				
				BizDataUtil.init(modelName,caseName);
				//执行TESTNG CASE
				runTestNG();
			}
		}
		
	}
	
	private void runTestNG(){
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		 
		XmlTest test = new XmlTest(suite);
		test.setName("TmpTest");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.creditease.xyuan.httpTest.test.testngRun"));
		test.setXmlClasses(classes) ;
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
////		TestListenerAdapter tla = new TestListenerAdapter();
////		testng.setTestClasses(new Class[] { testngRun.class });
////		testng.addListener(tla);
		testng.run(); 
	}
	
}
