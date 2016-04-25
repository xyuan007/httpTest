package com.creditease.xyuan.httpTest.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.sf.json.JSONObject;
import com.creditease.xyuan.httpTest.Helper.DataHelper;

public class clearTag {
	private DataHelper dh = null;
	private JSONObject header = null;
	private JSONObject json = null;
	
	public clearTag(String response) throws Exception{
		dh = new DataHelper();
		
		json = JSONObject.fromObject(response);
		header = JSONObject.fromObject(json.get("header"));
	}
	
	public void cleartag() {
		assertThat((String)header.get("resType"),equalTo("clearTag"));
		assertThat((String)header.get("status"),equalTo("1"));
		assertThat((String)header.get("message"),equalTo("处理成功"));
	}
}
