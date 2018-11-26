package com.kh.follow.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.gallery.model.vo.Gallery;
import com.kh.follow.model.service.FollowService;

/**
 * Servlet implementation class FollowCheckServlet
 */
@WebServlet("/fCheck.fo")
public class FollowCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String wid = request.getParameter("wid"); //글쓴이
		String mid = request.getParameter("mid"); //로그인한회원
		
		int result = new FollowService().checkFollow(wid, mid);
		
		response.getWriter().print((result > 0) ? "ok" : "no"); // ok=이미 팔로워 한 상태
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
