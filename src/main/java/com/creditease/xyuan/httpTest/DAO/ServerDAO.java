package com.creditease.xyuan.httpTest.DAO;

import java.sql.Timestamp;
import org.apache.ibatis.annotations.Param;

public interface ServerDAO {
	public int getMaxRound();
	
	public void updateRunReports(@Param("round")int round,@Param("apitotal")int apitotal,
			@Param("success")int success,@Param("fail")int fail,@Param("notrun")int notrun);
	
	public void newRunReports(@Param("round")int round);
	
	public void newReports(@Param("round")int round,@Param("apitype")String apitype,@Param("apiname")String apiname,
			@Param("message")String message,@Param("starttime")Timestamp starttime,@Param("endtime")Timestamp endtime,
			@Param("exectime")String exectime,@Param("responsecode")String responsecode,@Param("status")String status,
			@Param("sequencename")String sequencename,@Param("index")String index,@Param("casetype")String casetype);
	
}
