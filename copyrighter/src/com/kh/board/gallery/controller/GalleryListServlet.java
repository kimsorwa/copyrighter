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
import com.kh.board.gallery.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/gList.ga")
public class GalleryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Gallery> gList = null;
		GalleryService gs = new GalleryService();
		
		gList = gs.selectGalleryList();
		
		if(gList != null) {
			request.setAttribute("glist", gList);
			request.getRequestDispatcher("/galleryMain.jsp").forward(request, response);
		} else {
			System.out.println("실패");
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