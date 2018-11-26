package com.kh.board.common.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.common.model.vo.Board;
import com.kh.board.project.model.dao.ProjectDao;
import com.kh.board.project.model.vo.Project;

import static com.kh.common.JDBCTemplate.*;

public class BoardDao {

	private Properties prop = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public BoardDao() {
		String filePath = ProjectDao.class.getResource("/config/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));		
		} catch (IOException e) {			
			e.printStackTrace();			
		}
	}

	public ArrayList<Board> searchAll(Connection con, String keyword) {
		
		ArrayList<Board> bList = null;
		
		String sql = prop.getProperty("searchAll");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			pstmt.setString(4, keyword);
			pstmt.setString(5, keyword);
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			while(rset.next()){
				Board b = new Board();
				
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBid(rset.getInt("BID"));
				b.setBrcount(rset.getInt("BRCOUNT"));
				b.setBstatus(rset.getString("BSTATUS"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBtype(rset.getInt("BTYPE"));
				b.setBwriter(rset.getInt("BWRITER"));
				
				b.setjTag(rset.getString("JTAG"));
				b.setgTag(rset.getString("GTAG"));
		
				b.setLikeCnt(rset.getInt("LIKECNT"));
				b.setCommCnt(rset.getInt("COMMCNT"));
				b.setFName(rset.getString("FNAME"));
				b.setMname(rset.getString("MNAME"));
				b.setMprofile(rset.getString("MPROFILE"));
				
				bList.add(b);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return bList;
	}
	
}
