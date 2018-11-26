package com.kh.board.gallery.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.alarm.model.vo.Alarm;
import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.gallery.model.vo.Gallery;

public class GalleryDao {

	private Properties prop = new Properties();

	public GalleryDao() {

		String filePath = GalleryDao.class.getResource("/config/gallery-query.properties").getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Gallery selectOne(Connection con, int bid) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Gallery g = null;

		String sql = prop.getProperty("selectOne");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				g = new Gallery();

				g.setGid(rset.getInt("gid"));
				g.setGcategoryid(rset.getInt("gcategoryid"));
				g.setGtag(rset.getString("gtag"));
				g.setGlike(rset.getInt("glike"));
				g.setBid(rset.getInt("bid"));
				g.setCclid(rset.getInt("cclid"));
				g.setGcategoryname(rset.getString("gcategoryname"));
				g.setCclname(rset.getString("cclname"));

				g.setBtype(rset.getInt("btype"));
				g.setBtitle(rset.getString("btitle"));
				g.setBcontent(rset.getString("bcontent"));
				g.setBcount(rset.getInt("bcount"));
				g.setBdate(rset.getDate("bdate"));
				g.setBstatus(rset.getString("bstatus"));
				g.setBrcount(rset.getInt("brcount"));
				g.setBwriter(rset.getInt("bwriter"));
				g.setMname(rset.getString("mname"));
				g.setMprofile(rset.getString("mprofile"));
				

				// System.out.println("gdao g : " + g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return g;
	}
	
	public AttachedFile selectOneAf(Connection con, int bid) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AttachedFile af = null;

		String sql = prop.getProperty("selectOneAf");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				af = new AttachedFile();
				
