package com.test.xyuan.httpTest.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class TestFileUtil {
	public static MyLog loger = MyLog.getLoger();
	
	public static List<String> getTestFile(){
		List<String> file = new ArrayList<String>();
		if(ProjectPropUtil.getRunMode().toLowerCase().equals("all")){//所有文件 
			loger.info("runMode:1，运行所有文件 ");
			File[] files = new File("test/" + ProjectPropUtil.getProjectName()).listFiles();
			if(files!=null){
				for(File f : files){
					file.add(f.getPath());
				}
			}
		}
		else if(ProjectPropUtil.getRunMode().toLowerCase().equals("file")){//指定的某些文件 ，以逗号分隔
			loger.info("runMode:2，运行指定的文件 "+ProjectPropUtil.getTestFile());
			String[] files = ProjectPropUtil.getTestFile().split(",");
			for(String f:files){
				file.add("test/" + ProjectPropUtil.getProjectName() + "/" +f);
			}
		}
		else if(ProjectPropUtil.getRunMode().toLowerCase().equals("debug")){//指定的用例，AppointedTest
			loger.info("runMode:3，运行AppointedTest.xml下的指定用例 ");
			file.add("config/AppointedTest.xml");
		}
		
		return file;
	}
	
	public static Element getAllTests(String file){
		Element root = MyXMLUtil.getRootElement(file);
		return (Element) root.selectSingleNode("/TestSuite");
	}
	
}
