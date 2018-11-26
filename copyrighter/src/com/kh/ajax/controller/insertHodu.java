package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ajax.model.service.Service;
import com.kh.member.model.vo.Member;
import com.kh.payment.model.vo.Payment;

/**
 * Servlet implementation class insertHodu
 */
@WebServlet("/inserthodu.pm")
public class insertHodu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertHodu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		int pmoney_id = 0;
		int hodu = Integer.parseInt(request.getParameter("Mhodu"));
		switch(hodu){
			case 50 : pmoney_id = 1; break;
			case 100 : pmoney_id = 2; break;
			case 300 : pmoney_id = 3; break;
			case 500 : pmoney_id = 4; break;
		}
		int mid = Integer.parseInt(request.getParameter("Mid"));
		int price = Integer.parseInt(request.getParameter("Mprice"));
		
		Member m = new Member();
		
		m.setMhodu(hodu);
		
		Payment p = new Payment();
		
		p.setPmoney(pmoney_id);
		p.setMid(mid);
		
		Service sv = new Service();
		
		int result = sv.insertHodu(m, p);
		
		if(result > 0) {
			out.print("success");
			System.out.println("호두 충전 완료");
		} else {
			out.print("fail");
			response.sendRedirect("/crojecter/views/payment/payment.jsp");
			System.out.println("호두 충전 실패");
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
