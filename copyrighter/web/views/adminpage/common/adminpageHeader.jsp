<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
	
	.profileArea {
		border: 1px solid black;
		color: black;
		background: lightgray;
		text-align:center;
		font-weight:bold;
		font-size:23px;
		
	}
		.profileMenu{
			border: solid 1px black;
			text-decoration: none;
			color:black;
			background-color: lightgray;
			margin-left:10px;
			
		}

		.menu {
		border: 1px solid gray;
		padding-top:20px;
		padding-bottom:20px;

	}

		.menuMenu a {
		margin-left:15px;
		margin-right:40px;
		text-decoration: none;
		color: darkgray;
		font-size:23px;
		font-weight:bold;


	}

		.form{
			border:1px solid black;
		}

		#profileImg {
		width: 100px;
		height: 100px;
		border-radius: 50px;
		margin-top: 20px;
		margin-left: 20px;
		border: solid 1px gray;
	}	

</style>
<body>
<%@ include file="../../common/header.jsp"%>
	<div class="profileArea">
		<% if(m.getMprofile() == null) { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/images/user.png">
         <% } else { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/profileFiles/<%=m.getMprofile()%>">
         <% }  %>
		<p id="profileName"> 관리자 페이지</p>
	</div>
	<br>
	<div class="menu" align="center">
		<table>
			<tr>
				<td class="menuMenu"><a href="/crojecter/mListView.do">회원관리</a></td>
				<td class="menuMenu"><a href="/crojecter/paymentSelect.do">결제리스트</a></td>
				<td class="menuMenu"><a href="/crojecter/reportList.do">신고리스트</a></td>
			</tr>
		</table>
	</div><br>
</body>
</html>