package com.creditease.xyuan.httpTest.dm;

public class RoundData {
	private int round;
	private int apitotal;
	private int success;
	private int fail;
	private int notrun;
	
	public  RoundData(){}
	
	public RoundData(int round){
		this.round = round;
	}
	
	public int getRound() {
		return round;
	}
	public int getApitotal() {
		return apitotal;
	}
	public int getSuccess() {
		return success;
	}
	public int getFail() {
		return fail;
	}
	public int getNotrun() {
		return notrun;
	}
	
	public void incSuccess(){
		this.success++;
		this.apitotal++;
	}
	
	public void incNotrun(){
		this.notrun++;
		this.apitotal++;
	}
	
	public void incFail(){
		this.fail++;
		this.apitotal++;
	}

}
