package com.kh.follow.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.follow.model.dao.FollowDao;
import com.kh.follow.model.vo.Follow;
import com.kh.member.model.vo.Member;
import com.kh.spon.model.vo.Spon;

public class FollowService {

	FollowDao fDao = new FollowDao();
	
	public int checkFollow(String wid, String mid) {
		
		Connection con = getConnection();
		
		int result = fDao.checkFollow(con, wid, mid);
		
		close(con);
		
		return result;
	}

	public int switchFollow(String wid, String mid, String mname) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		if(fDao.checkFollow(con, wid, mid) > 0) {
			result = fDao.deleteFollow(con, wid, mid) * 1;
		} else {
			result = fDao.insertFollow(con, wid, mid) * 2;
			// mid가 wid를 팔로우 하면 wid한테 alarm 인서트
			fDao.insertAlarm(con, wid, mname);
		}
		
		if(result > 0) commit(con);
		else rollback(con);
			
		
		close(con);
		
		return result;

	}

	public ArrayList<Follow> searchFollowing(int mid, int mpid) {
		
		Connection con = getConnection();
		ArrayList<Follow> list = fDao.selectFollowingList(con, mid, mpid);
		
		close(con);
		
		return list;
		
	}

	public ArrayList<Follow> searchFollower(int mid, int mpid) {
		
		ArrayList<Follow> list = null;
		Connection con = getConnection();
		
		list = fDao.selectFollowerList(con, mid, mpid);
		
		close(con);
		
		return list;


	}

}
