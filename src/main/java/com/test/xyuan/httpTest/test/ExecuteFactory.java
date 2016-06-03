package com.test.xyuan.httpTest.test;

import org.dom4j.Element;

import com.test.xyuan.httpTest.process.IExecute;

public class ExecuteFactory {
    public static void createExecute(Element config,boolean bAssert) throws Exception{
    	IExecute exe = null;
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.test.xyuan.httpTest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	exe = (IExecute)obj;
    	
		exe.execute(config,bAssert);
    }
    
}
