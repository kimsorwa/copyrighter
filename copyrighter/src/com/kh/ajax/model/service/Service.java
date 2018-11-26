package com.kh.ajax.model.service;

import java.sql.Connection;

import com.kh.ajax.model.dao.Dao;
import com.kh.member.model.vo.Member;
import com.kh.payment.model.vo.Payment;
import com.kh.spon.model.vo.Spon;

import static com.kh.common.JDBCTemplate.*;

public class Service {

	private Connection con = null;
	private Dao dao = new Dao();
	
	public int checkNickName(String nickName) {
		
		int result = 0;
		
		con = getConnection();
		
		result = dao.checkNickName(con, nickName);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public int checkEmail(String email) {

		int result = 0;
		
		con = getConnection();
		
		result = dao.checkEmail(con, email);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public String searchNickName(String email) {

		String result = null;
		
		con = getConnection();
		
		result = dao.searchNickName(con, email);
		
		if(result != null) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public int insertHodu(Member m, Payment p) {
		
		int result = 0;
		
		con = getConnection();
		
		result = dao.insertHodu(con, m, p);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public int spon(Spon s, int hoduId) {

		int result = 0;
		
		Connection con = getConnection();
		
		result = dao.spon(con, s, hoduId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public String searchEmail(String nickName) {

		String result = null;
		
		con = getConnection();
		
		result = dao.searchEmail(con, nickName);
		
		if(result != null) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}

	public Member selectHodu(int mid) {
				
		con = getConnection();
		
		Member m = dao.selectHodu(con, mid);
		
		close(con);
		
		return m;
		
	}
	
}
