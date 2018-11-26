package com.kh.board.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.gallery.model.service.GalleryService;
import com.kh.board.gallery.model.vo.Gallery;

/**
 * Servlet implementation class GalleryUpdateViewServlet
 */
@WebServlet("/gUpView.ga")
public class GalleryUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bid = Integer.parseInt(request.getParameter("bid"));
		
		Gallery g = new GalleryService().updateView(bid);
		AttachedFile af = new GalleryService().updateViewAf(bid);	
		
		String page = "";
		if(g != null) {
			
			page = "views/board/galleryboard/galleryUpdate.jsp";
			request.setAttribute("gallery", g);
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
