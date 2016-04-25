package com.creditease.xyuan.httpTest.Protocol.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.creditease.xyuan.httpTest.Protocol.ISocketProtocol;
import com.creditease.xyuan.httpTest.Util.MyLog;
import com.creditease.xyuan.httpTest.Util.PropUtil;
import com.creditease.xyuan.httpTest.Util.SocketUtil;

public class SocketJsonProtocol implements ISocketProtocol{
	private Selector selector = null;
	private SocketChannel socketChannel = null;
	private static MyLog loger = MyLog.getLoger();
	
	public void connect() throws Exception {
		SocketUtil.connect();
		selector = SocketUtil.getSelector();
		socketChannel = SocketUtil.getSocketChannel();
	}
	
	public  void  closeConn() throws IOException{
		SocketUtil.closeConn();
    }

	public String getData() throws Exception {
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
	
	public void sendData(String data) throws Exception {
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
