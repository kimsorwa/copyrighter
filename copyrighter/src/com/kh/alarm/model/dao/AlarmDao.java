package com.kh.alarm.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.alarm.model.vo.Alarm;

import static com.kh.common.JDBCTemplate.*;

public class AlarmDao{
	
	private Properties prop = new Properties();
	private PreparedStatement pstmt = null;
	
	public AlarmDao(){
		String filePath = AlarmDao.class.getResource("/config/alarm-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));		
		} catch (IOException e) {			
			e.printStackTrace();			
		}
	}
	
	public int countUnReadAlarm(Connection con, int mid) {
		// 
		int unReadAlarm = 0;
		ResultSet rset = null;
		
		String sql = prop.getProperty("unReadAlarm");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				unReadAlarm = Integer.parseInt(rset.getString(1));
				
				
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return unReadAlarm;
	}


	public ArrayList<Alarm> selectAlarmList(Connection con, int mid) {
		// 
		ArrayList<Alarm> alarmList = null;
		ResultSet rset = null;
		String sql = prop.getProperty("alarmList");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			
			rset = pstmt.executeQuery();
			
			alarmList = new ArrayList<Alarm>();
			//
			//System.out.println(rset.next());
			while(rset.next()){
				Alarm al = new Alarm();
				
				al.setAid(rset.getInt("AID"));
				al.setMid(rset.getInt("MID"));
				al.setAMsg(rset.getString("AMSG"));
				al.setADate(rset.getDate("ADATE"));
				al.setAFlag(rset.getString("AFLAG"));
				
				
				
				alarmList.add(al);
				//System.out.println("selectAlarmList dao : "+ alarmList);
			}
			
		} catch (SQLException e) {
		 
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return alarmList;
	}

	public int updateAlarm(Connection con, int aid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateAlarm");
		//System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			//System.out.println(sql);
			pstmt.setInt(1, aid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	
	}

}
