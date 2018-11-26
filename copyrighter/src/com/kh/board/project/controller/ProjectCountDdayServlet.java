package com.kh.board.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.project.model.service.ProjectService;

/**
 * Servlet implementation class ProjectDeleteServlet
 */
@WebServlet("/pCountDday.pr")
public class ProjectCountDdayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectCountDdayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ProjectService ps = new ProjectService();
		
		int countDday = 0;
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		countDday = ps.countDday(bid);
		
		PrintWriter out = response.getWriter();
		
		request.setAttribute("countDday", countDday);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
