package com.kh.alarm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.alarm.model.service.AlarmService;
import com.kh.alarm.model.vo.Alarm;

/**
 * Servlet implementation class alarmListServlet
 */
@WebServlet("/aList.al")
public class AlarmListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlarmListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 요소
		ArrayList<Alarm> alarmList = null;
		AlarmService as = new AlarmService();
		int mid = Integer.parseInt(request.getParameter("Mid"));
		//System.out.println("Mid ser : "+ request.getParameter("Mid"));
		
		alarmList = as.selectAlarmList(mid);
		//System.out.println("AlarmListServlet alist : "+ alarmList);
		
		String page = "";
		
		
		if (alarmList != null){
			page = "views/alarm/alarmDetail.jsp";
			request.setAttribute("alarmList", alarmList);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "알람목록 조회실패");
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
