package com.kh.adminpage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.payment.model.service.PaymentService;
import com.kh.payment.model.vo.Payment;

/**
 * Servlet implementation class SearchPaymentServlet
 */
@WebServlet("/searchPayment.do")
public class SearchPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String condition = request.getParameter("con");
		String keyword = request.getParameter("keyword");
						
		ArrayList<Payment> plist = new ArrayList<Payment>();
						
		PaymentService ps = new PaymentService();
						
		plist = ps.searchPayment(condition, keyword);
						
		String page = "";
						
		if (plist != null) {
							
			page = "views/adminpage/paymentlistView.jsp";
			request.setAttribute("plist", plist);
							
		} else {
							
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "검색 실패!");
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
