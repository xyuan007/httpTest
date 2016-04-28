package com.creditease.xyuan.httpTest.dm;

import java.util.Date;

public class CaseData {
	private int round;
	private String apitype;
	private String responsecode;
	private String message;
	private String status;
	private Date starttime;
	private Date endtime;
	private String exectime;
	private String modelName ;
	private String caseName;
	private String outputField;
	private String sequencename;
	private String index;
	
	public String getSequencename() {
		return sequencename;
	}

	public void setSequencename(String sequencename) {
		this.sequencename = sequencename;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public CaseData(){
		
	}
	
	public CaseData(String modelName,String caseName,String apitype,int round,String sequencename,String index){
		this.round = round;
		this.caseName = caseName;
		this.apitype = apitype;
		this.modelName = modelName;
		this.sequencename = sequencename;
		this.index = index;
	}
	
	

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getApitype() {
		return apitype;
	}

	public void setApitype(String apitype) {
		this.apitype = apitype;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getExectime() {
		return exectime;
	}

	public void setExectime(String exectime) {
		this.exectime = exectime;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getOutputField() {
		return outputField;
	}

	public void setOutputField(String outputField) {
		this.outputField = outputField;
	}
	
	
}
