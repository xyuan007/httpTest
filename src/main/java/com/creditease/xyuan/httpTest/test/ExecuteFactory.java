package com.creditease.xyuan.httpTest.test;

import org.dom4j.Element;

import com.creditease.xyuan.httpTest.process.IExecute;
import com.creditease.xyuan.httpTest.processImpl.HttpProcesser;
import com.creditease.xyuan.httpTest.processImpl.SocketProcesser;

public class ExecuteFactory {
    public static void createExecute(String protocol,Element config) throws Exception{
    	IExecute exe = null;
    	if(protocol.toLowerCase().contains("http")){
    		exe = new HttpProcesser();  
    	}
    	else if(protocol.toLowerCase().contains("socket")){
    		exe = new SocketProcesser();  
    	}
    	
		exe.execute(config);
    }
}
