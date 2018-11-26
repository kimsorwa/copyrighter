package com.kh.boardcomment.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.boardcomment.model.dao.BoardCommentDao;
import com.kh.boardcomment.model.vo.BoardComment;


public class BoardCommentService {
	

	private BoardCommentDao bcDao = new BoardCommentDao();
	
	public int insertComment(BoardComment bc) {
		
		Connection con = getConnection();
		
		int result = bcDao.insertComment(con, bc);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public BoardComment selectOne(int cid) {
		Connection con = getConnection();
		
		BoardComment bco = bcDao.selectOne(con, cid);
		
		close(con);
		
		return bco;
	}
	
	public ArrayList<BoardComment> selectList(int bid){

		Connection con = getConnection();
		
		ArrayList<BoardComment> clist = bcDao.selectList(con, bid);
		
		close(con);
		
		return clist;
		
	}

	public int updateComment(BoardComment bc) {
		Connection con = getConnection();
		
		int result = bcDao.updateComment(con, bc);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public int deleteComment(int cid) {
		Connection con = getConnection();
		
		int result = bcDao.deleteComment(con, cid);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
}
