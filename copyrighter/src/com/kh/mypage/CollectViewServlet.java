package com.kh.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.likeit.model.service.LikeitService;
import com.kh.likeit.model.vo.Likeit;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class CollectViewServlet
 */
@WebServlet("/collectView.do")
public class CollectViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mid = Integer.parseInt(request.getParameter("mpid"));
		Member m = new MemberService().selectMember(mid);
		
		ArrayList<Likeit> list = null;
		list = new LikeitService().searchLikeit(mid);
		
		if(list != null){
			request.setAttribute("myMember", m);
			request.setAttribute("list", list);
			request.getRequestDispatcher("views/mypage/mycollectList.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "조회 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
