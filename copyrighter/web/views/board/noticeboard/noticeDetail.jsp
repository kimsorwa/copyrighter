<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.notice.model.vo.*, com.kh.member.model.vo.Member, java.util.*, com.kh.boardcomment.model.vo.*" %>

<%
   Notice n = (Notice)request.getAttribute("notice");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<style>

	.outer {
		margin-top: 50px;
		margin-bottom: 100px;
		padding: 20px;
		border: 1px solid lightgray;	
		background: white; 
		padding-top: 10px; 
		background-color: rgba( 255, 255, 255, 0.5 ); 

	}
	
	.bottomBtn {
	      width: 10%;
	      height: 25px;
	      background: lightpink;
	      border: 1px solid  lightpink;
	      border-radius: 5px;
	      color: white;
	      float: right;
	      margin-right:2px;
	   }
	   
	   body {
		font-family: 'Nanum Gothic', sans-serif;
		background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
	}
</style>

<title>공지사항 상세보기</title>
</head>
<body>
	<%@ include file="../../common/header.jsp"%>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6 outer">		
			<h4>[공지사항]</h4>
			<hr />
			<div>
				<span style="font-size:18px;"><%= n.getBtitle() %></span>
				<span style="float:right;"><%= n.getBdate() %></span>
			</div>
			<hr />
			<div  style="min-height: 400px">
			<p id="content"><%= n.getBcontent() %></p>
			</div>
			<hr />
	
			<div class="">
				<% if(m != null && m.getMid() == n.getBwriter()) { // 글쓴이 본인인 경우 %>
				<button class="bottomBtn" onclick="location.href='<%= request.getContextPath() %>/nUpView.no?bid='+<%=n.getBid()%>">수정하기</button>
				<button class="bottomBtn" onclick="location.href='<%= request.getContextPath() %>/nDelete.no?bid='+<%=n.getBid()%>">삭제하기</button>
				<br /> 
				<% } %>
				
			</div>
		<div class="col-md-3"></div>
		</div>
	</div>
	<%@ include file="../../common/footer.jsp"%>
</body>
</html>