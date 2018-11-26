package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String url = request.getParameter("url");
		
		System.out.println(email);
		System.out.println(pwd);
		
		if(email == ""){
			System.out.println("이메일을 입력하세요.");
		}else if(pwd == null){
			System.out.println("비밀번호를 입력하세요.");
		}else{
			Member m = new Member(email,pwd);
			MemberService ms = new MemberService();
			
			m = ms.selectMember(m);
			
			System.out.println(m);
			if(m != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", m);
				response.sendRedirect("/crojecter");
				System.out.println("로그인 성공");
				
			} else {
				
				out.println("<script>");
				out.println("location.href='views/member/login.jsp';");
				out.println("alert('로그인 실패\\n이메일 또는 비밀번호가 일치하지 않습니다.');");
				out.println("</script>");
				//response.sendRedirect("views/member/login.jsp");
				System.out.println("로그인 실패");
			}
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
