package com.kh.board.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.notice.model.service.NoticeService;
import com.kh.board.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/nInsert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService ns = new NoticeService();
		
	
			
			System.out.println("NoticeInsertServlet 들어왔니?");
			
			// Notice 객체 생성 후 DB 전달 VO 설정하기
			Notice n = new Notice();
			
			System.out.println("userId : " + request.getParameter("userId"));
			
			n.setBtype(1);
			n.setBtitle(request.getParameter("title"));
			n.setBcontent(request.getParameter("content"));
			n.setBwriter(Integer.parseInt(request.getParameter("userId")));
			
			System.out.println("setBcontent : " + request.getParameter("content"));			

			// service로 작성한 내용 전송하기
			
			int result = ns.insertNotice(n);
			
			if(result > 0) {
				response.sendRedirect("nSelectOne.no?bid="+result);
				
			} else {
				request.setAttribute("msg", "공지사항 인서트 에러");
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
