package com.test.xyuan.httpTest.Helper;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.test.xyuan.httpTest.DAO.ServerDAO;
import com.test.xyuan.httpTest.Util.MyLog;
import com.test.xyuan.httpTest.Util.ProjectPropUtil;

public class DatabaseHelper {
	public static MyLog loger = MyLog.getLoger();
	private static SqlSession session = null;
	private static ServerDAO serverDao = null;
	private static SqlSessionFactory sessionFactory = null;
	
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
	
	public static int getMaxRound(String proName){
		int res = serverDao.getMaxRound(proName);
		session.commit();
		reSession();
		return res;
	}
	
	public  static void updateRunReports(int round,int apitotal,int success,int fail,int notrun,String projectname){
		serverDao.updateRunReports(round, apitotal, success, fail, notrun,projectname);
		session.commit();
	}
	
	public static void newRunReports(int round){
		serverDao.newRunReports(ProjectPropUtil.getProjectName(),round);
		session.commit();
	}
	
	public static void newReports(int round,String apitype,String apiname,String message,Timestamp starttime,
			Timestamp endtime,String exectime,String responsecode,String status,String sequencename,String index,
			String casetype,String projectname,String requesturl,String requestdata,String responsedata){
		serverDao.newReports(round, apitype, apiname, message, starttime, endtime, 
				exectime, responsecode, status,sequencename,index,casetype,projectname,
				requesturl,requestdata,responsedata);
		session.commit();
	}
	
	public static void newLog(int round,String projectName,String logInfo){
		serverDao.newLog(round, projectName, logInfo);
		session.commit();
	}
	
}
