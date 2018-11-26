package com.kh.board.gallery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.gallery.model.service.GalleryService;

/**
 * Servlet implementation class GalleryCountCommentServlet
 */
@WebServlet("/gCountCo.ga")
public class GalleryCountCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryCountCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글수 확인용 서블릿
			GalleryService gs = new GalleryService();
				
			int countComment = 0;
			
			int bid = Integer.parseInt(request.getParameter("bid"));
			
			countComment = gs.countComment(bid);
			
			PrintWriter out = response.getWriter();
			if(countComment < 0){
				
				out.print(countComment);
			}
			else{
				
				out.print(countComment);
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
