package com.creditease.xyuan.httpTest.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.sf.json.JSONObject;
import com.creditease.xyuan.httpTest.Helper.DataHelper;

public class tag {
	private DataHelper dh = null;
	private JSONObject header = null;
	private JSONObject json = null;
	
	public tag(String response) throws Exception{
		dh = new DataHelper();
		
		json = JSONObject.fromObject(response);
		header = JSONObject.fromObject(json.get("header"));
	}
	
	public void setTag() {

	}
	
	public void setMaxTag() {

	}
	
	public void setExceedTag() {
		assertThat((String)header.get("resType"),equalTo("tag"));
		assertThat((String)header.get("status"),equalTo("0"));
		assertThat((String)header.get("message"),equalTo("设置的标签个数超过100，不能处理111"));
	}
	
	
	public void setLongTag(){
		assertThat((String)header.get("resType"),equalTo("tag"));
		assertThat((String)header.get("status"),equalTo("0"));
		assertThat((String)header.get("message"),equalTo("设置的标签名称(超过了最长字符数超过了最长字符数超过了最长)超过20个字符不符合规则,不能处理"));
	}
	
	public void setEmptyTag(){
		assertThat((String)header.get("resType"),equalTo("tag"));
		assertThat((String)header.get("status"),equalTo("1"));
		assertThat((String)header.get("message"),equalTo("处理成功"));
		assertThat((String)json.get("body"),equalTo("清空设备标签处理成功"));		
	}
	
	public void setNullTag(){
		assertThat((String)header.get("resType"),equalTo("tag"));
		assertThat((String)header.get("status"),equalTo("1"));
		assertThat((String)header.get("message"),equalTo("处理成功"));
		assertThat((String)json.get("body"),equalTo("清空设备标签处理成功"));
	}
}
