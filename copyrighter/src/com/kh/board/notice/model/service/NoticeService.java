package com.kh.board.notice.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.notice.model.dao.NoticeDao;
import com.kh.board.notice.model.vo.Notice;

public class NoticeService {
	
	private NoticeDao nDao = new NoticeDao();

	public int insertNotice(Notice n) {
		Connection con = getConnection();
		
		int result = 0;
		
		System.out.println("n : " + n);
		
		int bid = nDao.selectCurrentBid(con);
		
		int result1 = nDao.insertBoardContent(con, n, bid);
		
		
		if( result1 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result*bid;
	}

	public Notice selectOne(int bid) {
		
		Connection con = getConnection();
		int result = 0;
		
		Notice n = nDao.selectOne(con, bid);
		
		if( n != null ){
			result = nDao.updateCount(con, bid);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		return n;
	}

	public int updateNotice(Notice n) {
		Connection con = getConnection();
		
		int result = 0;
		
		int result1 = nDao.updateBoard(con, n);
		
		if( result1 > 0 ) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Notice> selectNoticeList() {
		// 
		Connection con = getConnection();
		ArrayList<Notice> noticeList = null;
		
		noticeList = nDao.selectNoticeList(con);
		
		close(con);
		
		return noticeList;
	}
	
	public int deleteNotice(int bid) {
		
		Connection con = getConnection();
		
		int result = nDao.deleteNotice(con, bid);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Notice> searchMywork(int mid) {
		
		Connection con = getConnection();
		ArrayList<Notice> list = nDao.selectMywork(con, mid);
		
		close(con);
		
		return list;

	}

}
