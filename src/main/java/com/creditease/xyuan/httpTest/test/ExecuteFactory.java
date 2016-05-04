package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;
import com.creditease.xyuan.httpTest.process.IExecute;
import com.creditease.xyuan.httpTest.processImpl.httpgetProcesser;
import com.creditease.xyuan.httpTest.processImpl.socketProcesser;

public class ExecuteFactory {
    public static void createExecute(Element config,boolean bAssert) throws Exception{
//    	if(protocol.toLowerCase().contains("http")){
//    		exe = new HttpProcesser();  
//    	}
//    	else if(protocol.toLowerCase().contains("socket")){
//    		exe = new SocketProcesser();
//    	}
    	IExecute exe = null;
    	String protocol = config.elementText("protocol");
    	Class cs = Class.forName("com.creditease.xyuan.httpTest.processImpl." + protocol + "Processer");
    	Object  obj = cs.newInstance();
    	exe = (IExecute)obj;
    	
		exe.execute(config,bAssert);
    }
    
}
