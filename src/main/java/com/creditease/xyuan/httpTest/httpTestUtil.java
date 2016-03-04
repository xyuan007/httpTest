package com.creditease.xyuan.httpTest;

import java.io.FileInputStream;
import java.util.Properties;


public class httpTestUtil 
{
	public static  void main(String[] args) throws Exception{
		System.out.println(getProp("RunMode"));
	}
	
	//拿配置文件 
    public static String getProp(String key) throws Exception{
    	Properties prop = new Properties();
    	FileInputStream fis = new FileInputStream("config\\httpTest.properites");//属性文件流      
    	prop.load(fis);
    	
    	return prop.getProperty(key);
    }
}
