package com.creditease.xyuan.httpTest.Util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXMLUtil {
	public static MyLog loger = MyLog.getLoger();
	
	public static Element getRootElement(String file) {
		SAXReader reader = new SAXReader();           
	    Document doc = null;
		try {
			doc = reader.read(new File(file));
		} catch (DocumentException e) {
			loger.error(e.getMessage());
		}
		return doc.getRootElement();  
	}
	
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<100;i++){
			sb.append("tag"+i+",");
		}
		System.out.println(sb.toString());
	}
}
