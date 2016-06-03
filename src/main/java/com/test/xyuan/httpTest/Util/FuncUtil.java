package com.test.xyuan.httpTest.Util;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Random;

public class FuncUtil {
	static String timestamp  = null;
	
	public static String getString(){
		return "test";
	}
	
	public static int getInt(){
		return 159;
	}
	
	public static String getTimestamp(){
		timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		return timestamp;
	}
	
	public static String getRandomNumber(String length){
		String base = "0123456789";     
		int len = Integer.parseInt(length);
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < len; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }
	    return sb.toString();
	}
	
	public static String getRandomMac(){
		String base = "ABCDEF1234567890";
	    Random random = new Random();     
	    int number = 0;
	    StringBuffer sb = new StringBuffer();  
	    for(int i=0;i<5;i++){
	    	number = random.nextInt(base.length());     
	        sb.append(base.charAt(number)); 
	    	number = random.nextInt(base.length());     
	        sb.append(base.charAt(number)); 
	        sb.append(":");
	    }
    	number = random.nextInt(base.length());     
        sb.append(base.charAt(number)); 
    	number = random.nextInt(base.length());     
        sb.append(base.charAt(number)); 
        
	    return sb.toString();
	}
	
	public static String getSign(){
		String sign = "test.bdapiplat" + "210.22.89.58d27d320c27c3033b7883xg6XBXvCLEA673ya8d77test@sohu.com5ekl" + timestamp;
    	
		return sha1(sign);
	}
	
	private static String sha1(String strSrc) {
	    MessageDigest md=null;
	    String strDes=null;
	 
	    byte[] bt=strSrc.getBytes();
	    try {
             md=MessageDigest.getInstance("SHA-1");
             md.update(bt);
             strDes=bytes2Hex(md.digest());  //to HexString
            }catch (Exception e) {
                    System.out.println("Invalid algorithm.");
                    return null;
            }
        	return strDes.toUpperCase();
        }
 
    private static String bytes2Hex(byte[]bts) {
    	String des="";
    	String tmp=null;
    	for (int i=0;i<bts.length;i++) {
    		tmp=(Integer.toHexString(bts[i] & 0xFF));
    		if (tmp.length()==1) {
    			des+="0";
            }
            des+=tmp;
        }
        return des;
    }
    
    public static void main(String[] args){
    	String time = "1458633734743";
    	String sign = "test.bdapiplat" + "210.22.89.58d27d320c27c3033b7883xg6XBXvCLEA673ya8d77test@sohu.com5ekl" + time;
    	
    	System.out.println( FuncUtil.sha1(sign));
    }
	
}
