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

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/pList.pr")
public class ProjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 목록용 서블릿
		ArrayList<Project> projectList = null;
		ProjectService ps = new ProjectService();
		
		
		projectList = ps.selectProjectList();
		System.out.println("ProjectListServlet ga : "+ projectList);
		
		if(projectList != null){
			request.setAttribute("projectList", projectList);
			request.getRequestDispatcher("/views/board/projectboard/projectList.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "프로젝트 목록 조회실패");
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
