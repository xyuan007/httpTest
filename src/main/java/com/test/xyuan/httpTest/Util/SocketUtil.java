package com.test.xyuan.httpTest.Util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketUtil {
	private static MyLog loger = MyLog.getLoger();
	static private Selector selector = null;
	static private SocketChannel socketChannel = null;
	
	public static SocketChannel getSocketChannel(){
		return socketChannel;
	}
	
	public static Selector getSelector(){
		return selector;
	}
	
	public static void connect() throws Exception {
		if(socketChannel != null && socketChannel.isConnected()){
			loger.info("连接仍存在，重用连接");
            return;
		}
		
		loger.info("建立新连接");
		selector = Selector.open();
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		SocketAddress socketAddress = new InetSocketAddress(PropUtil.getSocketIP(), PropUtil.getSocketPort()); 
		socketChannel.socket().setKeepAlive(true);
		socketChannel.connect(socketAddress);   
		socketChannel.register(selector, SelectionKey.OP_READ | 
				SelectionKey.OP_WRITE  | SelectionKey.OP_CONNECT); 
        for (int i = 0; i < 10; i++) {
            if (socketChannel.finishConnect()) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }
        
        if(socketChannel.isConnected()){
            selector.select();
        }
        else
        	throw new Exception("无法建立SOCKET连接");
	}
	
	public static void  closeConn() throws IOException{
		loger.info("关闭当前连接");
        if(socketChannel.isConnected())
            socketChannel.close();  
        socketChannel = null;
        selector = null;
    }
	
}
