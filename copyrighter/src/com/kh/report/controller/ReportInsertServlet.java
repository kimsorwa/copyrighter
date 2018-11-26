package com.kh.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.report.model.service.ReportService;
import com.kh.report.model.vo.Report;

/**
 * Servlet implementation class ReportInsertServlet
 */
@WebServlet("/rInsert.re")
public class ReportInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rreason = Integer.parseInt(request.getParameter("rreason"));
		String retc = request.getParameter("retc");
		int mid = Integer.parseInt(request.getParameter("mid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		Report r = new Report();
		r.setRreason(rreason);
		r.setRetc(retc);
		r.setMid(mid);
		r.setCid(cid);
		r.setBid(bid);
		
		int result = new ReportService().insertReport(r);
		
		System.out.println("신고 result : " + result);
		
		response.getWriter().print((result > 0) ? "ok" : "no");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
