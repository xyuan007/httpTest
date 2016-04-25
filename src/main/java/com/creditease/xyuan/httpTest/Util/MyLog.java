package com.creditease.xyuan.httpTest.Util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLog {
	private Logger loger; 
	 
	private MyLog(){
		 loger=Logger.getLogger(this.getClass()); 
		 PropertyConfigurator.configure("config/log4j.properites"); 
	}
	
	public static MyLog getLoger()
    {
         return new MyLog();
    } 
	
	public void info(String info){
		this.loger.info(info);
	}
	
	public void error(String error){
		this.loger.error(error);
	}
	
	public void warn(String warn){
		this.loger.warn(warn);
	}
	
	public void debug(String debug){
		this.loger.debug(debug);
	}
	
}
