package com.kh.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;
import static com.kh.common.JDBCTemplate.close;

public class MemberDao {
	
	private Properties prop = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public MemberDao(){
		
		String filePath = MemberDao.class.getResource("/config/member-query.properties").getPath();
		
		try {
		
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

	public int signUpMember(Connection con, Member m) {
		
		int result = 0;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemail());
			pstmt.setString(2, m.getMpwd());
			pstmt.setString(3, m.getMname());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
		
	}

	public Member selectMember(Connection con, Member m) {
		
		Member result = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemail());
			pstmt.setString(2, m.getMpwd());
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				
				result = new Member();
				
				result.setMid(rset.getInt(1));
				result.setMprofile(rset.getString(2));
				result.setMemail(rset.getString(3));
				result.setMpwd(rset.getString(4));
				result.setMname(rset.getString(5));
				result.setMdate(rset.getDate(6));
				result.setMhodu(rset.getInt(7));
				result.setMsid(rset.getInt(8));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return result;
		
	}
	
	public Member selectMember(Connection con, int mid) {
		
		Member result = null;
		
		String sql = prop.getProperty("selectMemberbyMid");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				
				result = new Member();
				
				result.setMid(rset.getInt(1));
				result.setMprofile(rset.getString(2));
				result.setMemail(rset.getString(3));
				result.setMpwd(rset.getString(4));
				result.setMname(rset.getString(5));
				result.setMdate(rset.getDate(6));
				result.setMhodu(rset.getInt(7));
				result.setMsid(rset.getInt(8));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return result;
		
	}

	public int selectEmail(Connection con, Member m) {

		int result = 0;
		
		String sql = prop.getProperty("selectEmail");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getMname());
			pstmt.setString(2, m.getMemail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
		
	}

	public int updateRandomPwd(Connection con, String tempPassword, Member m) {
		
		int result = 0;
		
		String sql = prop.getProperty("updatePwd");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, tempPassword);
			pstmt.setString(2, m.getMemail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
		
	}

	public int updateMember(Connection con, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getMpwd());
			pstmt.setString(2, m.getMname());
			pstmt.setInt(3, m.getMid());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {			
			close(pstmt);
		}
		
		return result;

	}

	public int deleteMember(Connection con, String memail) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memail);

			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		
			e.printStackTrace();

		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberStatus(Connection con, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMemberStatus");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, m.getMsid());
			pstmt.setInt(2, m.getMid());
	
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pstmt);			
		}
		
		return result;
		
	}

	public ArrayList<Member> searchMember(Connection con, String condition, String keyword) {
		ArrayList<Member> mlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "name" :
			sql = prop.getProperty("mSearchByName"); break;
		case "email" :
			sql = prop.getProperty("mSearchByEmail"); break;
		}
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			mlist = new ArrayList<Member>();
			
			while(rset.next()){
				
				Member m = new Member();
				
				m.setMid(rset.getInt("mid"));
				m.setMprofile(rset.getString("mprofile"));
				m.setMemail(rset.getString("memail"));
				m.setMpwd(rset.getString("mpwd"));
				m.setMname(rset.getString("mname"));
				m.setMdate(rset.getDate("mdate"));
				m.setMhodu(rset.getInt("mhodu"));
				m.setMsid(rset.getInt("msid"));
				m.setMstatus(rset.getString("mstatus"));
		
				mlist.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mlist;
	}

	public ArrayList<Member> selectList(Connection con) {
		ArrayList<Member> mlist = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			mlist = new ArrayList<Member>();
			
			while(rset.next()){
				
				Member m = new Member();
				
				m.setMid(rset.getInt("mid"));
				m.setMprofile(rset.getString("mprofile"));
				m.setMemail(rset.getString("memail"));
				m.setMpwd(rset.getString("mpwd"));
				m.setMname(rset.getString("mname"));
				m.setMdate(rset.getDate("mdate"));
				m.setMhodu(rset.getInt("mhodu"));
				m.setMsid(rset.getInt("msid"));
				m.setMstatus(rset.getString("mstatus"));
				
				mlist.add(m);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return mlist;
	}


	public ArrayList<Member> selectMypage(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateProfile(Connection con, Member m) {
		
		int result = 0;
		
		String sql = prop.getProperty("updateProfile");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getMprofile());
			pstmt.setInt(2, m.getMid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {			
			close(pstmt);			
		}
		
		return result;
	}

	public void insertAlarm(Connection con, int mid) {

		String sql = prop.getProperty("insertAlarmMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
	}
	
}

