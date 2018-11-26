<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.*"
	import="com.kh.alarm.model.vo.*, java.util.*"%>
<%
	Member m = (Member) session.getAttribute("member");
	// 읽지않은 알람갯수 조회용
	ArrayList<Alarm> aList = (ArrayList<Alarm>) request.getAttribute("aList");
	System.out.println("Header alist : " + aList);
	Alarm al = (Alarm) request.getAttribute("Alarm");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- bootstrap css include -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- bootstrap js include -->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<!-- 폰트 설정 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic"
	rel="stylesheet">


<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/js-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<!-- tagsinput -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap-tagsinput/bootstrap-tagsinput.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap-tagsinput/assets/app.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>


<style>
.sign {
	font-size: 18px;
	font-weight: bold;
	color: black;
	margin-left: 5px;
	margin-right: 5px;
	padding: 15px;
	font: bolder;
}

.sign:hover {
	color: #30B2A0;
	font-weight: bold;
}

.navbar {
	margin-bottom: 0;

}

</style>

</head>

<body>
	<nav class="navbar navbar-expand-lg"
		style="background-image: none; background-color: white;">
		<div class="container" style="">
			<!-- 로고 -->
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/gList.ga" style="padding:0;"> 
				<img src="<%=request.getContextPath()%>/resources/images/icon/LogoImage.png"
					 style="width: auto; height: 50px;"></a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<!-- 왼쪽 -->
				 <ul class="nav navbar-nav navbar-left">
					<!-- 갤러리 -->
					<li>
						<a class="sign" style="margin-left: 30px;" href="<%=request.getContextPath()%>/gList.ga">갤러리</a>
					</li>					
					<!-- 프로젝트 -->
					<li>
						<a class="sign" href="<%=request.getContextPath()%>/pList.pr">프로젝트</a>
					</li>
				
				</ul>
				<!-- 오른쪽 -->
				<ul class="nav navbar-nav navbar-right">
				<% if (m == null) { %>									
				<li><a href="<%=request.getContextPath()%>/views/member/login.jsp">Sign In</a></li>
				<li><a href="<%=request.getContextPath()%>/views/member/signUp.jsp">Sign Up</a></li>
					
				<%} else { %>
				
				<li style="">
					<button type="button" data-target="#myModal" onclick="openAlarmList();"
							style="height: 20px; background-color: white; border: 0px;
							padding: 15px; margin: 0;">
						<img src="<%=request.getContextPath()%>/resources/images/icon/alarm.png" style="height: 20px"> 
						<span class="badge badge-light" id="countUnreadAlarm"></span>
					</button>
					<script>
						function openAlarmList() {							
							// 알람창 크기
								var xPos = ((document.body.clientWidth / 2) - (500 / 2)); 
								xPos += window.screenLeft;
								var yPos = ((screen.availHeight / 2) - (300 / 2));
								window.open('<%=request.getContextPath()%>/aList.al?Mid=<%=m.getMid()%>', 
											알람', 'width=500,height=300,top='+yPos+',left='+xPos
											+',toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
							}
					</script>
				</li>
					
					

				<!-- 프로필 -->
				<li class="nav-item dropdown" style="">
						<a href="<%=request.getContextPath()%>#" id="navbarDropdown"
						   role="button" data-toggle="dropdown" aria-haspopup="true"
						   aria-expanded="false" style="height: auto; text-decoration: none">
						<%if (m.getMprofile() == null) {%> 
							<img src="<%=request.getContextPath()%>/resources/images/user.png"
								 style="height: 20px;" class="rounded-circle" alt="Cinque Terre">
						<% } else {%> 
							<img src="<%=request.getContextPath()%>/resources/profileFiles/<%=m.getMprofile()%>"
								 style="height: 20px;" class="rounded-circle" alt="Cinque Terre">
						<% } %>
						</a>
							
						
								<ul class="dropdown-menu" role="menu">
									
								
							
							
							<% if (m.getMid() != -1) { %>
							<li><a class="dropdown-item"
							   href="<%=request.getContextPath()%>/views/payment/payment.jsp">호두충전</a></li>
							<li><a class="dropdown-item"
							   href="<%=request.getContextPath()%>/selectPage.my?mid=<%=m.getMid()%>">마이페이지</a></li>
							<%} else { %>
							<li><a class="dropdown-item"
							   href="<%=request.getContextPath()%>/mListView.do">관리자페이지</a></li>
							<% } %>
							<li><a class="dropdown-item" onclick='logout();'>로그아웃</a></li>
							
							<script>
								function logout(){
									location.href="/crojecter/logout.me";
								}
							</script>
							
							<% if (m.getMid() != -1) { %>
					 		<li>
								<a class="dropdown-item" href="#"> 
								<img src="<%=request.getContextPath()%>/resources/images/icon/walnut.jpg"
									 alt="" style="height: 30px;"><span id="callHodu"></span>개
								</a>
								<script>
									$(document).ready(function(){
										callHodu();
										callAlarm();
									});
															
									function callHodu(){
										$.ajax({
											url : "/crojecter/selectHodu.sh",
											data : { mid : <%=m.getMid()%> },
											type : "post",
											success : function(data){
												$("#callHodu").text(data);
											}
										});
										setTimeout("callHodu()", 1000);
									}
															
									function callAlarm(){
										$.ajax({
											data : { Mid : <%=m.getMid()%>},
											url : "/crojecter/aRead.al",
											type : "post",
											success : function(data){
												$("#countUnreadAlarm").text(data);
											}
										});
										setTimeout("callAlarm()", 1000);
									}
								</script>
								</li>
								<% }%>
								</ul>
								
							
						
					</li>
						
					<li>
					<!-- 글쓰기 --> 
						<a id="moveInsert" href="<%=request.getContextPath()%>/views/board/galleryboard/galleryInsert.jsp"
						   style="height: 20px;"> 
						<script>
							var x = location.href;
							$(function(){
								if(x == "http://localhost:8088/crojecter/pList.pr" || x == "http://192.168.20.21:8088/crojecter/pList.pr"){
									$("#moveInsert").attr('href','<%=request.getContextPath()%>/views/board/projectboard/projectInsert.jsp');
								}
							});			
						</script> 
						<img src="<%=request.getContextPath()%>/resources/images/icon/upload.png"
							 alt="" style="height: 20px;"></a> &nbsp;&nbsp;
					</li>
				
				<% } %>

				<!-- 검색 -->
				<form class="navbar-form navbar-right" role="search" 
					 action="<%=request.getContextPath()%>/search.all" method="get"
					 style="padding: 15px; margin: 0;">
					<input type="text" class="search-query" placeholder="Search"
						   name="keyword" style="border-radius: 5px;border: 2px solid lightgray;">
					<button class="btn my-2 my-sm-0" style="background: white; padding:0;" type="submit">
						<img src="<%=request.getContextPath()%>/resources/images/icon/search.png" alt="" style="height: 20px;">
					</button>
				</form>
			</ul>
				
			</div>
		</div>
	</nav>
	
	
	
	
</body>
</html>
