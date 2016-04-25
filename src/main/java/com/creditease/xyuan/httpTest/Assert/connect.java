package com.creditease.xyuan.httpTest.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.log4testng.Logger;

import net.sf.json.JSONObject;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Util.OutputUtil;

public class connect {
	private JSONObject header;
	private JSONObject body;
	public static Logger logger = Logger.getLogger(connect.class); 
	
	public connect(String response) throws Exception{
		DataHelper dh = new DataHelper();
		
		JSONObject json = JSONObject.fromObject(response);
		header = JSONObject.fromObject(json.get("header"));
		body = JSONObject.fromObject(json.get("body"));
	}
	
	public void newUserLogin() throws Exception{
		assertThat((String)header.get("resType") , equalTo("login") ); 
		assertThat(((String)body.get("uid")).length(), greaterThan(0)); 
		
		OutputUtil.setValue("uid", (String)body.get("uid"));
	}
	
	public void oldUserLoginWithUID() throws Exception{
		assertThat((String)header.get("resType") , equalTo("login") ); 
		assertThat((String)header.get("status") , equalTo("1") ); 		
		assertThat((String)body.get("uid"), equalTo("d2748ea7d6fe96136259e0c4e4879f36")); 
	}
	
	public void oldUserLoginWithoutUID() throws Exception{
		assertThat((String)header.get("resType") , equalTo("login") ); 
		assertThat((String)body.get("uid"), equalTo("d2748ea7d6fe96136259e0c4e4879f36")); 
	}
}
