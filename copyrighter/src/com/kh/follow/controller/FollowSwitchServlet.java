package com.kh.follow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.follow.model.service.FollowService;

/**
 * Servlet implementation class FollowSwitchServlet
 */
@WebServlet("/fSwitch.fo")
public class FollowSwitchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowSwitchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String wid = request.getParameter("wid"); //글쓴이
		String mid = request.getParameter("mid"); //로그인한회원
		String mname = request.getParameter("mname"); //로그인한회원이름	
		
		System.out.println("mid : " + mid + " / wid : " + wid + " / mname : " + mname);
		
		int result = new FollowService().switchFollow(wid, mid, mname);
		System.out.println("fSwitch.fo result : " + result);
		
		response.getWriter().print((result == 0) ? "error" : (result == 1)? "delete" : "insert");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
