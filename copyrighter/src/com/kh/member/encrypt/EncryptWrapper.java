package com.kh.member.encrypt;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String name) {
		// getParameter로 들어오는 요청 중
		// 비밀번호를 요청할 경우
		// 암호화 하여 전달한다.
		
		String value = "";
		
		if (name != null && (name.equals("password") || name.equals("tempPassword"))){
			// 암호화 된 비밀번호를 전달한다.
			value = getSHA512(super.getParameter(name));
		} else {
		    // 원래의 값을 전달한다.
			value = super.getParameter(name);
			
		}
		return value;
	}

	private static String getSHA512(String password){
		String result = null;
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
			
			md.update(bytes);
			
			result = Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}








