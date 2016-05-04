package com.creditease.xyuan.httpTest.casedata.impl;

import org.dom4j.Element;

import com.creditease.xyuan.httpTest.casedata.ITestcaseData;

public class GetData implements ITestcaseData{
	public String getCaseData(Element eleConfig, Element eleData) throws Exception {
		if(eleData == null)
			return null;
		
		if(eleConfig == null)
			throw new Exception("数据类型节点未进行配置");
		
		
		return null;
	}

}
