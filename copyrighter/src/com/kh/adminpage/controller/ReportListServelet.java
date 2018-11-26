package com.kh.adminpage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.report.model.service.ReportService;
import com.kh.report.model.vo.Report;

/**
 * Servlet implementation class ReportListServelet
 */
@WebServlet("/reportList.do")
public class ReportListServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportListServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Report> list = null;
		ReportService rs = new ReportService();
		
		list = rs.selectlist();
		
		if(list != null){
			
			request.setAttribute("rlist", list);
			request.getRequestDispatcher("views/adminpage/reportlistView.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msg", "조회 실패!");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
