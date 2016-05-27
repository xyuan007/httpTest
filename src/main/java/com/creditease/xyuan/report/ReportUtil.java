package com.creditease.xyuan.report;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.creditease.xyuan.httpTest.Util.PropUtil;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportUtil {

	
	
	private static Connection conn = null;
	
	public static void main(String[] args) throws Exception{
//		System.out.println(getCurrentReportDir());
		mailTo();
	}
	
	 public static String encode(String bstr) throws Exception{    
		   return new sun.misc.BASE64Encoder().encode(bstr.getBytes());    
	 }  
	
	public static void mailTo() throws Exception{
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.creditease.cn");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.secureConnection", "true");
		
//	    MailAuthenticator authenticator = new MailAuthenticator(encode("xuqingliang@creditease.cn"),encode("qweqwe111!!!"));
	    MailAuthenticator authenticator = new MailAuthenticator("xuqingliang@creditease.cn","qweqwe111!!!");
	    // 创建session
	    Session session = Session.getInstance(props, authenticator);
	    
	    MimeMessage message = new MimeMessage(session);
	    // 设置发信人
	    message.setFrom(new InternetAddress(authenticator.getUsername()));
	    // 设置收件人
	    message.setRecipient(RecipientType.TO, new InternetAddress("xuqingliang@creditease.cn"));
	    // 设置主题
	    message.setSubject("subject");
	    // 设置邮件内容
	    message.setContent("testing", "text/html;charset=utf-8");
	    // 发送
	    Transport.send(message);
	}
	
	public static void genDetailReport() throws Exception{
		JasperReport jasperReport = JasperCompileManager.compileReport(getReportConfigDir() + "detail.jrxml");  
		
		HashMap<String, Object> parameter =  new HashMap<String, Object>();   
	    parameter.put( "status", "success" );  
		JasperPrint jpSuccess = JasperFillManager.fillReport( jasperReport,parameter,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jpSuccess, getCurrentReportDir() + "Success.html");
	
		parameter.clear();
	    parameter.put( "status", "failure" );  
		JasperPrint jpFailure = JasperFillManager.fillReport( jasperReport,parameter,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jpFailure, getCurrentReportDir() + "Failure.html");
	    
		parameter.clear();
	    parameter.put( "status", "notrun" );  
		JasperPrint jpNotrun = JasperFillManager.fillReport( jasperReport,parameter,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jpNotrun, getCurrentReportDir() + "Notrun.html");
	}
	
	public static void genMainReport() throws Exception{
		JasperReport jasperReport = JasperCompileManager.compileReport(getReportConfigDir() + "test.jrxml");  
		JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,null,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jasperPrint, getCurrentReportDir() + "Report.html");
	}
	
	private static String getCurrentReportDir(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String parDir = String.format("%s\\report\\report\\%s\\",System.getProperty("user.dir") ,sdf.format(new Date()));
		return parDir;
	}
	
	private static String getReportConfigDir(){
		return String.format("%s\\report\\config\\",System.getProperty("user.dir"));
	}
	
    private static Connection getConnection () throws Exception {  
    	if(conn != null)
    		return conn;
    	
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());     
        conn = DriverManager.getConnection( PropUtil.getMysqlUrl(),PropUtil.getMysqlUserName(),PropUtil.getMysqlPassword() );   
        return conn;
    }  
}
