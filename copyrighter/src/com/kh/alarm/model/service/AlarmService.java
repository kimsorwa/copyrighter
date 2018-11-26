package com.kh.alarm.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.alarm.model.dao.AlarmDao;
import com.kh.alarm.model.vo.Alarm;

import static com.kh.common.JDBCTemplate.*;
public class AlarmService {
	
	private AlarmDao aDao = new AlarmDao();
	
	public int countUnReadAlarm(int mid) {
		// 
		Connection con = getConnection();
		int unReadAlarm = aDao.countUnReadAlarm(con, mid);
		
		close(con);
		
		return unReadAlarm;
	}

	public ArrayList<Alarm> selectAlarmList(int mid) {
		// 
		Connection con = getConnection();
		ArrayList<Alarm> alarmList = aDao.selectAlarmList(con, mid);
		
		//System.out.println("selectAlarmList s : "+ alarmList);
		
		close(con);
		return alarmList;
	}

	public int updateAlarm(int aid) {
		
		Connection con = getConnection();
		
		int result = aDao.updateAlarm(con, aid);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	
	
	
	

}
