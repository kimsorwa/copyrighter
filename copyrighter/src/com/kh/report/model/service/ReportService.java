package com.kh.report.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.report.model.dao.ReportDao;
import com.kh.report.model.vo.Report;

public class ReportService {

	ReportDao rDao = new ReportDao();

	public int insertReport(Report r) {
		Connection con = getConnection();

		int result = rDao.insertReport(con, r);
		int result2 = rDao.updateCount(con, r);

		if(result*result2 > 0) commit(con);
		else rollback(con);
		
		close(con);

		return result*result2;
	}

	public int deleteReport(int rid) {
		Connection con = getConnection();

		int result = rDao.deleteReport(con, rid);

		if (result > 0) commit(con);
		else rollback(con);

		close(con);

		return result;
	}
	public ArrayList<Report> selectlist() {
		ArrayList<Report> list = null;
		Connection con = getConnection();
		
		list = rDao.selectList(con);
		
		close(con);
		
		return list;
	}

}
