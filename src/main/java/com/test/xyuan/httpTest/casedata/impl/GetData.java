package com.test.xyuan.httpTest.casedata.impl;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import org.dom4j.Element;
import com.test.xyuan.httpTest.Helper.DataHelper;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.casedata.ITestcaseData;

public class GetData implements ITestcaseData{
	private static MyLog loger = MyLog.getLoger();
	private boolean urlEncode = false;
	
	private StringBuffer getParamStr(StringBuffer sb,Element temp) throws Exception{
		if(temp.attributeValue("func") == null){
			sb.append(temp.getName());
			sb.append("=");
			String value = (urlEncode == true)?URLEncoder.encode(temp.getTextTrim(),ProjectPropUtil.getCharSet()):temp.getTextTrim();
			sb.append(value);
		}
		else{
			Object obj = null;
			try{
				Class clazz = Class.forName(temp.attributeValue("func"));

				if(temp.attributeValue("param") == null){
					Method method = clazz.getMethod(temp.getTextTrim(), null);  
					obj = method.invoke(null);
				}
				else{
					Class[] args = new Class[1];
					args[0] = String.class;
					Method method = clazz.getMethod(temp.getTextTrim(),args);
					
					Object[] p = new Object[1];
					p[0] = (String)temp.attributeValue("param");
					obj = method.invoke(null,p);
				}
			}catch(Exception e){
				loger.error(e.getMessage());
			}
			
			sb.append(temp.getName());
			sb.append("=");
			String value = (urlEncode == true)?URLEncoder.encode((String)obj,ProjectPropUtil.getCharSet()):(String)obj;
			sb.append(value);
		}
		
		return sb;
	}
	
	public Object getCaseData(Element eleConfig) throws Exception {
		if(eleConfig == null)
			return null;
		
		DataHelper dh = new DataHelper();
		if(dh.getDataElement() == null)
			return "";
		else{
			StringBuffer sb = new StringBuffer();
			sb.append("?");
			List<Element> eles = dh.getDataElement().elements();
			for(int i=0;i<eles.size()-1;i++){
				Element temp = eles.get(i);
				
				sb = getParamStr(sb,temp);
				sb.append("&");
			}
			Element temp = eles.get(eles.size()-1);
			sb = getParamStr(sb,temp);
			
			return sb.toString();
		}
	}
}
