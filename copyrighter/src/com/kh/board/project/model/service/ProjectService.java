package com.kh.board.project.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.project.model.dao.ProjectDao;
import com.kh.board.project.model.vo.Project;

public class ProjectService {
	
	private ProjectDao pDao = new ProjectDao();

	public int insertProject(Project p, AttachedFile af) {
		Connection con = getConnection();
		
		int result = 0;
		int result2 = 0;
		
		System.out.println("p : " + p);
		
		int bid = pDao.selectCurrentBid(con);
		af.setBid(bid);
		
		int result1 = pDao.insertBoardContent(con, p, bid);
		
		if(result1 > 0){
			result2 = pDao.insertProjectContent(con, p, bid);
		}
		
		int result3 = pDao.insertAttachedfile(con, af);
		
		if( result1 > 0 && result2 > 0 && result3 > 0) {
			commit(con);
			result = 1;
			pDao.insertAlarm(con, p);	
			
		} else rollback(con);
		
		close(con);
		
		return result*bid;
	}

	public Project selectOne(int bid) {
		Connection con = getConnection();
		int result = 0;
		
		Project p = pDao.selectOne(con, bid);
		
		if( p != null ){
			result = pDao.updateCount(con, bid);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		return p;
	}

	public HashMap<String, Object> selectProjectMap(int bid) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		
		int result = pDao.updateCount(con, bid);
		
		if(result > 0) {
			commit(con);
			hmap = pDao.selectProjectMap(con, bid);
			
			System.out.println("service hamp : " + hmap);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return hmap;
	}

	public int updateProject(Project p, AttachedFile af) {
		Connection con = getConnection();
		
		int result = 0;

		System.out.println("p : " + p);
		
		int result1 = pDao.updateBoard(con, p);
		int result2 = pDao.updateProject(con, p);
				
		int result3 = pDao.updateAttachedfile(con, af);
		
		if( result1 > 0 && result2 > 0 && result3 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Project> selectProjectList() {
		// 게시판 목록
		Connection con = getConnection();
		ArrayList<Project> projectList = pDao.selectProjectList(con);

		System.out.println("projectList SER"+ projectList);
		
		close(con);
		return projectList;
	}
	
	public int getCountProjectList() {
		// 게시판 페이지

		Connection con = getConnection();
		int listCount = pDao.getCountProjectList(con);
		
		close(con);		
		return listCount;
	}
	
	public int deleteProject(int bid) {
		Connection con = getConnection();
		
		int result = pDao.deleteProject(con, bid);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int countDday(int bid) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		int countDday = pDao.countDday(con, bid);
		System.out.println("countDday ser : "+ countDday);
		close(con);
		
		return countDday;
	}



}
