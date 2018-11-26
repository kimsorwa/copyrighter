package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ajax.model.service.Service;
import com.kh.spon.model.vo.Spon;

/**
 * Servlet implementation class SponServlet
 */
@WebServlet("/spon.sp")
public class SponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String referrer = request.getParameter("reFerrer");
		int giver = Integer.parseInt(request.getParameter("Smid"));
		String receiver = request.getParameter("Swriter");
		int hodu = Integer.parseInt(request.getParameter("Shodu"));
		int hoduId = 0;
		switch(hodu){
			case 10 : hoduId = 1; break;
			case 30 : hoduId = 2; break;
			case 50 : hoduId = 3; break;
			case 100 : hoduId = 4; break;
		}
		
		Spon sp = new Spon();
		
		sp.setSgiverid(giver);
		sp.setSreceivername(receiver);
		sp.setShodu(hodu);
		
		Service s = new Service();
		int result = s.spon(sp, hoduId);

		if(result > 0) {
			out.print("success");
			System.out.println("후원 성공");			
		} else if(result == -2) {
			out.print(referrer);
			System.out.println("남은 호두량 부족");
			System.out.println("이전 URL : " + referrer);
		} else {
			out.print("fail");
			System.out.println("후원 실패");
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
