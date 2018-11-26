package com.kh.board.attachedfile.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AttachedFileInsertServlet
 */
@WebServlet("/sInsert.fo")
public class SummernoteImageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SummernoteImageViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// SummernoteImageViewServlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 1024 * 1024 * 10;

			// 저장할 경로 설정하기
			String root = request.getServletContext().getRealPath("/resources");

			System.out.println("root 경로 확인 : " + root);

			String url = request.getContextPath() + "/resources/uploadFiles/";
			String savePath = root + "/uploadFiles/";

			MultipartRequest srequest = 
					new MultipartRequest(request, savePath, maxSize, "UTF-8",
							new DefaultFileRenamePolicy());

			// 폼으로 전송된 파일 이름들을 받아온다.
			Enumeration<String> files = srequest.getFileNames();

			JSONObject jobj = new JSONObject();

			String uploadPath = "";

			// 각 파일의 정보를 가져와서 DB에 저장할 내용을 추출한다.
			String name = files.nextElement();
			String fileName = srequest.getFilesystemName(name);

			uploadPath = url + fileName;

			jobj.put("url", uploadPath);

			System.out.println("summernote stream uploadPath : " + uploadPath);
			System.out.println("summernote stream upload img name : " + fileName);

			response.setContentType("text/html; application/json; charset=UTF-8"); // 데이터 타입을 json으로 설정하기															// 위한 세팅

			
			//response.getWriter().print(jobj.toJSONString());
			response.getWriter().print(uploadPath);
			
			

		} else {
			System.out.println("섬머노트 이 바보야!!!");
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
