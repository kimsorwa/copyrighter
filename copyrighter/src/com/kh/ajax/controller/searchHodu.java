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

/**
 * Servlet implementation class searchHodu
 */
@WebServlet("/searchHodu.sh")
public class searchHodu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchHodu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int mid = Integer.parseInt(request.getParameter("mid"));
		PrintWriter out = response.getWriter();
		
		Service s = new Service();
		Member m = new Member();
		m.setMid(mid);
		
		m = s.selectHodu(mid);
		
		if(m != null) {
			out.print(m.getMhodu());
		} else {
			System.out.println("호두 잔여량 불러오기 실패");
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
