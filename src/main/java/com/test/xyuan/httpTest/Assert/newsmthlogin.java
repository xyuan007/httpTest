package com.test.xyuan.httpTest.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.test.xyuan.httpTest.Helper.DataHelper;

import net.sf.json.JSONObject;

public class newsmthlogin {
	private DataHelper dh = null;
	private JSONObject header = null;
	private JSONObject json = null;
	
	public newsmthlogin(String response) throws Exception{
//		dh = new DataHelper();
//		
//		json = JSONObject.fromObject(response);
//		header = JSONObject.fromObject(json.get("header"));
	}
	
	public void login() {
	}
	
	
}
