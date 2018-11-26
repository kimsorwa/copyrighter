package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class sendEmail
 */
@WebServlet("/send.do")
public class sendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendEmail() {
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
		// getParameter() 로 가져온 부분만 암호화 복호화가 됨.
		// jsp 부분에서 임시비밀번호 발생시켜서 가져오기.
		
		MemberService ms = new MemberService();
		Member m = new Member();
		
		m.setMname(nickName);
		m.setMemail(email);
		
		int result = ms.selectEmail(m);
		
		if(result > 0) {
			
			// 임시비밀번호로 업데이트
			String tempPwd = request.getParameter("tempPassword");
			
			String whatPwd = request.getParameter("tempPwd");
			System.out.println("임시 비밀번호 : " + whatPwd);
			
			int resultPwd = ms.updateRandomPwd(tempPwd, m);
			
			if(resultPwd > 0) {
				response.sendRedirect("/crojecter/views/member/login.jsp");
				System.out.println("임시비밀번호로 수정 성공");
				// 이메일 전송
				gmailSend(nickName,whatPwd, email);
				
			} else {
				
				out.println("<script>");
				out.println("alert('임시비밀번호 수정 실패');");
				out.println("history.back(-1);");
				out.println("</script>");
				
				System.out.println("임시비밀번호로 수정 실패");
			}
		} else {
			
			out.println("<script>");
			out.println("alert('이메일 또는 닉네임이 일치하지 않습니다.');");
			out.println("history.back(-1);");
			out.println("</script>");
			
			System.out.println("이메일 전송 실패");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void gmailSend(String id, String pwd, String email) {
		String user = "crojecter@gmail.com";
		String password = "gooCrojecter1!";

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// 메일 제목을 입력
			message.setSubject("Crojecter 임시 비밀번호 발송");

			// 메일 내용을 입력
			message.setText(id + "님의 임시 비밀번호는 ' " + pwd + " ' 입니다.");

			// 전송
			Transport.send(message);
			System.out.println("이메일 전송 성공");
			
		} catch (AddressException e) {

			e.printStackTrace();
			System.out.println("이메일 전송 실패");
			
		} catch (MessagingException e) {

			e.printStackTrace();
			System.out.println("이메일 전송 실패");
			
		}
		
	}
	
}
