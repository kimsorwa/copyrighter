package com.kh.board.gallery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.gallery.model.service.GalleryService;
import com.kh.board.gallery.model.vo.Gallery;

/**
 * Servlet implementation class GalleryImageListServlet
 */
@WebServlet("/gCategoryList.ga")
public class GalleryCategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryCategoryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryId = Integer.parseInt(request.getParameter("cateId"));
		System.out.println("categoryId : " + categoryId);
		ArrayList<Gallery> gList = null;
		GalleryService gs = new GalleryService();
		
		gList = gs.selectCategoryId(categoryId);
				
		if(gList != null) {
			request.setAttribute("gList2", gList);
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
