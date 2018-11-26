package com.kh.likeit.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.gallery.model.dao.GalleryDao;
import com.kh.follow.model.vo.Follow;
import com.kh.likeit.model.vo.Likeit;

public class LikeitDao {

	private Properties prop = new Properties();
	
	public LikeitDao() {
		
		String filePath = LikeitDao.class.getResource("/config/likeit-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));		
		} catch (IOException e) {			
			e.printStackTrace();			
		}
	}
	

	public int checkLikeit(Connection con, int mid, int bid) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = prop.getProperty("isLikeit");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			pstmt.setInt(2, bid);

			rset = pstmt.executeQuery();
			
			if(rset.next()) result = rset.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int deleteLikeit(Connection con, int mid, int bid) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteLikeit");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			pstmt.setInt(2, bid);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int insertLikeit(Connection con, int mid, int bid) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertLikeit");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			pstmt.setInt(2, bid);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}


	public ArrayList<Likeit> searchLikeit(Connection con, int mid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Likeit> list = null;
		
		String sql = prop.getProperty("selectLikeit");
		
		try {
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, mid);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Likeit>();
			
			while(rset.next()){
				
				Likeit l = new Likeit();
				
				l.setMid(rset.getInt("mid"));
				l.setBid(rset.getInt("bid"));
				l.setBwriter(rset.getString("bwriter"));
				l.setBtitle(rset.getString("btitle"));
				l.setFname(rset.getString("fname"));
				l.setMname(rset.getString("mname"));
				
				list.add(l);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
			
		}
		
		return list;
	}


	public void insertAlarm(Connection con, int mid, int wid, String mname, String btitle) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("insertAlarm");

		try {
			pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, wid);
				String msg = mname +" 님이 회원님의 게시물 '" + btitle + "'(을)를 좋아합니다.";
				pstmt.setString(2, msg);
			
			
			int result = 0;
			result += pstmt.executeUpdate();
			System.out.println("result : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
	}



}
