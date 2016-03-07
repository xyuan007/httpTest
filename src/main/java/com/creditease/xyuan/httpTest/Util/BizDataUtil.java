package com.creditease.xyuan.httpTest.Util;

import com.creditease.xyuan.httpTest.object.BizData;

public class BizDataUtil {
	private static BizData bd = null;

	public static BizData getBizData() {
		return bd;
	}

	public static void setBizData(BizData bd) {
		BizDataUtil.bd = bd;
	}
	
}
