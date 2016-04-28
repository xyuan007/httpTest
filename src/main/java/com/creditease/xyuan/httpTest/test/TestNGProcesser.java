package com.creditease.xyuan.httpTest.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.creditease.xyuan.httpTest.Helper.DatabaseHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.POJO.DetailReports;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.Util.TestFileUtil;
import com.sun.xml.internal.bind.v2.runtime.property.Property;
import com.sun.xml.internal.fastinfoset.sax.Properties;

public class TestNGProcesser {
	private static MyLog loger = MyLog.getLoger();
	private static PublicDataHelper pdh = PublicDataHelper.getInstance();
	private static int round;
	
	public static void main(String[] args) throws Exception{
		TestNGProcesser pro = new TestNGProcesser();
//		pro.test();
//		
		ArrayList  res = new ArrayList();
		while(true){
			res.add(new DetailReports());
		}
	}
	
	public void test() throws Exception{
		Element eleSequence = null;
		
		//轮次，公共数据初始
		round = DatabaseHelper.getMaxRound() + 1;
		DatabaseHelper.newRunReports(round);
		pdh.initRoundData(round);
		
		//取得运行模式
		loger.info("取得runmode");
		List<String> files = TestFileUtil.getTestFile();
		
		loger.info("取得待运行的测试用例");
		for(String file: files){
			eleSequence = TestFileUtil.getSequenceTests(file);
			
			runSequenceTests(eleSequence);
		}
		
		int apitotal = PublicDataHelper.getInstance().getRound().getApitotal();
		int success = PublicDataHelper.getInstance().getRound().getSuccess();
		int fail = PublicDataHelper.getInstance().getRound().getFail();
		int notrun = PublicDataHelper.getInstance().getRound().getNotrun();
		DatabaseHelper.updateRunReports(round, apitotal, success, fail, notrun);
	}
	
	//运行有顺序的用例
	private void runSequenceTests(Element eleSequence){
		String modelName = null;
		
		loger.info("运行顺序执行的用例");
		try{
		//	SequenceTest的用例执行
		if(eleSequence != null){
			for(int i=0;i<eleSequence.elements().size();i++){
				Element eleCur = (Element) eleSequence.elements().get(i);
				String seqName = eleCur.attributeValue("name");
				for(int j=0;j<eleCur.elements().size();j++){
					Element eleStep = (Element)eleCur.selectSingleNode(String.format("/AllTests/SequenceTests/SequenceTest[@name=\"%s\"]/step[@stepid=\"%d\"]",seqName,j+1));
					
					if(eleStep != null){
						modelName  = eleStep.attributeValue("model");
						String caseName = eleStep.getTextTrim();
						String stepid = eleStep.attributeValue("stepid");
						
						pdh.initCaseData(modelName, caseName, null, round,seqName,stepid);
						//执行TESTNG CASE
						loger.info("TESTNG动态执行用例。模块名：" + modelName + ",用例名称：" + caseName);
						runTestNG();
					}
				}
			}
		}
		}catch(Exception ex){loger.error("运行顺序执行的用例时出错"+ex.getMessage());}
	}
	
	private void runTestNG(){
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		 
		XmlTest test = new XmlTest(suite);
		test.setName("TmpTest");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass(PropUtil.getRunClass()));
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
