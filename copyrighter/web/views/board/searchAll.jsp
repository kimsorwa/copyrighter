<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.board.common.model.vo.*"%>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	System.out.println("jsp bList : " + bList);
	int count = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
	.selectNotice:hover{
		/* background-color:#E4FCD6; */
		background-color:#E9ECE7;
		cursor:pointer
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<% if(bList == null) { %>
		검색결과가 없습니다.
	<% } else { %>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8" align="right"><label>검색 결과 총 "<%= bList.size() %>"개 입니다.</label></div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<hr style="height:3px; background:gray;"/>
		<h3 style="text-align:center;">갤러리</h3>
		<br />		<% for (Board b : bList) { %>
			<% if(b.getBtype() == 2) { %>
			<div id="gal-list" class="card" style="width: 300px; height: auto; display: inline-block;">
			<a href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= b.getBid() %>">
			<% if(b.getFName() != null) { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/uploadFiles/<%= b.getFName() %>">
			<% } else { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/images/icon/upload.png">
			<% } %>
			</a>
				<div class="card-body">
					<h5 class="card-title"><%= b.getMname() %> | <a style="text-decoration:none; color:black;" href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= b.getBid() %>"><%= b.getBtitle() %></a></h5>
					<% if(b.getgTag() != null) {
						String tags[] = b.getgTag().split(",");
						for(int i = 0; i < tags.length; i++) { %>
						<a style="text-decoration:none;" href="<%= request.getContextPath()%>/search.all?keyword=<%= tags[i] %>">#<%= tags[i] %></a>
						<% }
					} else {
						
					} %>
					<br>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/view.png" style="height:22px;"><%= b.getBcount() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/like.png" style="height:22px;"><%= b.getLikeCnt() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/reply.png" style="height:22px;"><%= b.getCommCnt() %>
					</label>
				</div>
			</div>
			<% count ++; } %>
		<% } %>
		<% if(count == 0) { %>
		<h4 style="margin-left:30px;">갤러리 검색결과가 없습니다.</h4>
		<% } else { count = 0; } %>
		<hr style="height:3px; background:gray;"/>
		<h3 style="text-align:center;">프로젝트</h3>
		<br />
		<% for (Board b : bList) { %>
			<% if(b.getBtype() == 3) { %>
			<div id="gal-list" class="card" style="width: 300px; height: auto; display: inline-block;">
			<a href="<%= request.getContextPath()%>/jSelectOne.pr?bid=<%= b.getBid() %>">
			<% if(b.getFName() != null) { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/uploadFiles/<%= b.getFName() %>">
			<% } else { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/images/icon/upload.png">
			<% } %>
			</a>
				<div class="card-body">
					<h5 class="card-title"><%= b.getMname() %> | <a style="text-decoration:none; color:black;" href="<%= request.getContextPath()%>/jSelectOne.pr?bid=<%= b.getBid() %>"><%= b.getBtitle() %></a></h5>
					<% if(b.getjTag() != null) {
						String tags[] = b.getjTag().split(",");
						for(int i = 0; i < tags.length; i++) { %>
						<a style="text-decoration:none;" href="<%= request.getContextPath()%>/search.all?keyword=<%= tags[i] %>">#<%= tags[i] %></a>
						<% }
					} else {
						
					} %>
					<br>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/view.png" style="height:22px;"><%= b.getBcount() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/like.png" style="height:22px;"><%= b.getLikeCnt() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/reply.png" style="height:22px;"><%= b.getCommCnt() %>
					</label>
				</div>
			</div>
			<% count ++; } %>
		<% } %>
		<% if(count == 0) { %>
		<h4 style="margin-left:30px;">프로젝트 검색결과가 없습니다.</h4>
		<% } else { count = 0; } %>
		<hr style="height:3px; background:gray;"/>
		<h3 style="text-align:center;">공지사항</h3>
		<br />
		<table style="width:600px; height:30px; margin:auto;">
			<tr>
				<th style="width:50px; text-align:center;">글번호</th>
				<th style="width:260px; text-align:center;">제목</th>
				<th style="width:80px; text-align:center;">작성자</th>
				<th style="width:140px; text-align:center;">작성일</th>
				<th style="width:50px; text-align:center;">조회수</th>
			</tr>
		</table>
		<% for (Board b : bList) { %>
			<% if(b.getBtype() == 1) { %>
			<table style="width:600px; height:30px; text-align:center; margin:auto;">
				<tr class="selectNotice" onclick="location.href='<%= request.getContextPath()%>/nSelectOne.no?bid=<%= b.getBid() %>'">
					<td style="width:50px;"><%= b.getBid() %></td>
					<td style="width:260px;"><%= b.getBtitle() %></td>
					<td style="width:80px;"><%= b.getMname() %></td>
					<td style="width:140px;"><%= b.getBdate() %></td>
					<td style="width:50px;"><%= b.getBcount() %></td>
				</tr>
			</table>
			<% count ++; } %>
		<% } %>
		<% if(count == 0) { %>
		<h4 style="margin-left:30px;">공지사항 검색결과가 없습니다.</h4>
		<% } else { count = 0; } %>
		<hr style="height:3px; background:gray;"/>
	<% } %>
	</div>
	<div class="col-md-2"></div>
	</div>
	<br />
	<%@ include file="../common/footer.jsp" %>
</body>
</html>