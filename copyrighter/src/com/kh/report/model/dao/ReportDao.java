package com.kh.report.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.report.model.vo.Report;

public class ReportDao {
	
	private Properties prop = new Properties();
	
	public ReportDao() {
		
		String filePath = ReportDao.class.getResource("/config/report-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertReport(Connection con, Report r) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertReport");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r.getRreason());
			pstmt.setInt(3, r.getMid());
			pstmt.setInt(5, r.getBid());
			
			if(r.getRreason() != 5) pstmt.setNull(2, java.sql.Types.NULL);
			else pstmt.setString(2, r.getRetc());

			if(r.getCid() > 0) pstmt.setInt(4, r.getCid());
			else pstmt.setNull(4, java.sql.Types.NULL);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteReport(Connection con, int rid) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteReport");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, rid);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Report> selectList(Connection con) {
		
		ArrayList<Report> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReport");
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<Report>();
			
			while(rset.next()){
				Report r = new Report();
				
				r.setRid(rset.getInt("rid"));
				r.setRreason(rset.getInt("rreason"));
				
				if(r.getRreason() == 5) {
					r.setRetc(rset.getString("retc"));
				} else {
					r.setRetc(rset.getString("rcontent"));
				}
				
				r.setRdate(rset.getDate("rdate"));
				r.setMid(rset.getInt("mid"));
				r.setBid(rset.getInt("bid"));
				r.setCid(rset.getInt("cid"));
				r.setMname(rset.getString("mname"));
				
				if(r.getCid() > 0) {
					r.setCwriter(rset.getString("cname"));
				} else {
					r.setCwriter(rset.getString("bname"));
				}
				
				list.add(r);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int updateCount(Connection con, Report r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;
		int id = 0;
		
		if(r.getCid() > 0) {
			sql = "UPDATE BOARDCOMMENT SET CRCOUNT = CRCOUNT+1 WHERE CID = ?";
			id = r.getCid();
		} else {
			sql = "UPDATE BOARD SET BRCOUNT = BRCOUNT+1 WHERE BID = ?";
			id = r.getBid();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}
