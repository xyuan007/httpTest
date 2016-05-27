package com.creditease.xyuan.httpTest.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class Test {

	public static void genDetailReport() throws Exception{
	    HashMap<String, Object> parameter =  new HashMap<String, Object>();   
	    parameter.put( "status", "success" );  
		JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Administrator.20160229-115937\\Desktop\\test_subreport.jrxml");  
		JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameter,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Administrator.20160229-115937\\Desktop\\success.html");
	}
	
	public static void genMainReport() throws Exception{
		JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Administrator.20160229-115937\\Desktop\\test.jrxml");  
	    HashMap<String, Object> parameter =  new HashMap<String, Object>(); 
		parameter.put( "round", "1" );  
	    parameter.put( "day", "2016-05-04" );  
		JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameter,getConnection()); 
		JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Administrator.20160229-115937\\Desktop\\Report.html");
	}
	
	public static void  main(String[] args) throws Exception{
		genMainReport();
	}
	
    private static Connection getConnection () throws SQLException {   
        	DriverManager.registerDriver(    
           new com.mysql.jdbc.Driver());     
        	return DriverManager.getConnection( url, user, pw ); 
     }   
         
     private static String url = "jdbc:mysql://localhost:3306/appiumserver";   
     private static String user = "root";   
     private static String pw   = "root"; 
}
