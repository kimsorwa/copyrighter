package com.kh.payment.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.payment.model.vo.Payment;

public class PaymentDao {
	
private Properties prop = null;
	
	public PaymentDao(){
		prop = new Properties();
		
		String filePath = PaymentDao.class
				.getResource("/config/admin-query.properties").getPath();
		
		try {
			
			prop.load(new FileReader(filePath));
		
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	 
	public ArrayList<Payment> selectList(Connection con){
		ArrayList<Payment> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPayment");
		System.out.println("sql : " + sql);
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<Payment>();
			
			while(rset.next()){
				Payment p = new Payment();
				
				p.setPid(rset.getInt("pid"));
				p.setPmoneyid(rset.getInt("pmoney"));
				p.setPdate(rset.getDate("pdate"));
				p.setMid(rset.getInt("mid"));
				p.setPmoney(rset.getInt("money"));
				p.setPhodu(rset.getInt("phodu"));
				p.setMname(rset.getString("mname"));
				p.setMemail(rset.getString("memail"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(stmt);
		}
		
		return list;
	}


	public ArrayList<Payment> searchPayment(Connection con, String condition, String keyword) {
		ArrayList<Payment> plist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "name" :
			sql = prop.getProperty("pSearchByName"); break;
		case "email" :
			sql = prop.getProperty("pSearchByEmail"); break;
		}
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			plist = new ArrayList<Payment>();
			
			while(rset.next()){
				
				Payment p = new Payment();
				
				p.setPid(rset.getInt("pid"));
				p.setPmoneyid(rset.getInt("pmoney"));
				p.setPdate(rset.getDate("pdate"));
				p.setMid(rset.getInt("mid"));
				p.setMname(rset.getString("mname"));
				p.setMemail(rset.getString("memail"));
				p.setPmoney(rset.getInt("money"));
				p.setPhodu(rset.getInt("phodu"));
				
				plist.add(p);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return plist;
	}


	public ArrayList<Payment> searchPayment(Connection con, int mid) {
		ArrayList<Payment> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMypayment");

		
		try {
			
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, mid);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Payment>();
			
			while(rset.next()){
				
				Payment p = new Payment();
				p.setPmoney_1(rset.getInt("PAYMONEY"));
				p.setPdate(rset.getDate("pdate"));
				p.setPmoney(rset.getInt("pmoney"));
				p.setPhodu(rset.getInt("phodu"));
		
				list.add(p);

			}
			System.out.println("PAYMENT list : " + list);
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
