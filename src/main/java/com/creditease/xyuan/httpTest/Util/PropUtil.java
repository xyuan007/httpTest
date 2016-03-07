package com.creditease.xyuan.httpTest.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    
    public static int getProxyPort(){
    	return Integer.parseInt( getProp("ProxyPort"));
    }
}
