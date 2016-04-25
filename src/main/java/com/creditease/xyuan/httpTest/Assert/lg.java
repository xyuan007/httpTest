package com.creditease.xyuan.httpTest.Assert;

import net.sf.json.JSONObject;

import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Util.BizDataUtil;
import com.creditease.xyuan.httpTest.Util.OutputUtil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class lg {
	private DataHelper dh = null;
	private JSONObject data = null;
	private JSONObject json = null;
	
	public lg(String response) throws Exception{
		dh = new DataHelper();
		
		json = JSONObject.fromObject(response);
		data = JSONObject.fromObject(json.get("data"));
	}
	
	public void luOKAssert() {
		
		assertThat((String)json.get("sta") , equalTo("1") ); 
		assertThat((String)json.get("msg") , equalTo("登录成功") ); 
		assertThat((String)data.get("email") , equalTo(dh.getDataByField("email")) ); 
		
		//输出
		if(BizDataUtil.getOutputField() != null){
			if(BizDataUtil.getOutputField().contains("token")){
				OutputUtil.setValue("token", data.get("token"));
			}
		}
	}
}