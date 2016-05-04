package com.creditease.xyuan.httpTest.Helper;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.creditease.xyuan.httpTest.DAO.ServerDAO;
import com.creditease.xyuan.httpTest.Util.MyLog;

public class DatabaseHelper {
	public static MyLog loger = MyLog.getLoger();
	private static SqlSession session = null;
	private static ServerDAO serverDao = null;
	private static SqlSessionFactory sessionFactory = null;
	
	public static void  main(String[] args){
//		DatabaseHelper.newReports(2, "http", "devsave", "",new Timestamp((new Date()).getTime()) , new Timestamp((new Date()).getTime()), 111, "200", "success");
		System.out.println(DatabaseHelper.getMaxRound());
	}
	
	
	static{
		String resource = System.getProperty("user.dir") + "\\config\\config.xml";
		Reader is;
		try {
			is = new FileReader(resource);
	        sessionFactory = new SqlSessionFactoryBuilder().build(is);
	        session = sessionFactory.openSession();
	        serverDao = session.getMapper(ServerDAO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void reSession(){
		session.close();
        session = sessionFactory.openSession();
        serverDao = session.getMapper(ServerDAO.class);
	}
	
	public static int getMaxRound(){
		int res = serverDao.getMaxRound();
		session.commit();
		reSession();
		return res;
	}
	
	public  static void updateRunReports(int round,int apitotal,int success,int fail,int notrun){
		serverDao.updateRunReports(round, apitotal, success, fail, notrun);
		session.commit();
	}
	
	public static void newRunReports(int round){
		serverDao.newRunReports(round);
		session.commit();
	}
	
	public static void newReports(int round,String apitype,String apiname,String message,Timestamp starttime,
			Timestamp endtime,String exectime,String responsecode,String status,String sequencename,String index,String casetype){
		serverDao.newReports(round, apitype, apiname, message, starttime, endtime, exectime, responsecode, status,sequencename,index,casetype);
		session.commit();
	}
	
}
