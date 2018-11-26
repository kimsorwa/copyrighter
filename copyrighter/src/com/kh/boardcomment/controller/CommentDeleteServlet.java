package com.kh.boardcomment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.boardcomment.model.service.BoardCommentService;


/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/cDelete.co")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int btype = Integer.parseInt(request.getParameter("btype"));
		
		int result = new BoardCommentService().deleteComment(cid);
		
		System.out.println("댓글 삭제 cid : " + cid);
		
		if(result > 0) {
			switch(btype) {
			case 2: response.sendRedirect("gSelectOne.ga?bid=" + bid); break;
			case 3: response.sendRedirect("jSelectOne.pr?bid=" + bid); break;
			}
		} else {
			request.setAttribute("msg", "댓글 삭제 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
