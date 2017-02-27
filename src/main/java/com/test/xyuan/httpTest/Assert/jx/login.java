package com.test.xyuan.httpTest.Assert.jx;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;

import net.sf.json.JSONObject;

public class login {
	private JSONObject data = null;
	private JSONObject json = null;
	
	public login(String response) throws Exception{
		json = JSONObject.fromObject(response);
		data = JSONObject.fromObject(json.get("data"));
		PublicDataHelper.getIns().getOutput().setValue("token", (String)data.get("token"));
	}
	
	public void login() {
		assertThat((String)json.get("message"),equalTo("成功"));
		assertThat((int)data.get("status"),equalTo(1));
		assertThat((int)data.get("code"),equalTo(101));
	}
	
}
