package com.test.xyuan.httpTest.Util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.xyuan.httpTest.Helper.PublicDataHelper;

public class FuncUtil {
//	private static MyLog loger = MyLog.getLoger();
	static String timestamp  = null;
	
	static public void AssertForTimeout(String expected,String response,String className){
		String temp = "{\"message\":\"此次请求没有查询到相应内容\",\"code\":1001}";		
		if(temp.equals(response)){
			//超时的话
			if(PublicDataHelper.getIns().getCycleFlag().equals("timeout")){
				org.junit.Assert.fail(className+"超时未返回正确数据");
			}
		}
		else{
			//拿到结果，退出循环
			PublicDataHelper.getIns().setCycleFlag("false");
			assertThat(chopString(response),equalTo(chopString(expected)));
		}
	}
	
	public static String getOutputValue(String key){
		String res = (String) PublicDataHelper.getIns().getOutput().getValue(key);
		System.out.println("key:::::::"+key+"   value:::::::::" + res);
		return res;
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
    
    public static String chopString(String input){
    	return removeUUID(remove_ID(input));
    }
    
    //去掉UUID
    public static String removeUUID(String input){
    	Pattern pattern = Pattern.compile("[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(input);
    	String string = matcher.replaceAll("");
    	return  string;
    }
    
    
    //去提ID和GUID
    public static String remove_ID(String input){
    	Pattern pattern = Pattern.compile("\"_id\":(\\d+),", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(input);
    	
    	String string = matcher.replaceAll("");

    	pattern = Pattern.compile("\"guid\":\"([0-9a-zA-Z]{32})\",", Pattern.DOTALL);
    	matcher = pattern.matcher(string);
    	string  = matcher.replaceAll("");
    	
    	return  string;
    }
    
    
    public static void main(String[] args){

    }
	
}
