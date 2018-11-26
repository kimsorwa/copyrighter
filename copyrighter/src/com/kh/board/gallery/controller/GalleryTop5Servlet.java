package com.kh.board.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.board.gallery.model.service.GalleryService;
import com.kh.board.gallery.model.vo.Gallery;
import com.kh.board.gallery.model.vo.PageInfo;

/**
 * Servlet implementation class GalleryTop5Servlet
 */
@WebServlet("/gTop5.ga")
public class GalleryTop5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryTop5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		GalleryService gs = new GalleryService();
		
		ArrayList<Gallery> gTop5List = gs.galleryTop5();
		System.out.println("Top5list ser: "+ gTop5List);
		/*
		if(gTop5List != null){
			
			request.setAttribute("gTop5List", gTop5List);
			
		} */
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		gson.toJson(gTop5List, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
