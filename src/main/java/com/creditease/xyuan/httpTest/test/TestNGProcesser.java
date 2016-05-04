package com.creditease.xyuan.httpTest.test;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.creditease.xyuan.httpTest.Helper.DatabaseHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.Listener.TestListener;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.Util.TestFileUtil;

public class TestNGProcesser {
	private static MyLog loger = MyLog.getLoger();
	private static PublicDataHelper pdh = PublicDataHelper.getIns();
	private static int round;
	
	public static void main(String[] args) throws Exception{
		TestNGProcesser pro = new TestNGProcesser();
		pro.test();
//		
//		ArrayList  res = new ArrayList();
//		while(true){
//			res.add(new DetailReports());
//		}
//		System.out.println(System.getProperty("user.dir"));
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
		
		//轮次结束，写回数据
		int apitotal = PublicDataHelper.getIns().getRound().getApitotal();
		int success = PublicDataHelper.getIns().getRound().getSuccess();
		int fail = PublicDataHelper.getIns().getRound().getFail();
		int notrun = PublicDataHelper.getIns().getRound().getNotrun();
		DatabaseHelper.updateRunReports(round, apitotal, success, fail, notrun);
	}
	
	//运行有顺序的用例
	private void runSequenceTests(Element eleSequence){
		loger.info("运行顺序执行的用例");
		try{
		//	SequenceTest的用例执行
		if(eleSequence != null){
			for(int i=0;i<eleSequence.elements().size();i++){
				pdh.setRunFlag(true);
				Element eleCur = (Element)eleSequence.elements().get(i);
//				Element eleCur = (Element)eleSequence.selectSingleNode(String.format("/AllTests/SequenceTests/SequenceTest[@index=\'%s\']",i+1));
//				String index = eleCur.attributeValue("index");
				String seqName = eleCur.attributeValue("name");
				//pre
				runTestCaseByTag(eleCur,"pre",seqName);
				if(pdh.getRunFlag() == false)
					continue;
				
				//test
				runTestCaseByTag(eleCur,"test",seqName);
				if(pdh.getRunFlag() == false)
					continue;
				
				//after
				runTestCaseByTag(eleCur,"after",seqName);
				if(pdh.getRunFlag() == false)
					continue;

			}
		}
		}catch(Exception ex){loger.error("运行顺序执行的用例时出错"+ex.getMessage());}
	}
	
	//根据标记运行相应的用例步骤
	private void runTestCaseByTag(Element eleCur,String tag,String seqName){
		for(int j=0;j<eleCur.elements(tag).size();j++){
			Element eleStep = (Element)eleCur.selectSingleNode(String.format("/AllTests/SequenceTests/SequenceTest[@name=\"%s\"]/%s[@stepid=\"%d\"]",seqName,tag,j+1));
			if(eleStep != null){
				String modelName  = eleStep.attributeValue("model");
				String caseName = eleStep.getTextTrim();
				String stepid = eleStep.attributeValue("stepid");
				
				pdh.initCaseData(modelName, caseName, null, round,seqName,stepid,tag);
				//执行TESTNG CASE
				loger.info("TESTNG动态执行用例。模块名：" + modelName + ",用例名称：" + caseName);
				
				if(tag.equals("test"))
					runTestNG("true");
				else
					runTestNG("false");
				
				if(pdh.getRunFlag() == false)
					break;
			}
		}
	}
	
	private void runTestNG(String param){
		XmlSuite suite = new XmlSuite();
//		suite.setName("TmpSuite");
		 
		XmlTest test = new XmlTest(suite);
		test.setName(param);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass(PropUtil.getRunClass()));
		test.setXmlClasses(classes) ;
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
		TestListenerAdapter tla = new TestListenerAdapter();
		List<Class> lis = new ArrayList<Class>();
		lis.add(TestListener.class);
		
		//运行TESTNG
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.addListener(tla);
		testng.setListenerClasses(lis);
		testng.run(); 
		
	}
}
