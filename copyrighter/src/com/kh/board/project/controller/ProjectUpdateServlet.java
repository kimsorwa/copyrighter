package com.kh.board.project.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.attachedfile.model.vo.AttachedFile;
import com.kh.board.gallery.model.vo.Gallery;
import com.kh.board.project.model.service.ProjectService;
import com.kh.board.project.model.vo.Project;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ProjectUpdateServlet
 */
@WebServlet("/jUpdate.pr")
public class ProjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectService ps = new ProjectService();
		
		if(ServletFileUpload.isMultipartContent(request)){
			// 만약 multipart/form-data 로 전송이 되었다면 실행해라!
			
			System.out.println("서블릿 들어왔니?");
			
			// 전송할 파일의 용량 선정
			int maxSize = 1024 * 1024 * 10;
			
			// 저장할 경로 설정하기
			String root = request.getServletContext().getRealPath("/resources");
			
			System.out.println("root 경로 확인 : " + root);
			
			String savePath = root + "/uploadFiles/";
			
			MultipartRequest mrequest =
					new MultipartRequest(request,savePath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
			
			// selectProjectMap(bid) : projectUpdate.jsp에서 받아온 bid로 Project와 AttachedFile을 불러온다.
			int bid = Integer.parseInt(request.getParameter("bid"));
			System.out.println("bid : " + bid);
			
			HashMap<String, Object> prj = ps.selectProjectMap(bid);
			
			// Project 객체 생성 후 DB 전달 VO 설정하기
			Project p = (Project)prj.get("project");
			
			System.out.println("userId : " + mrequest.getParameter("userId"));
			
			p.setBtype(3);
			p.setBtitle(mrequest.getParameter("title"));
			p.setBcontent(mrequest.getParameter("content"));
			p.setBwriter(Integer.parseInt(mrequest.getParameter("userId")));
			p.setJtag(mrequest.getParameter("tags"));
			
			if(mrequest.getParameter("date") != null){
			    String endDate = mrequest.getParameter("date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date jend;
				try {
					jend = new Date(sdf.parse(endDate).getTime());
					System.out.println("jend : " + jend);
					p.setJend(jend);
				} catch (ParseException e) {
					//e.printStackTrace();
				}
			}
			
			// Attachment 객체 생성 후 DB 전달 값 설정
			AttachedFile af= (AttachedFile)prj.get("attachedfile");
			File savefile = null;
			
			if(mrequest.getFile("thumbnailInput") != null){
				// 대표 이미지 영역의 파일만 불러온다.
				savefile = mrequest.getFile("thumbnailInput");
				System.out.println("thumbnailInput file : " + savefile);

				String path = savefile.getPath();
				String fileName = savefile.getName();  
				
				System.out.println("GalleryUpdateServlet path : " + path);				
				System.out.println("GalleryUpdateServlet fileName : " + fileName);	
			
				af.setFname(fileName); 
				af.setFpath(path); 
			
			}	

			// service로 작성한 내용 전송하기
			
			int result = ps.updateProject(p, af);
			
			if(result > 0) {
				response.sendRedirect("jSelectOne.pr?bid="+result*bid);
				
			} else {
				request.setAttribute("msg", "파일 전송 실패!");
				
				// 실패했을 때 이전 파일 정보 삭제하기
				savefile.delete();
				
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
