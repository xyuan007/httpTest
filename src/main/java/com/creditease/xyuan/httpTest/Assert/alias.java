package com.creditease.xyuan.httpTest.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.sf.json.JSONObject;
import com.creditease.xyuan.httpTest.Helper.DataHelper;

public class alias {
	private DataHelper dh = null;
	private JSONObject header = null;
	private JSONObject json = null;
	
	public alias(String response) throws Exception{
		dh = new DataHelper();
		
		json = JSONObject.fromObject(response);
		header = JSONObject.fromObject(json.get("header"));
	}
	
	public void setAlias() {
		assertThat((String)header.get("resType"),equalTo("alias"));
		assertThat((String)header.get("status"),equalTo("1"));
		assertThat((String)header.get("message"),equalTo("处理成功"));
	}
	
	public void setAliasByInput() {
		assertThat((String)header.get("resType"),equalTo("alias"));
		assertThat((String)header.get("status"),equalTo("1"));
		assertThat((String)header.get("message"),equalTo("处理成功"));
	}
	
	public void setLongAlias() {
		assertThat((String)header.get("resType"),equalTo("alias"));
		assertThat((String)header.get("status"),equalTo("0"));
		assertThat((String)header.get("message"),equalTo("设置的别名超过20个字符,不能处理"));
	}
	
	public void setNullAlias() {
		assertThat((String)header.get("resType"),equalTo("alias"));
		assertThat((String)header.get("status"),equalTo("0"));
		assertThat((String)header.get("message"),equalTo("未设置别名,不能处理"));
	}
	
	public void setEmptyAlias(){
		assertThat((String)header.get("resType"),equalTo("alias"));
		assertThat((String)header.get("status"),equalTo("0"));
		assertThat((String)header.get("message"),equalTo("未设置别名,不能处理"));
	}
	
}
