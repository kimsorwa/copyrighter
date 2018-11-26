package com.kh.board.project.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.project.model.service.ProjectService;
import com.kh.board.project.model.vo.Project;
import com.kh.boardcomment.model.service.BoardCommentService;
import com.kh.boardcomment.model.vo.BoardComment;

/**
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/jSelectOne.pr")
public class ProjectSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		Project p = new ProjectService().selectOne(bid);
		ArrayList<BoardComment> clist = new BoardCommentService().selectList(bid);
		
		String page = "";
		if(p != null) {
			page = "views/board/projectboard/projectDetail.jsp";
			request.setAttribute("project", p);
			request.setAttribute("clist", clist);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "갤러리 상세보기 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
