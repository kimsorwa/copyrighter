package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	private Connection con;
	private MemberDao mDao = new MemberDao();

	public int signUpMember(Member m) {
		
		int result = 0;
		
		con = getConnection();
		
		result = mDao.signUpMember(con, m);
		
		if(result > 0){
			commit(con);
			Member m2 = mDao.selectMember(con, m);
			mDao.insertAlarm(con, m2.getMid());
		} else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public Member selectMember(Member m) {

		con = getConnection();
		
		Member result = mDao.selectMember(con, m);
				
		if(result != null) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;

	}
	
	public Member selectMember(int mid) {

		con = getConnection();
		
		Member result = mDao.selectMember(con, mid);
		
		close(con);
		
		return result;

	}

	public int selectEmail(Member m) {
		
		int result = 0;
		
		con = getConnection();
		
		result = mDao.selectEmail(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateRandomPwd(String tempPassword, Member m) {
		
		int result = 0;
		
		con = getConnection();
		
		result = mDao.updateRandomPwd(con, tempPassword, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateMember(Member m) {
		
		int result = 0;
		
		con = getConnection();
		
		result = mDao.updateMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
		
	}

	public int deleteMember(String memail) {
		int result = 0;
		
		con = getConnection();
		
		result = mDao.deleteMember(con, memail);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
		
	}

	public int updateMemberStatus(Member m) {
		int result = 0;
		
		con = getConnection();
		
		result = mDao.updateMemberStatus(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
	}

	public ArrayList<Member> searchMember(String condition, String keyword) {
		Connection con = getConnection();
		ArrayList<Member> mlist = null;
		
		mlist = (condition.length() > 0) ? 
				mDao.searchMember(con, condition, keyword) : mDao.selectList(con); 
		
		return mlist;
	}

	public ArrayList<Member> selectlist() {
		ArrayList<Member> mlist = null;
		Connection con = getConnection();
		
		mlist = mDao.selectList(con);
		
		close(con);
		
		return mlist;
	}

	public ArrayList<Member> searchMypage() {
		ArrayList<Member> list = null;
		Connection con = getConnection();
		
		list = mDao.selectMypage(con);
		
		close(con);
		
		return list;
	}

	public int updateProfile(Member m) {
		
		int result = 0;
		
		con = getConnection();
		
		result = mDao.updateProfile(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
	}

}
