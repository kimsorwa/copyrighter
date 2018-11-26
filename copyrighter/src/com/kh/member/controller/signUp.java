package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class signUp
 */
@WebServlet("/signUp.do")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Member m = new Member(email, password, nickName);
		
		MemberService ms = new MemberService();
		
		int result = ms.signUpMember(m);
		
		if(result > 0) {
			System.out.println("회원가입 성공");
			response.sendRedirect("/crojecter/views/member/login.jsp");
			request.setAttribute("member", m);
		} else {
			
			out.println("<script>");
			out.println("alert('회원가입 실패');");
			out.println("history.back(-1);");
			out.println("</script>");
			System.out.println("회원가입 실패");
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
