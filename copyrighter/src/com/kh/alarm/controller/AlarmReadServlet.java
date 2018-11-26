package com.kh.alarm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.alarm.model.service.AlarmService;

/**
 * Servlet implementation class alarmRead
 */
@WebServlet("/aRead.al")
public class AlarmReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlarmReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 읽지않은 알람메세지 확인용 서블릿
		AlarmService as = new AlarmService();
		
		int unReadAlarm = 0;
		
		int mid = Integer.parseInt(request.getParameter("Mid"));
		
		unReadAlarm = as.countUnReadAlarm(mid);
		
		if(unReadAlarm < 0){
			System.out.println("조회할 알람메세지가 없습니다.");
		}
		else{
			PrintWriter out = response.getWriter();
			out.print(unReadAlarm);
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
