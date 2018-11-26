package com.kh.likeit.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.likeit.model.dao.LikeitDao;
import com.kh.likeit.model.vo.Likeit;

public class LikeitService {
	
	LikeitDao lDao = new LikeitDao();
	
	public int checkLikeit(int mid, int bid) {
		
		Connection con = getConnection();
		
		int result = lDao.checkLikeit(con, mid, bid);
		
		close(con);
		
		return result;
	}

	public int switchLikeit(int mid, int bid, int wid, String mname, String btitle) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		if(lDao.checkLikeit(con, mid, bid) > 0) {
			result = lDao.deleteLikeit(con, mid, bid) * 1;
		} else {
			result = lDao.insertLikeit(con, mid, bid) * 2;
			lDao.insertAlarm(con, mid, wid, mname, btitle);
		}
		
		if(result > 0) commit(con);
		else rollback(con);
			
		close(con);
		
		return result;

	}

	public ArrayList<Likeit> searchLikeit(int mid) {
		Connection con = getConnection();
		ArrayList<Likeit> list = lDao.searchLikeit(con, mid);

		close(con);
		
		return list;
	}

}
