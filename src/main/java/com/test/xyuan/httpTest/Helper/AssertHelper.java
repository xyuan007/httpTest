package com.test.xyuan.httpTest.Helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;

public class AssertHelper {
	private static MyLog logger = MyLog.getLoger();
	private static String pkgname = "com.test.xyuan.httpTest.Assert";
	
	public static void asserting(String response) throws Exception {
		String filename = PublicDataHelper.getIns().getCasedata().getModelName();
		String methodname = PublicDataHelper.getIns().getCasedata().getCaseName();
		Class clazz;
		try {
			logger.info("验证的方法名：" + methodname);
			clazz = Class.forName(String.format("%s.%s.%s", pkgname,ProjectPropUtil.getProjectName(),filename) );
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
