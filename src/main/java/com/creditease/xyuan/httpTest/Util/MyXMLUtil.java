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
}
