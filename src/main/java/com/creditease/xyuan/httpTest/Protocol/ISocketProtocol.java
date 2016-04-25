package com.creditease.xyuan.httpTest.Protocol;

public interface ISocketProtocol {
	public void connect() throws Exception;
	
	public String  getData() throws Exception;
	
	public void  sendData(String data) throws Exception;
	
	public void  closeConn() throws Exception;
	
}
