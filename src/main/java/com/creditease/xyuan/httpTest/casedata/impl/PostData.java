package com.creditease.xyuan.httpTest.casedata.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.casedata.ITestcaseData;

public class PostData implements ITestcaseData{

	public Object getCaseData(Element eleConfig) throws Exception {
		
		DataHelper dh = new DataHelper();
		if(dh.getDataElement() == null)
			return "";
		else{
			List<Element> eles = dh.getDataElement().elements();
			List <NameValuePair> params = new ArrayList<NameValuePair>(); 
			for(int i=0;i<eles.size();i++){
				Element temp  = eles.get(i);
				params.add(new BasicNameValuePair(temp.getName(), temp.getTextTrim()));  
			}
			
			return new UrlEncodedFormEntity(params);
		}
	}

}
