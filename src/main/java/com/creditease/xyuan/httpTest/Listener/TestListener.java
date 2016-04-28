package com.creditease.xyuan.httpTest.Listener;

import java.sql.Timestamp;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.creditease.xyuan.httpTest.Helper.DatabaseHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;

public class TestListener implements ITestListener  {
	

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		try {
			newDetailReports(result,"success");
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getInstance().getRound().incSuccess();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			newDetailReports(result,"fail");
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getInstance().getRound().incFail();
		}
	}

	public void onTestSkipped(ITestResult result) {
		try {
			newDetailReports(result,"notrun");
		} catch (Exception e) {
		}finally{
			PublicDataHelper.getInstance().getRound().incNotrun();
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
		int round = PublicDataHelper.getInstance().getCasedata().getRound();
		String sequencename = PublicDataHelper.getInstance().getCasedata().getSequencename();
		String index = PublicDataHelper.getInstance().getCasedata().getIndex();
		String apitype = PublicDataHelper.getInstance().getCasedata().getApitype();
		String apiname = PublicDataHelper.getInstance().getCasedata().getCaseName();
		String responsecode = PublicDataHelper.getInstance().getCasedata().getResponsecode();
		String message = "";
		String exectime = PublicDataHelper.getInstance().getCasedata().getExectime();
		Timestamp starttime = new Timestamp( result.getStartMillis());
		Timestamp endtime = new Timestamp(result.getEndMillis());
		
		DatabaseHelper.newReports(round, apitype, apiname, message, starttime, endtime, exectime, responsecode, status, sequencename, index);
	}

}
