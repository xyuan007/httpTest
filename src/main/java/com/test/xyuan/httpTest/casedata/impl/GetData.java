package com.test.xyuan.httpTest.casedata.impl;

import java.net.URLEncoder;
import java.util.List;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Helper.DataHelper;
import com.test.xyuan.httpTest.Util.PropUtil;
import com.test.xyuan.httpTest.casedata.ITestcaseData;

public class GetData implements ITestcaseData{
	private boolean urlEncode = false;
	
	public Object getCaseData(Element eleConfig) throws Exception {
//		if(eleConfig == null)
//			return "";
//		
//		if(eleConfig.element("params") == null)
//			return "";
		
		if(eleConfig.element("params").attribute("urlencode") != null)
			urlEncode = eleConfig.element("params").attributeValue("urlencode").equals("true")?true:false;
		
		DataHelper dh = new DataHelper();
		if(dh.getDataElement() == null)
			return "";
		else{
			StringBuffer sb = new StringBuffer();
			sb.append("?");
			List<Element> eles = dh.getDataElement().elements();
			for(int i=0;i<eles.size()-1;i++){
				Element temp = eles.get(i);
				sb.append(temp.getName());
				sb.append("=");
				String value = (urlEncode == true)?URLEncoder.encode(temp.getTextTrim(),PropUtil.getCharSet()):temp.getTextTrim();
				sb.append(value);
				sb.append("&");
			}
			Element temp = eles.get(eles.size()-1);
			sb.append(temp.getName());
			sb.append("=");
			String value = (urlEncode == true)?URLEncoder.encode(temp.getTextTrim(),PropUtil.getCharSet()):temp.getTextTrim();
			sb.append(value);
			return sb.toString();
		}
	}
}
