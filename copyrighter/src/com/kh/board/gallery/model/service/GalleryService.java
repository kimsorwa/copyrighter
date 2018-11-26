package com.kh.board.gallery.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.gallery.model.dao.GalleryDao;
import com.kh.board.gallery.model.vo.Gallery;

public class GalleryService {
	
	private GalleryDao gDao = new GalleryDao();

	
	public Gallery selectOne(int bid) {
		
		Connection con = getConnection();
		int result = 0;
		
		Gallery g = gDao.selectOne(con, bid);
		
		if( g != null ){
			result = gDao.updateCount(con, bid);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		return g;
	}
	

	public Gallery updateView(int bid) {
		
		Connection con = getConnection();
		
		Gallery g = gDao.selectOne(con, bid);
		
		close(con);
		
		return g;
	}
	
	public AttachedFile updateViewAf(int bid){
		
		Connection con = getConnection();
		
		AttachedFile af = gDao.selectOneAf(con, bid);
		
		close(con);
		
		return af;
	}

	public int insertGallery(Gallery g, AttachedFile af) {
		Connection con = getConnection();
		
		int result = 0;
		int result2 =0;
		
		System.out.println("g : " + g);
		
		int bid = gDao.selectCurrentBid(con);
		
		int result1 = gDao.insertBoardContent(con, g, bid);
		
		if(result1 > 0){

			result2 = gDao.insertGalleryContent(con, g, bid);

		}
		
		int result3 = gDao.insertAttachedfile(con, af, bid);
		
		if( result1 > 0 && result2 > 0 && result3 > 0) {
			commit(con);
			result = 1;
			gDao.insertAlarm(con, g);	
			
		} else rollback(con);
		
		close(con);
		
		return result*bid;
	}

	public int updateGallery(Gallery g, AttachedFile af) {
		Connection con = getConnection();
		
		int result = 0;

		System.out.println("g : " + g);
		
		int result1 = gDao.updateBoard(con, g);
		int result2 = gDao.updateGallery(con, g);
				
		int result3 = gDao.updateAttachedfile(con, af, g.getBid());
		
		if( result1 > 0 && result2 > 0 && result3 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result;
	}

	public HashMap<String, Object> selectGalleryMap(int bid) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		
		int result = gDao.updateCount(con, bid);
		
		if(result > 0) {
			commit(con);
			hmap = gDao.selectGalleryMap(con, bid);
			
			System.out.println("service hamp : " + hmap);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return hmap;
	}
	
	public ArrayList<Gallery> selectGalleryList(int currentPage, int limit) {
		// 게시판 목록
		Connection con = getConnection();
		ArrayList<Gallery> list = gDao.selectGalleryList(con, currentPage, limit);
		
		close(con);
		return list;
	}
	
	public int getCountGalleryList() {
		// 게시판 페이지

		Connection con = getConnection();
		int listCount = gDao.getCountGalleryList(con);
		
		close(con);		
		return listCount;
	}

		
	public ArrayList<Gallery> galleryTop5(){
		// 게시글 순위
		
		Connection con = getConnection();
		ArrayList<Gallery> list = gDao.galleryTop5(con);

		System.out.println("Top5 Ser : " + list);
		close(con);
		return list;
	}
	

	public ArrayList<Gallery> searchGallery(String condition, String keyword) {
		ArrayList<Gallery> searchGalleryList = null;
		Connection con = getConnection();
		
	
		searchGalleryList = (condition.length() > 0) ? gDao.searchGallery(con, condition, keyword) 
				:  gDao.selectGalleryList(con, 0, 0);
		
		return searchGalleryList;
	}


	public int deleteGallery(int bid) {
		
		Connection con = getConnection();
		
		int result = gDao.deleteGallery(con, bid);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Gallery> searchGallery(int mid) {
		Connection con = getConnection();
		ArrayList<Gallery> glist = gDao.searchGallery(con, mid);

		close(con);
		
		return glist;
	}


	public int countComment(int bid) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		int countComment = gDao.countComment(con, bid);
		System.out.println("countComment ser : "+ countComment);
		close(con);
		
		return countComment;
	}


	public ArrayList<Gallery> selectGalleryList() {
		
		Connection con = getConnection();
		
		ArrayList<Gallery> gList = gDao.selectGalleryList(con);
		
		close(con);
		
		return gList;
	}


	public ArrayList<Gallery> selectCategoryId(int categoryId) {
		
		Connection con = getConnection();
		ArrayList<Gallery> gList = null;
		
		if(categoryId == 0) {
			gList = gDao.selectGalleryList(con);
		} else {
			gList = gDao.selectCategoryId(con, categoryId);
		}
		close(con);
		
		return gList;
	}

}