				af.setBid(bid);
				af.setFid(rset.getInt("fid"));
				af.setFlevel(rset.getInt("flevel"));
				af.setFname(rset.getString("fname"));
				af.setFpath(rset.getString("fpath"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return af;
	}

	public int insertBoardContent(Connection con, Gallery g, int bid) {

		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("insertBoard");
		System.out.println("insertBoardContent DAO bid 확인 : " + bid);

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bid);
			pstmt.setInt(2, g.getBtype());
			pstmt.setString(3, g.getBtitle());
			pstmt.setString(4, g.getBcontent());
			pstmt.setInt(5, g.getBwriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		System.out.println("insertBoardContent result : " + result);
		return result;
	}

	public int insertGalleryContent(Connection con, Gallery g, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("insertGallery");

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, g.getGcategoryid());
			pstmt.setString(2, g.getGtag());
			pstmt.setInt(3, bid);
			pstmt.setInt(4, g.getCclid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		System.out.println("insertGalleryContent result : " + result);
		return result;
	}

	public int selectCurrentBid(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int bid = 0;

		String sql = prop.getProperty("selectCurrentBid");

		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				bid = rset.getInt(1); // "CURRVAL"
				System.out.println("bid : " + bid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return bid;
	}

	public int insertAttachedfile(Connection con, AttachedFile af, int bid) {

		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("insertAttachedfile");

		try {

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, af.getFname());
				pstmt.setString(2, af.getFpath());
				pstmt.setInt(3, 1);
				pstmt.setInt(4, bid);

				result += pstmt.executeUpdate();


		} catch (SQLException e) {

		} finally {
			close(pstmt);
		}

		System.out.println("insertAttachedfile result : " + result);
		return result;
	}

	public int updateBoard(Connection con, Gallery g) {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("updateBoard");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, g.getBtitle());
			pstmt.setString(2, g.getBcontent());
			pstmt.setInt(3, g.getBid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateGallery(Connection con, Gallery g) {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("updateGallery");

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, g.getGcategoryid());
			pstmt.setString(2, g.getGtag());
			pstmt.setInt(3, g.getCclid());
			pstmt.setInt(4, g.getBid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAttachedfile(Connection con, AttachedFile af, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("updateAttachedfile");

		try {
				pstmt = con.prepareStatement(sql);
			
				pstmt.setString(1, af.getFname());
				pstmt.setInt(2, bid);
				
				System.out.println("af.getFname() : " + af.getFname());
				System.out.println("bid : " + bid);

				result += pstmt.executeUpdate();

		} catch (SQLException e) {

		} finally {
			close(pstmt);
		}

		System.out.println("updateAttachedfile result : " + result);
		return result;
	}

	public int updateCount(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateCount");
		
		try{
			
			pstmt = con.prepareStatement(sql);
				
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public HashMap<String, Object> selectGalleryMap(Connection con, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Object> hmap = null;
		Gallery g = null;
		AttachedFile af = null;
		
		System.out.println("selectGalleryMap 진입");
		String sql = prop.getProperty("selectGalleryOne");
				
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();
			
			af = new AttachedFile();
			
			while(rset.next()){
				
				g = new Gallery();
				g.setGid(rset.getInt("gid"));
				g.setGcategoryid(rset.getInt("gcategoryid"));
				g.setGtag(rset.getString("gtag"));
				g.setGlike(rset.getInt("glike"));
				g.setCclid(rset.getInt("cclid"));
				
				g.setBid(rset.getInt("bid"));
				g.setBtype(rset.getInt("btype"));
				g.setBtitle(rset.getString("btitle"));
				g.setBcontent(rset.getString("bcontent"));
				g.setBcount(rset.getInt("bcount"));
				g.setBdate(rset.getDate("bdate"));
				g.setBstatus(rset.getString("bstatus"));
				g.setBrcount(rset.getInt("brcount"));
				g.setBwriter(rset.getInt("bwriter"));
				g.setMname(rset.getString("mname"));
				
				af = new AttachedFile();
				af.setBid(bid);
				af.setFid(rset.getInt("fid"));
				af.setFlevel(rset.getInt("flevel"));
				af.setFname(rset.getString("fname"));
				af.setFpath(rset.getString("fpath"));
				
				System.out.println("selectGalleryMap의 g : " + g);
				System.out.println("selectGalleryMap의 af : " + af);
				
			}
			
			hmap = new HashMap<String, Object>();
			
			hmap.put("gallery", g);
			hmap.put("attachedfile", af);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hmap;
	}
	
	public ArrayList<Gallery> selectGalleryList(Connection con, int currentPage, int limit) {
		
		ArrayList<Gallery> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

				
		String sql = prop.getProperty("selectGalleryList");		
		
		try {
			
			pstmt = con.prepareStatement(sql);			
			
			int startRow = (currentPage -1) * limit +1;	
			int endRow = startRow + limit -1;
/*			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);					
*/			
			rset = pstmt.executeQuery();			
			list = new ArrayList<Gallery>();
			
			while(rset.next()){
				
				Gallery g = new Gallery();
				// 갤러리분
				g.setGid(rset.getInt("GID"));
				g.setGcategoryid(rset.getInt("GCATEGORYID"));
				g.setGtag(rset.getString("GTAG"));
				g.setGlike(rset.getInt("GLIKE"));
				// 상속분
				g.setBid(rset.getInt("BID"));
				g.setBtype(rset.getInt("BTYPE"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setBcount(rset.getInt("BCOUNT"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBstatus(rset.getString("BSTATUS"));
				g.setBwriter(rset.getInt("BWRITER"));
				// 
				g.setFname(rset.getString("FNAME"));
				
				list.add(g);
				//System.out.println("selectGalleryList Dao : "+ list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
				


		public int getCountGalleryList(Connection con) {
		// 
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countGalleryList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			if(rset.next()){
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Gallery> galleryTop5(Connection con) {
		// 
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Gallery> gTop5List = null;
		
		String sql = prop.getProperty("selectGalleryTop5");
		
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			gTop5List = new ArrayList<Gallery>();
			
			while(rset.next()){
				Gallery g = new Gallery();
				
				g.setBid(rset.getInt("BID"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setBwriter(rset.getInt("BWRITER"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBcount(rset.getInt("BCOUNT"));
				
				g.setFname(rset.getString("FNAME"));
				
				gTop5List.add(g);
				
			}
			System.out.println("Top5 Dao : " + gTop5List);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return gTop5List;
	}


	public ArrayList<Gallery> searchGallery(Connection con, int mid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Gallery> glist = null;
		
		String sql = prop.getProperty("selectMyworkList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			
			rset = pstmt.executeQuery();
			
			glist = new ArrayList<Gallery>();
			
			while(rset.next()){
				Gallery g = new Gallery();
				
				g.setBid(rset.getInt("BID"));
				
				g.setFName(rset.getString("FNAME"));
				g.setMname(rset.getString("MNAME"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setgTag(rset.getString("GTAG"));
				g.setBcount(rset.getInt("BCOUNT"));
				g.setLikeCnt(rset.getInt("LIKECNT"));
				g.setCommCnt(rset.getInt("COMMCNT"));
				
				g.setBcontent(rset.getString("BCONTENT"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBrcount(rset.getInt("BRCOUNT"));
				g.setBstatus(rset.getString("BSTATUS"));
				g.setBtype(rset.getInt("BTYPE"));
				g.setBwriter(rset.getInt("BWRITER"));
				g.setGcategoryid(rset.getInt("GCATEGORYID"));
				g.setGid(rset.getInt("GID"));
				
				glist.add(g);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return glist;
	}
	
	public int deleteGallery(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteGallery");

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Gallery> searchGallery(Connection con, String condition, String keyword) {
		
		ArrayList<Gallery> searchGalleryList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		String sql = null;
		
		switch(condition){
		case "image" : 
			sql = prop.getProperty("searchImage");
			break;
		case "audio" :
			sql = prop.getProperty("searchAudio");
			break;
		case "text" : 
			sql = prop.getProperty("searchText");
			break;
		case "vidio" : 
			sql = prop.getProperty("searchVidio");
			break;
		}
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			searchGalleryList = new ArrayList<Gallery>();
			
			while(rset.next()){
				
				Gallery g = new Gallery();
				// 갤러리분
				g.setGid(rset.getInt("GID"));
				g.setGcategoryid(rset.getInt("GCATEGORYID"));
				g.setGtag(rset.getString("GTAG"));
				g.setGlike(rset.getInt("GLIKE"));
				// 상속분
				g.setBid(rset.getInt("BID"));
				g.setBtype(rset.getInt("BTYPE"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setBcontent(rset.getString("BCONTENT"));
				g.setBcount(rset.getInt("BCOUNT"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBstatus(rset.getString("BSTATUS"));
				g.setBwriter(rset.getInt("BWRITER"));
				
				searchGalleryList.add(g); 
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		// 확인용 출력문
		//for(Notice n : list) System.out.println(list);
		return searchGalleryList;
	}

	public void insertAlarm(Connection con, Gallery g) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Alarm> alarmList = null;
		Alarm al = null;
		
		String sql = prop.getProperty("viewFollower");
		
		try {
			
			alarmList = new ArrayList<Alarm>();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, g.getBwriter());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
				al = new Alarm();
				al.setMid(rset.getInt(2));
				alarmList.add(al);
				
				System.out.println("al : " + al);
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		String sql2 = prop.getProperty("insertAlarm");

		try {
			pstmt = con.prepareStatement(sql2);
			
			for(int i = 0; i < alarmList.size(); i++){	
				
				System.out.println("al.getMid() : " + al.getMid());
				pstmt.setInt(1, al.getMid());
				String msg = g.getMname() +" 님이 " +  g.getBtitle() +" (을)를 업로드했습니다.";
				pstmt.setString(2, msg);
			}
			
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
	
	public int countComment(Connection con, int bid) {
		int countComment = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();

			if(rset.next()){
				countComment = Integer.parseInt(rset.getString(1));
				System.out.println("countComment dao : "+ countComment);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return countComment;
	}

	public ArrayList<Gallery> selectGalleryList(Connection con) {
		
		ArrayList<Gallery> gList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectGalleryList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			gList = new ArrayList<Gallery>();
			while(rset.next()){
				Gallery g = new Gallery();
				
				g.setBid(rset.getInt("BID"));
				
				g.setFName(rset.getString("FNAME"));
				g.setMname(rset.getString("MNAME"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setgTag(rset.getString("GTAG"));
				g.setBcount(rset.getInt("BCOUNT"));
				g.setLikeCnt(rset.getInt("LIKECNT"));
				g.setCommCnt(rset.getInt("COMMCNT"));
				
				g.setBcontent(rset.getString("BCONTENT"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBrcount(rset.getInt("BRCOUNT"));
				g.setBstatus(rset.getString("BSTATUS"));
				g.setBtype(rset.getInt("BTYPE"));
				g.setBwriter(rset.getInt("BWRITER"));
				g.setGcategoryid(rset.getInt("GCATEGORYID"));
				g.setGid(rset.getInt("GID"));
				
				gList.add(g);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		
		return gList;
	}

	public ArrayList<Gallery> selectCategoryId(Connection con, int categoryId) {

		ArrayList<Gallery> gList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryId");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rset = pstmt.executeQuery();
			
			gList = new ArrayList<Gallery>();
			while(rset.next()){
				Gallery g = new Gallery();
				
				g.setBid(rset.getInt("BID"));
				
				g.setFName(rset.getString("FNAME"));
				g.setMname(rset.getString("MNAME"));
				g.setBtitle(rset.getString("BTITLE"));
				g.setgTag(rset.getString("GTAG"));
				g.setBcount(rset.getInt("BCOUNT"));
				g.setLikeCnt(rset.getInt("LIKECNT"));
				g.setCommCnt(rset.getInt("COMMCNT"));
				
				g.setBcontent(rset.getString("BCONTENT"));
				g.setBdate(rset.getDate("BDATE"));
				g.setBrcount(rset.getInt("BRCOUNT"));
				g.setBstatus(rset.getString("BSTATUS"));
				g.setBtype(rset.getInt("BTYPE"));
				g.setBwriter(rset.getInt("BWRITER"));
				g.setGcategoryid(rset.getInt("GCATEGORYID"));
				g.setGid(rset.getInt("GID"));
				
				gList.add(g);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return gList;
	}
}