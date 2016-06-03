package com.test.xyuan.httpTest.POJO;

import java.sql.Timestamp;
import java.util.Date;

public class RunReports {
	private Date day;
	private int round;
	private String apitype;
	private String apiname;
	private String status;
	private String responsecode;
	private String message;
	private int exectime;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp createtime;
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
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
	public String getApiname() {
		return apiname;
	}
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getExectime() {
		return exectime;
	}
	public void setExectime(int exectime) {
		this.exectime = exectime;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
}
