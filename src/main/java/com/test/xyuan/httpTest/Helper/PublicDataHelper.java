package com.test.xyuan.httpTest.Helper;

import com.test.xyuan.httpTest.dm.CaseData;
import com.test.xyuan.httpTest.dm.OutputData;
import com.test.xyuan.httpTest.dm.RoundData;

public class PublicDataHelper {
	private static PublicDataHelper instance = new PublicDataHelper();
	
	public static PublicDataHelper getIns(){
		return instance;
	}

	private CaseData casedata;
	private OutputData output;
	private RoundData round;
	private String  cycleFlag;
	private boolean  runFlag;

	public String getCycleFlag() {
		return cycleFlag;
	}

	public void setCycleFlag(String cycleFlag) {
		this.cycleFlag = cycleFlag;
	}	
	
	public boolean getRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}
	
//	public void initLogData(int round,String projectName,String logInfo){
//		this.log = new LogData(round,projectName,logInfo);
//	}

	public void initRoundData(int round){
		this.round = new RoundData(round);
		this.output = new OutputData();
	}
	
	public void initCaseData(String modelName,String caseName,String apitype,int round,String sequencename,String index,String casetype,String projectname){
		this.casedata = new CaseData(modelName, caseName, apitype, round,sequencename,index,casetype,projectname);
	}

	public CaseData getCasedata() {
		return casedata;
	}

	public OutputData getOutput() {
		return output;
	}

	public RoundData getRound() {
		return round;
	}
	
}
