package com.test.xyuan.httpTest.processImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import org.dom4j.Element;

import com.test.xyuan.httpTest.Helper.AssertHelper;
import com.test.xyuan.httpTest.Helper.ConfigHelper;
import com.test.xyuan.httpTest.Helper.DataHelper;
import com.test.xyuan.httpTest.Helper.PublicDataHelper;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.SocketUtil;
import com.test.xyuan.httpTest.object.ConfigData;
import com.test.xyuan.httpTest.process.IExecute;

public class socketProcesser implements IExecute{
	private Selector selector = null;
	private SocketChannel socketChannel = null;
	private static MyLog loger = MyLog.getLoger();

	public void execute(Element config,boolean bAssert) throws Exception {
		String response = null;
		ConfigData cd = null;
		DataHelper dh = null;
		
		try{
			loger.info("开始执行SOCKET处理流程");
			//配置数据
			loger.info("取得配置数据数据" + PublicDataHelper.getIns().getCasedata().getModelName());
			cd = ConfigHelper.getConfigData(config);
			
			//业务数据
			loger.info("取得业务数据:" + PublicDataHelper.getIns().getCasedata().getCaseName());
			dh = new DataHelper();
			String body = dh.getJsonBody();
			
			System.out.println(body);
			
			loger.info("执行SOCKET请求");
			connect();
			sendData(body);
			response = getData();
			
			System.out.println(response);
			
			//验证
			loger.info("结果验证");
			if(bAssert == true)
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

	
	private void connect() throws Exception {
		SocketUtil.connect();
		selector = SocketUtil.getSelector();
		socketChannel = SocketUtil.getSocketChannel();
	}
	
	private  void  closeConn() throws IOException{
		SocketUtil.closeConn();
    }

	private String getData() throws Exception {
		loger.info("接收数据并返回，10秒内检查缓存区内容，防止服务端响应不及时");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
        String receiveData = null;
        
        while (keyIterator.hasNext()){
        	SelectionKey key = keyIterator.next();
	        boolean flag = true;
	        int times = 0;
	        
	        SocketChannel socketChannel = (SocketChannel) key.channel();
	        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
	        byte[] bytes;  
	        int count = 0;  

	        while(flag){
	        	try {
	                count = socketChannel.read(buffer);

	                if(count > 0){
	                	buffer.flip();  
	                	bytes = new byte[count];  
	                    buffer.get(bytes);  
	                    baos.write(bytes); 
	                    buffer.clear();
	                }
	                else{
	                	if(baos.size() > 0){
	                		receiveData = baos.toString();
	                		loger.info("接收到数据" + receiveData);
	                		flag = false;	                			
	                	}
	                	else{
	                		if(times == 10)
	                			flag = false;
	                		else{
	                			Thread.sleep(1000);
	                			times++;
	                		}
	                	}
	                }
	        	} catch (Exception e) {
	                break;
	            }
	        }
			try{
				baos.close();
			}catch(Exception ex){}
        }
        
        return receiveData;
    }
	
	private void sendData(String data) throws Exception {
		loger.info("发送 数据:"+data);
		ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());  
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()){
        	SelectionKey key = keyIterator.next();
        
	        SocketChannel channel = (SocketChannel) key.channel();
	        if (channel.isConnected() && channel.socket().isConnected()){
		        while(buffer.hasRemaining()){
		        	channel.write(buffer); 
		        }
	        }
	        buffer.clear();
        }
	}
	
	
}
