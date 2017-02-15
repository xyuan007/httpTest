package com.test.xyuan.httpTest.Util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLog {
	private Logger loger; 
	private static String msg;
	 
	private MyLog(){
		 loger=Logger.getLogger(this.getClass()); 
		 PropertyConfigurator.configure("config/log4j.properites"); 
	}
	
	public static MyLog getLoger()
    {
         return new MyLog();
    } 
	
	public static String getMsg(){
		return msg;
	}
	
	public void info(String info){
		this.loger.info(info);
		msg = info;
	}
	
	public void error(String error){
		this.loger.error(error);
		msg=error;
	}
	
	public void warn(String warn){
		this.loger.warn(warn);
		msg=warn;
	}
	
	public void debug(String debug){
		this.loger.debug(debug);
		msg=debug;
	}
	
}
