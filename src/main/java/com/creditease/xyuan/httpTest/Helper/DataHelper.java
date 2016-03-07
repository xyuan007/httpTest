package com.creditease.xyuan.httpTest.Helper;

import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Util.*;

public class DataHelper {
	String projectName = null;
	Element ele = null;
	
	public DataHelper() throws Exception{
		this.projectName = PropUtil.getProjectName();
		String configFile = String.format("data\\%s\\%s.xml", this.projectName,BizDataUtil.getBizData().getModelName());
		Element root = XMLUtil.getRootElement(configFile);
		
		ele = (Element)root.selectSingleNode(String.format("/TestSuite/TestCase[@name=\"%s\"]",BizDataUtil.getBizData().getCaseName()));
	}
	
	public Element getDataElement(){
		return this.ele;
	}
	
	public int getStepNum(){
		return this.ele.elements("step").size();
	}
	
	
}
