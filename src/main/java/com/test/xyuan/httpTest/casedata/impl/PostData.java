package com.test.xyuan.httpTest.casedata.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Helper.DataHelper;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;
import com.test.xyuan.httpTest.casedata.ITestcaseData;

public class PostData implements ITestcaseData{
	private static MyLog loger = MyLog.getLoger();
	
	public Object getCaseData(Element eleConfig){
		if(eleConfig == null)
			return null;
		
//		if(eleConfig.element("params") == null)
//			return null;
		
		DataHelper dh = new DataHelper();
		if(dh.getDataElement() == null)
			return null;
		else{
			List<Element> eles = dh.getDataElement().elements();
			List <NameValuePair> params = new ArrayList<NameValuePair>(); 
			for(int i=0;i<eles.size();i++){
				Element temp  = eles.get(i);
				//处理特殊类型的数据
				if(temp.attributeValue("func") == null)
					params.add(new BasicNameValuePair(temp.getName(), temp.getTextTrim()));
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
					
					params.add(new BasicNameValuePair(temp.getName(), (String)obj));
				}
			}
			
			try {
				return new UrlEncodedFormEntity(params,ProjectPropUtil.getCharSet());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				loger.error(e.getMessage());
			}
		}
		return null;
	}
	
	public static void main(String[] args111) throws Exception{
	}

}
