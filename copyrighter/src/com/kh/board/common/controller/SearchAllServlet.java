package com.kh.board.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.common.model.service.BoardService;
import com.kh.board.common.model.vo.Board;

/**
 * Servlet implementation class SearchAllServlet
 */
@WebServlet("/search.all")
public class SearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Board> bList = null;
		BoardService bs = new BoardService();
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		
		bList = bs.searchAll(keyword);
		
		if(bList != null) {
			System.out.println("성공 bList : " + bList);
			request.setAttribute("bList", bList);
			request.getRequestDispatcher("views/board/searchAll.jsp").forward(request, response);
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
