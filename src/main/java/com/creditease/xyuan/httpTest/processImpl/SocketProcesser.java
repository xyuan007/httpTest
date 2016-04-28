package com.creditease.xyuan.httpTest.processImpl;

import org.dom4j.Element;
import com.creditease.xyuan.httpTest.Helper.AssertHelper;
import com.creditease.xyuan.httpTest.Helper.ConfigHelper;
import com.creditease.xyuan.httpTest.Helper.DataHelper;
import com.creditease.xyuan.httpTest.Helper.PublicDataHelper;
import com.creditease.xyuan.httpTest.Protocol.ISocketProtocol;
import com.creditease.xyuan.httpTest.Protocol.impl.SocketJsonProtocol;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.object.ConfigData;
import com.creditease.xyuan.httpTest.process.IExecute;

public class SocketProcesser implements IExecute{
	private static MyLog loger = MyLog.getLoger();
	public void execute(Element config) throws Exception {
		String response = null;
		ISocketProtocol socket = null;
		ConfigData cd = null;
		DataHelper dh = null;
		
		try{
			loger.info("开始执行SOCKET处理流程");
			//配置数据
			loger.info("取得配置数据数据" + PublicDataHelper.getInstance().getCasedata().getModelName());
			cd = ConfigHelper.getConfigData(config);
			
			//业务数据
			loger.info("取得业务数据:" + PublicDataHelper.getInstance().getCasedata().getCaseName());
			dh = new DataHelper();
			String body = dh.getJsonBody();
			
			System.out.println(body);
			
			//执行类
			if(cd.getProtocol().equals("socketjson")){
				socket = new SocketJsonProtocol();
			}
			
			loger.info("执行SOCKET请求");
			socket.connect();
			socket.sendData(body);
			response = socket.getData();
			
			System.out.println(response);
			
			//验证
			loger.info("结果验证");
			AssertHelper.asserting(response);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}finally{
			//清理数据
			dh = null;
			cd = null;
		}
	}
}
