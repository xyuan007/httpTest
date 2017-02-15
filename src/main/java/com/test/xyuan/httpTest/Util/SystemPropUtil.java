package com.test.xyuan.httpTest.Util;

import java.io.FileInputStream;
import java.util.Properties;


public class SystemPropUtil 
{
	public static  void main(String[] args) throws Exception{
		System.out.println(getProp("RunMode"));
	}
	
	static private Properties prop = null;
	
	static{
		prop = new Properties();
    	FileInputStream fis;
		try {
			fis = new FileInputStream("config\\httpTest.properites");
	    	prop.load(fis);
		} catch (Exception e) {
		}//属性文件流      
	}
	
	//拿配置文件 
    public static String getProp(String key){
    	return prop.getProperty(key);
    }
    
    public static String getProjectList(){
    	return getProp("TestProject");
    }
    
    public static String getMysqlUrl(){
    	return getProp("mysqlurl");
    }
    
    public static String getMysqlUserName(){
    	return getProp("mysqlusername");
    }
    
    public static String getMysqlPassword(){
    	return getProp("mysqlpassword");
    }
}
