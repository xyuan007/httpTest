package com.test.xyuan.httpTest.Listener;

import java.sql.Timestamp;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.xyuan.httpTest.Helper.DatabaseHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;

public class TestListener implements ITestListener  {

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		try {
			newDetailReports(result,"success");
			newLog();
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getIns().getRound().incSuccess();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			//根据配置设置是否继续运行
			if(ProjectPropUtil.getGoonWhenFail().equals("true"))
				PublicDataHelper.getIns().setRunFlag(true);
			else
				PublicDataHelper.getIns().setRunFlag(false);
			
			newDetailReports(result,"fail");
			newLog();
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getIns().getRound().incFail();
		}
	}

	public void onTestSkipped(ITestResult result) {
		try {
			//根据配置设置是否继续运行
			if(ProjectPropUtil.getGoonWhenFail().equals("true"))
				PublicDataHelper.getIns().setRunFlag(true);
			else
				PublicDataHelper.getIns().setRunFlag(false);
			
			newDetailReports(result,"notrun");
			newLog();
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getIns().getRound().incNotrun();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		try{
		}
		catch(Exception ex){ex.printStackTrace();}
	}

	public void onFinish(ITestContext context) {
		try{
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	
	private void newDetailReports(ITestResult result,String status) throws Exception{
		int round = PublicDataHelper.getIns().getCasedata().getRound();
		String sequencename = PublicDataHelper.getIns().getCasedata().getSequencename();
		String index = PublicDataHelper.getIns().getCasedata().getIndex();
		String apitype = PublicDataHelper.getIns().getCasedata().getApitype();
		String apiname = PublicDataHelper.getIns().getCasedata().getModelName();	
		String responsecode = PublicDataHelper.getIns().getCasedata().getResponsecode();
		String message = "";
		String exectime = PublicDataHelper.getIns().getCasedata().getExectime();
		String casetype = PublicDataHelper.getIns().getCasedata().getCasetype();
		String projectname = PublicDataHelper.getIns().getCasedata().getProjectName();
		String requesturl = PublicDataHelper.getIns().getCasedata().getRequestURL();
		String requestdata = PublicDataHelper.getIns().getCasedata().getRequestData();
		String responsedata = PublicDataHelper.getIns().getCasedata().getResponseData();
		Timestamp starttime = new Timestamp( result.getStartMillis());
		Timestamp endtime = new Timestamp(result.getEndMillis());
		
		DatabaseHelper.newReports(round, apitype, apiname, message, starttime, endtime, exectime, responsecode, status, sequencename, index,casetype,projectname,requesturl,requestdata,responsedata);
	}

	private void newLog() throws Exception{
		String projectName = PublicDataHelper.getIns().getCasedata().getProjectName();
		int round = PublicDataHelper.getIns().getCasedata().getRound();
		DatabaseHelper.newLog(round, projectName, MyLog.getMsg());
	}
}
