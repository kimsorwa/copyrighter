package com.kh.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.follow.model.service.FollowService;
import com.kh.follow.model.vo.Follow;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class FollowingViewServlet
 */
@WebServlet("/followingView.do")
public class FollowingViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowingViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 팔로잉 목록 = 내가 좋아하는 사람들
		ArrayList<Follow> list = null;
		FollowService fs = new FollowService();
		
		int mid = Integer.parseInt(request.getParameter("mid")); //로그인한 사람
		int mpid = Integer.parseInt(request.getParameter("mpid")); //마이페이지 주인
		Member m = new MemberService().selectMember(mpid);
		
		list = fs.searchFollowing(mid, mpid);
		
		if(list != null){
			request.setAttribute("myMember", m);
			request.setAttribute("list", list);
			request.getRequestDispatcher("views/mypage/followingList.jsp").forward(request, response);
			
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
