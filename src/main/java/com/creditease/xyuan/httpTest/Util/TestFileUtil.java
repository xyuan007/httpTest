package com.creditease.xyuan.httpTest.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class TestFileUtil {
	public static List<String> getTestFile(){
		List<String> file = new ArrayList<String>();
		if(PropUtil.getRunMode().equals("1")){
			File[] files = new File("test").listFiles();
			if(files!=null){
				for(File f : files){
					file.add(f.getPath());
				}
			}
		}
		else if(PropUtil.getRunMode().equals("2")){
			String[] files = PropUtil.getTestFile().split(",");
			for(String f:files){
				file.add("test/"+f);
			}
		}
		else if(PropUtil.getRunMode().equals("3")){
			file.add("config/AppointedTest.xml");
		}
		
		return file;
	}
	
	public static Element getSingleTests(String file) throws Exception{
		Element root = MyXMLUtil.getRootElement(file);
		return (Element) root.selectSingleNode("/AllTests/SingleTest");
	}
	
	public static Element getSequenceTests(String file) throws Exception{
		Element root = MyXMLUtil.getRootElement(file);
		return (Element) root.selectSingleNode("/AllTests/SequenceTests");
	}
	
}
