package com.kh.board.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.common.model.vo.Board;
import com.kh.board.notice.model.service.NoticeService;
import com.kh.board.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/nSelectOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		System.out.println("nsv bid : "+ request.getParameter("bid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		NoticeService ns = new NoticeService();
		
		Notice n = ns.selectOne(bid);

		String page = "";		
		if(n != null){
			page = "views/board/noticeboard/noticeDetail.jsp";
			request.setAttribute("notice", n);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 상세보기 실패!");
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
