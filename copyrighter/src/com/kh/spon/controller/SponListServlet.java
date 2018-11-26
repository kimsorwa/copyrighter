package com.kh.spon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.spon.model.service.SponService;
import com.kh.spon.model.vo.Spon;

/**
 * Servlet implementation class SelectPaymentList
 */
@WebServlet("/sponSelect.do")
public class SponListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Spon> list = null;
		SponService ss = new SponService();
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		list = ss.selectlist(mid);
		
		if(list != null){
			
			request.setAttribute("slist", list);
			request.getRequestDispatcher("views/mypage/paymentList.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msg", "조회 실패!");
			
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
