package com.creditease.xyuan.httpTest.process;

import org.dom4j.Element;
import com.creditease.xyuan.httpTest.processImpl.HttpProcesser;
import com.creditease.xyuan.httpTest.processImpl.SocketProcesser;

public class ExecuteFactory {
	public static IExecute executeHttp(){  
        return new HttpProcesser();  
    }  
      
    public static IExecute exeSocket(){  
        return new SocketProcesser();  
    }
    
    public static void createExecute(String protocol,Element config) throws Exception{
    	IExecute exe = null;
    	if(protocol.toLowerCase().contains("http")){
    		exe = ExecuteFactory.executeHttp();
    	}
    	else if(protocol.toLowerCase().contains("socket")){
    		exe = ExecuteFactory.exeSocket();
    	}
    	
		exe.execute(config);
    }
}
