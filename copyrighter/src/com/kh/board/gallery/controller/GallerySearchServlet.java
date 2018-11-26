package com.kh.board.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.gallery.model.service.GalleryService;
import com.kh.board.gallery.model.vo.Gallery;


/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/gSearch.ga")
public class GallerySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GallerySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 서블릿
		// 검색 카데고리
		String condition = request.getParameter("con");
	
		// 검색 키워드
		String keyword = request.getParameter("keyword");
		
		ArrayList<Gallery> searchGalleryList = new ArrayList<Gallery>();
				
		GalleryService gs = new GalleryService();
		
		searchGalleryList = gs.searchGallery(condition, keyword);
		
		String page = "";
		
		if(searchGalleryList != null){
			
			page = "galleryMain.jsp";
			request.setAttribute("list", searchGalleryList);
			
		} else {
			
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 검색실패");
			
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
