package com.kh.boardcomment.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.boardcomment.model.vo.BoardComment;

public class BoardCommentDao {
	private Properties prop = new Properties();

	public BoardCommentDao() {
		String filePath = BoardCommentDao.class.getResource("/config/comment-query.properties").getPath();
		try {

			prop.load(new FileReader(filePath));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public int insertComment(Connection con, BoardComment bc) {
		
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("insertComment");
		System.out.println(bc);
		
		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bc.getCcontent());
			pstmt.setInt(2, bc.getBid());
			pstmt.setInt(3, bc.getCwriter());

			if (bc.getCrefmid() > -1) {
				pstmt.setInt(4, bc.getCrefmid());
			} else {
				pstmt.setNull(4, java.sql.Types.NULL);
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public BoardComment selectOne(Connection con, int cid) {
		
		BoardComment bc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectOne");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cid);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				bc = new BoardComment();

				bc.setCid(cid);
				bc.setCcontent(rset.getString("ccontent"));
				bc.setCdate(rset.getDate("cdate"));
				bc.setCstatus(rset.getString("cstatus"));
				bc.setBid(rset.getInt("bid"));
				bc.setCwriter(rset.getShort("cwriter"));
				bc.setCrcount(rset.getInt("crcount"));
				bc.setCrefmid(rset.getInt("crefmid"));
				bc.setCwname(rset.getString("cwname"));
				bc.setCfname(rset.getString("cfname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return bc;
	}

	public ArrayList<BoardComment> selectList(Connection con, int bid) {
		
		ArrayList<BoardComment> clist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			clist = new ArrayList<BoardComment>();

			while (rset.next()) {
				BoardComment bc = new BoardComment();

				bc.setCid(rset.getInt("cid"));
				bc.setCcontent(rset.getString("ccontent"));
				bc.setCdate(rset.getDate("cdate"));
				bc.setCstatus(rset.getString("cstatus"));
				bc.setBid(bid);
				bc.setCwriter(rset.getShort("cwriter"));
				bc.setCrcount(rset.getInt("crcount"));
				bc.setCrefmid(rset.getInt("crefmid"));
				bc.setCwname(rset.getString("cwname"));
				bc.setCfname(rset.getString("cfname"));

				clist.add(bc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return clist;
	}

	public int updateComment(Connection con, BoardComment bc) {
		
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("updateComment");

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bc.getCcontent());
			pstmt.setInt(2, bc.getCid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}

		return result;
	}

	public int deleteComment(Connection con, int cid) {
		
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("deleteComment");

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}
