package com.kh.payment.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;
import com.kh.payment.model.dao.PaymentDao;
import com.kh.payment.model.vo.Payment;


public class PaymentService {
	
private PaymentDao pDao = new PaymentDao();
	
	public ArrayList<Payment> selectlist() {
		
		ArrayList<Payment> list = null;
		Connection con = getConnection();
		
		list = pDao.selectList(con);
		
		close(con);
		
		return list;

	}

	public ArrayList<Payment> searchPayment(String condition, String keyword) {
		Connection con = getConnection();
		ArrayList<Payment> plist = null;
		
		plist = (condition.length() > 0) ? 
				pDao.searchPayment(con, condition, keyword) : pDao.selectList(con); 
		
		return plist;
	}

	public ArrayList<Payment> searchPayment(int mid) {
		Connection con = getConnection();
		ArrayList<Payment> list = pDao.searchPayment(con, mid);
		
		close(con);
		
		return list;
	}

}
