package com.creditease.xyuan.httpTest.Util;

import java.io.FileInputStream;
import java.util.Properties;


public class PropUtil 
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
    
    public static String getProjectName(){
    	return getProp("TestProject");
    }
    
    public static String getHttpProxy(){
    	return getProp("HttpProxy");
    }
    
    public static String getProxyIP(){
    	return getProp("ProxyIP");
    }
    
    public static String getRunMode(){
    	return getProp("RunMode");
    }
    
    public static String getTestFile(){
    	return getProp("TestFile");
    }
    
    public static String getRunClass(){
    	return getProp("RunClass");
    }
    
    public static String getSocketIP(){
    	return getProp("SocketIP");
    }
    
    public static String getFuncClass(){
    	return getProp("FuncClass");
    }
    
    public static String getCharSet(){
    	return getProp("CharSet");
    }
    
    public static int getSocketPort(){
    	return Integer.parseInt(getProp("SocketPort"));
    }
    
    public static int getProxyPort(){
    	return Integer.parseInt( getProp("ProxyPort"));
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
