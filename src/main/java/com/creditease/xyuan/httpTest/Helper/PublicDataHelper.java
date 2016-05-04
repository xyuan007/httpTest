package com.creditease.xyuan.httpTest.Helper;

import com.creditease.xyuan.httpTest.dm.CaseData;
import com.creditease.xyuan.httpTest.dm.OutputData;
import com.creditease.xyuan.httpTest.dm.RoundData;

public class PublicDataHelper {
	private static PublicDataHelper instance = new PublicDataHelper();
	
	public static PublicDataHelper getIns(){
		return instance;
	}

	private CaseData casedata;
	private OutputData output;
	private RoundData round;
	private boolean  runFlag;

	public boolean getRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}

	public void initRoundData(int round){
		this.round = new RoundData(round);
		this.output = new OutputData();
	}
	
	public void initCaseData(String modelName,String caseName,String apitype,int round,String sequencename,String index,String casetype){
		this.casedata = new CaseData(modelName, caseName, apitype, round,sequencename,index,casetype);
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