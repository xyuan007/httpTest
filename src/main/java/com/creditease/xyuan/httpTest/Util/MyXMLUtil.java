package com.creditease.xyuan.httpTest.Util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXMLUtil {
	
	public static Element getRootElement(String file) throws Exception{
		SAXReader reader = new SAXReader();           
	    Document   doc = reader.read(new File(file));
		return doc.getRootElement();  
	}
}
