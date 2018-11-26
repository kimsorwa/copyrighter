package com.kh.board.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.notice.model.service.NoticeService;
import com.kh.board.notice.model.vo.Notice;



/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/nList.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		ArrayList<Notice> noticeList = null;
		NoticeService ns = new NoticeService();
		
		//System.out.println("Mid ser : "+ request.getParameter("Mid"));
		
		noticeList = ns.selectNoticeList();
		String page = "";
		
		if (noticeList != null){
			page = "views/board/noticeboard/noticePage.jsp";
			request.setAttribute("noticeList", noticeList);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지목록 조회실패");
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
