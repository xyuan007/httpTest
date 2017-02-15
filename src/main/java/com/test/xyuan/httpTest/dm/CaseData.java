package com.test.xyuan.httpTest.dm;

import java.util.Date;

public class CaseData {
	private int round;//轮次
	private String apitype;//API类型
	private String responsecode;//返回码
	private String message;//错误信息
	private String status;//执行状态
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String exectime;//执行时间（秒）
	private String modelName ;//模块名称，也就是调用的API的名称
	private String caseName;//测试用例名称
	private String outputField;//输出的字段名
	private String sequencename;//测试用例名称
	private String index;//顺序号
	private String casetype;//用例类型（pre,after,test）
	private String projectname;//测试项目名称
	
	private String requestURL;//请求URL
	private String requestData;//请求数据
	private String responseData;//响应数据
	
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

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
	
	public CaseData(String modelName,String caseName,String apitype,int round,String sequencename,String index,String casetype,String projectname){
		this.round = round;
		this.caseName = caseName;
		this.apitype = apitype;
		this.modelName = modelName;
		this.sequencename = sequencename;
		this.index = index;
		this.casetype = casetype;
		this.projectname = projectname;
	}
	
	public String getCasetype() {
		return casetype;
	}
	
	public String getProjectName(){
		return projectname;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
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
