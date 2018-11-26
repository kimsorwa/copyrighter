package com.kh.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/mUpdateProfile.me")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		if (ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;

			// 3. 웹 상의 루트(최상위) 경로를 활용하여 저장할 폴더 위치를 선정한다.
			String root = request.getServletContext().getRealPath("/");
			String savePath = root + "resources/profileFiles";
			System.out.println("savePath : " + savePath);
	
			MultipartRequest mrequest = new MultipartRequest(
					request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());

			String fileName = mrequest.getFilesystemName("file");
			System.out.println("filename : " + fileName);
			
			HttpSession session = request.getSession(false);
			Member m = (Member)session.getAttribute("member");
			m.setMprofile(fileName);
			
			int result = new MemberService().updateProfile(m);
	
			if (result > 0) {	
				request.setAttribute("myMember", m);
				request.getRequestDispatcher("views/mypage/mypageView.jsp").forward(request, response);	
			} else {	
				request.setAttribute("msg", "게시글 작성 실패!");	
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);	
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
