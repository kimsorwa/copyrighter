package com.kh.board.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.gallery.model.service.GalleryService;
import com.kh.board.project.model.service.ProjectService;
import com.kh.board.project.model.vo.Project;

/**
 * Servlet implementation class GalleryUpdateViewServlet
 */
@WebServlet("/jUpView.pr")
public class ProjectUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bid = Integer.parseInt(request.getParameter("bid"));
		
		Project p = new ProjectService().selectOne(bid);
		AttachedFile af = new GalleryService().updateViewAf(bid);	
		
		String page = "";
		if(p != null) {
			
			page = "views/board/projectboard/projectUpdate.jsp";
			request.setAttribute("project", p);
			request.setAttribute("attachedfile", af);
			
		} else {
			
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 수정화면 전환 실패!");
			
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
