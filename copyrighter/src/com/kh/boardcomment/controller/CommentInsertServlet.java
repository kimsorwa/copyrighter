package com.kh.boardcomment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.boardcomment.model.service.BoardCommentService;
import com.kh.boardcomment.model.vo.BoardComment;

/**
 * Servlet implementation class CommentInsertServlet
 */
@WebServlet("/cInsert.co")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		int btype = Integer.parseInt(request.getParameter("btype"));
		int cwriter = Integer.parseInt(request.getParameter("cwriter"));
		String ccontent = request.getParameter("ccontent");
		int crefmid = Integer.parseInt(request.getParameter("crefmid"));
		
		BoardComment bc = new BoardComment();
		bc.setBid(bid);
		bc.setCwriter(cwriter);
		bc.setCcontent(ccontent);
		bc.setCrefmid(crefmid);
		
		int result = new BoardCommentService().insertComment(bc);
		
		if(result > 0) {
			switch(btype) {
			case 2: response.sendRedirect(request.getContextPath()+"/gSelectOne.ga?bid=" + bid); break;
			case 3: response.sendRedirect(request.getContextPath()+"/jSelectOne.pr?bid=" + bid); break;
			}
		} else {
			request.setAttribute("msg", "댓글 작성 실패!");
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
