package com.kh.board.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.notice.model.service.NoticeService;
import com.kh.board.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/nUpdate.no")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService ns = new NoticeService();
	
		System.out.println("NoticeUpdateServlet 들어왔니?");
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		System.out.println("bid : " + bid);
		
		// Notice 객체 생성 후 DB 전달 VO 설정하기
		Notice n = ns.selectOne(bid);
		
		System.out.println("userId : " + request.getParameter("userId"));
		System.out.println("notice : " + n);
		
		n.setBtype(1);
		n.setBtitle(request.getParameter("title"));
		n.setBcontent(request.getParameter("content"));
		n.setBwriter(Integer.parseInt(request.getParameter("userId")));
		
		System.out.println("setBcontent : " + request.getParameter("content"));			

		// service로 작성한 내용 전송하기
		
		int result = ns.updateNotice(n);
		
		if(result > 0) {
			response.sendRedirect("nSelectOne.no?bid="+(result*bid));
			
		} else {

			request.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
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
