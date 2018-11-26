package com.kh.ajax.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.payment.model.vo.Payment;
import com.kh.spon.model.vo.Spon;

import static com.kh.common.JDBCTemplate.*;

public class Dao {

	private Properties prop = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public Dao(){
		
		String filePath = Dao.class.getResource("/config/ajax-query.properties").getPath();
		
		try {
		
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public int checkNickName(Connection con, String nickName) {
		
		int result = 0;
		
		String sql = prop.getProperty("checkNickName");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, nickName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public int checkEmail(Connection con, String email) {

		int result = 0;
		
		String sql = prop.getProperty("checkEmail");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
		
	}

	public String searchNickName(Connection con, String email) {
		
		String result = null;

		String sql = prop.getProperty("searchNickName");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				result = rset.getString(1);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(pstmt);

		}

		return result;

	}

	public int insertHodu(Connection con, Member m, Payment p) {
		
		int resultAll = 0;
		
		String sql = prop.getProperty("insertHodu");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p.getPmoney());
			pstmt.setInt(2, p.getMid());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				String sql2 = prop.getProperty("updateHodu");
				
				PreparedStatement pstmt2 = null;
				
				pstmt2 = con.prepareStatement(sql2);
				
				pstmt2.setInt(1, m.getMhodu());
				pstmt2.setInt(2, p.getMid());
				
				resultAll = pstmt2.executeUpdate();
				
				close(pstmt2);
				
			} else {
				
				System.out.println("호두 충전 실패");
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return resultAll;
		
	}
	
	public int spon(Connection con, Spon s, int hoduId) {

		int resultAll = 0;
		
		String sql = prop.getProperty("insertSpon");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, hoduId);
			pstmt.setInt(2, s.getSgiverid());
			pstmt.setString(3, s.getSreceivername());
			
			int resultSpon = pstmt.executeUpdate();
			
			if(resultSpon > 0) {
				
				String sql2 = prop.getProperty("updateGiver");
				String sql3 = prop.getProperty("updateReceiver");
				
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, s.getShodu());
				pstmt2.setInt(2, s.getSgiverid());
				pstmt2.setInt(3, s.getShodu());
				PreparedStatement pstmt3 = con.prepareStatement(sql3);
				pstmt3.setInt(1, s.getShodu());
				pstmt3.setString(2, s.getSreceivername());
				
				int result2 = pstmt2.executeUpdate();
				int result3 = pstmt3.executeUpdate();
				
				if(result2 > 0 && result3 > 0) {
					
					System.out.println("Giver Receiver 후원 성공");
					
					resultAll = 1;
					
				} else {
					
					System.out.println("Giver Receiver 후원 실패");
					
					resultAll = -2;
					
				}
				
				close(pstmt2);
				close(pstmt3);
				
			} else {
				
				System.out.println("insertSpon 후원 실패");
				resultAll = 0;
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return resultAll;
		
	}

	public String searchEmail(Connection con, String nickName) {

		String result = null;

		String sql = prop.getProperty("searchEmail");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, nickName);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				result = rset.getString(1);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(pstmt);

		}

		return result;
		
	}

	public Member selectHodu(Connection con, int mid) {
		
		Member m = null;
		
		String sql = prop.getProperty("selectHodu");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				m = new Member();
				
				m.setMhodu(rset.getInt(1));
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return m;
	}

}
