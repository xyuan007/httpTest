package com.test.xyuan.httpTest.test;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.test.xyuan.httpTest.Helper.DatabaseHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Listener.TestListener;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.Util.SystemPropUtil;
import com.test.xyuan.httpTest.Util.TestFileUtil;

public class TestNGProcesser {
	private static MyLog loger = MyLog.getLoger();
	private static PublicDataHelper pdh = PublicDataHelper.getIns();
	private static int round;
	
	public static void main(String[] args) throws Exception{
		TestNGProcesser pro = new TestNGProcesser();
		pro.testProject();
	}
	
	//执行所有项目的用例
	public void testProject() throws Exception{
		String[] testProject = SystemPropUtil.getProjectList().split(",");
		for(int i=0;i<testProject.length;i++){
			String curProject = testProject[i];
			ProjectPropUtil.setPojectName(curProject);
			test(curProject);
		}
	}
	
	//执行当前项目
	public void test(String proName) throws Exception{
		Element eleSequence = null;
		
		//轮次，公共数据初始
		round = DatabaseHelper.getMaxRound(proName) + 1;
		DatabaseHelper.newRunReports(round);
		pdh.initRoundData(round);
		
		//取得运行模式
		loger.info("取得runmode对应的用例文件");
		List<String> files = TestFileUtil.getTestFile();
		
		loger.info("取得待运行的测试用例");
		for(String file: files){
			eleSequence = TestFileUtil.getAllTests(file);
			runSequenceTests(eleSequence);
		}
		
		//轮次结束，写回数据
		int apitotal = PublicDataHelper.getIns().getRound().getApitotal();
		int success = PublicDataHelper.getIns().getRound().getSuccess();
		int fail = PublicDataHelper.getIns().getRound().getFail();
		int notrun = PublicDataHelper.getIns().getRound().getNotrun();
		DatabaseHelper.updateRunReports(round, apitotal, success, fail, notrun,ProjectPropUtil.getProjectName());
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
	private void runTestCaseByTag(Element eleCur,String tag,String seqName) throws Exception{
		for(int j=0;j<eleCur.elements(tag).size();j++){
			Element eleStep = (Element)eleCur.selectSingleNode(String.format("/TestSuite/TestCase[@name=\"%s\"]/%s[@stepid=\"%d\"]",seqName,tag,j+1));
			if(eleStep != null){
				String modelName  = eleStep.attributeValue("model");
				String caseName = eleStep.getTextTrim();
				String stepid = eleStep.attributeValue("stepid");
				String cycle = eleStep.attributeValue("cycle");
				
				pdh.initCaseData(modelName, caseName, null, round,seqName,stepid,tag,ProjectPropUtil.getProjectName());
				//执行TESTNG CASE
				loger.info("TESTNG动态执行用例。模块名：" + modelName + ",用例名称：" + caseName);

				//需要循环执行的请求
				if(cycle!=null && cycle.equals("true")){
					int timeout = ProjectPropUtil.getTimeOut();
					int total = ProjectPropUtil.getWaitTime();
					PublicDataHelper.getIns().setCycleFlag("true");//初始需要循环
					int i = 0;
					for(;i<total-1;i++){
						runTestNGByTag(tag);
						
						if(PublicDataHelper.getIns().getCycleFlag()=="true")//还需要循环
							Thread.sleep(timeout*1000);
						else
							break;
					}
					//循环结束，超时仍未拿到结果，直接失败
					PublicDataHelper.getIns().setCycleFlag("timeout");
					runTestNGByTag(tag);
				}
				else{//不需要循环，直接执行请求
					runTestNGByTag(tag);
				}
				
				if(pdh.getRunFlag() == false)
					break;
			}
		}
	}
	
	private void runTestNGByTag(String tag){
		if(tag.equals("test"))
			runTestNG("true");//测试
		else
			runTestNG("false");//执行前置或者后置的操作
	}
	
	private void runTestNG(String param){
		XmlSuite suite = new XmlSuite();
//		suite.setName("TmpSuite");
		 
		XmlTest test = new XmlTest(suite);
		test.setName(param);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass(ProjectPropUtil.getRunClass()));
		test.setXmlClasses(classes) ;
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
//		TestListenerAdapter tla = new TestListenerAdapter();
//		List<Class> lis = new ArrayList<Class>();
//		lis.add(TestListener.class);
		
		//运行TESTNG
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.addListener(new TestListener());
		testng.run(); 
	}
}
