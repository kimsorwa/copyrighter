package com.kh.follow.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.alarm.model.vo.Alarm;
import com.kh.follow.model.vo.Follow;

public class FollowDao {
	
	private Properties prop = new Properties();
	
	public FollowDao() {
		
		String filePath = FollowDao.class.getResource("/config/follow-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));		
		} catch (IOException e) {			
			e.printStackTrace();			
		}
	}
	

	public int checkFollow(Connection con, String wid, String mid) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = prop.getProperty("isFollow");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, wid);

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

	public int deleteFollow(Connection con, String wid, String mid) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteFollow");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, wid);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int insertFollow(Connection con, String wid, String mid) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertFollow");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, wid);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}


	public ArrayList<Follow> selectFollowingList(Connection con, int mid, int mpid) {
		ArrayList<Follow> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("viewFollow2");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.setInt(2, mpid);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Follow>();
			
			while(rset.next()){
				
				Follow f = new Follow();
				
				f.setFollowid(rset.getInt("followid"));
				f.setFollowname(rset.getString("mname"));
				f.setChk(rset.getString("chk"));

				list.add(f);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public ArrayList<Follow> selectFollowerList(Connection con, int mid, int mpid) {
		ArrayList<Follow> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	
		String sql = prop.getProperty("viewFollower2");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.setInt(2, mpid);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Follow>();
			
			while(rset.next()){
				
				Follow f = new Follow();
				
				f.setFollowerid(rset.getInt("followerid"));
				f.setFollowername(rset.getString("mname"));
				f.setChk(rset.getString("chk"));
				
				list.add(f);
			} 
			
		} catch (SQLException e){
				e.printStackTrace();
		
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
			
		return list;
	}


	public void insertAlarm(Connection con, String wid, String mname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("insertAlarm");

		try {
			pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, Integer.parseInt(wid));
				String msg = mname +" 님이 팔로우했습니다.";
				pstmt.setString(2, msg);
				
				int result = pstmt.executeUpdate();
				System.out.println("result: " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
	}
}
	