package com.kh.adminpage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String condition = request.getParameter("con");
		String keyword = request.getParameter("keyword");
				
		ArrayList<Member> mlist = new ArrayList<Member>();
				
		MemberService ms = new MemberService();
				
		mlist = ms.searchMember(condition, keyword);
				
		String page = "";
				
		if (mlist != null) {
					
			page = "views/adminpage/memberlistView.jsp";
			request.setAttribute("mlist", mlist);
					
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
