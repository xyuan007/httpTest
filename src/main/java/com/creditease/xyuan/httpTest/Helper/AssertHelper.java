package com.creditease.xyuan.httpTest.Helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.log4testng.Logger;

import com.creditease.xyuan.httpTest.Assert.connect;
import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.MyLog;

public class AssertHelper {
	private static MyLog logger = MyLog.getLoger();
	private static String pkgname = "com.creditease.xyuan.httpTest.Assert";
	
	public static void asserting(String response) throws Exception {
		String filename = BizDataUtil.getModelName();
		String methodname = BizDataUtil.getCaseName();
		Class clazz;
		try {
			logger.info("验证的方法名：" + methodname);
			clazz = Class.forName(pkgname+"."+filename);
			Constructor c1 = clazz.getDeclaredConstructor(new Class[]{String.class});
			c1.setAccessible(true); 
			Object obj = c1.newInstance(response);
			Method method = clazz.getMethod(methodname, null);  
			method.invoke(obj,null);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
