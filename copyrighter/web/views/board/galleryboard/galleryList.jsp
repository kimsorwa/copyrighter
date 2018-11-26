<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.board.gallery.model.vo.*" %>
 
<%
	ArrayList<Gallery> gList = (ArrayList<Gallery>)request.getAttribute("glist");
	ArrayList<Gallery> gList2 = (ArrayList<Gallery>)request.getAttribute("gList2");
	System.out.println("gList2 : " + gList2);
%> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.card-img-top {
    height: 300px;
    
}

</style>
</head>

<body style="height:100%; margin-bottom: 100px;">
		<div class="card-columns">
		<% if(gList == null) { %>
			<% if(gList2 == null) { %>
				해당 게시글이 존재하지 않습니다.
			<% } else { %>
			<% for (Gallery g : gList2) { %>
			<div id="gal-list" class="card" style="width: 350px; height: auto; display: inline-block; margin:10px;">
			<a href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= g.getBid() %>">
			<% if(g.getFName() != null) { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/uploadFiles/<%= g.getFName() %>">
			<% } else { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/images/icon/upload.png">
			<% } %>
			</a>
				<div class="card-body">
					<h5 class="card-title" style="height:40px;"><%= g.getMname() %> | <a style="text-decoration:none; color:black;" href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= g.getBid() %>"><%= g.getBtitle() %></a></h5>
					<% if(g.getgTag() != null) {
						String tags[] = g.getgTag().split(",");
						for(int i = 0; i < tags.length; i++) { %>
						<a style="text-decoration:none;" href="<%= request.getContextPath()%>/search.all?keyword=<%= tags[i] %>">#<%= tags[i] %></a>
						<% }
					} else {
						
					} %>
					<br>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/view.png" style="height:22px;"><%= g.getBcount() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/like.png" style="height:22px;"><%= g.getLikeCnt() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/reply.png" style="height:22px;"><%= g.getCommCnt() %>
					</label>
				</div>
			</div>
		<% } %>
			<% } %>
		<% } else { %>
		<% for (Gallery g : gList) { System.out.println("gllist : "+g.getBid());%>
		
			<div id="gal-list" class="card" style="width: 350px; height: auto; display: inline-block; margin:10px;">
			<a href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= g.getBid() %>">
			<% if(g.getFName() != null) { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/uploadFiles/<%= g.getFName() %>">
			<% } else { %>
			<img class="card-img-top" src="<%= request.getContextPath()%>/resources/images/icon/upload.png">
			<% } %>
			</a>
				<div class="card-body">
					<h5 class="card-title"><%= g.getMname() %> | <a style="text-decoration:none; color:black;" href="<%= request.getContextPath()%>/gSelectOne.ga?bid=<%= g.getBid() %>"><%= g.getBtitle() %></a></h5>
					<% if(g.getgTag() != null) {
						String tags[] = g.getgTag().split(",");
						for(int i = 0; i < tags.length; i++) { %>
						<a style="text-decoration:none;" href="<%= request.getContextPath()%>/search.all?keyword=<%= tags[i] %>">#<%= tags[i] %></a>
						<% }
					} else {
						
					} %>
					<br>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/view.png" style="height:22px;"><%= g.getBcount() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/like.png" style="height:22px;"><%= g.getLikeCnt() %>
					</label>
					<label>
					<img src="<%= request.getContextPath()%>/resources/images/icon/reply.png" style="height:22px;"><%= g.getCommCnt() %>
					</label>
				</div>
			</div>
		<% } %>
		<% } %>
		</div>

</body>
</html>