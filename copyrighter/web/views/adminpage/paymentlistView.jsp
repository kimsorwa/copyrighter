<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.payment.model.vo.*, java.util.*"%>
<%
   ArrayList<Payment> plist = (ArrayList<Payment>)request.getAttribute("plist"); 
   System.out.println("plist : " + plist);
%>
<!DOCTYPE html>
<html>

<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
.outer {
	width: 900px;
	height: 600px;
	background: black;
	color: white;
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
}

.table {
	width: 500px;
	border: 1px solid black;
	text-align: center;
	margin-left: 40px;
	margin-right: 40px;
	margin-top: 40px;
}

.table th {
	color: white;
	background: darkgray;
	text-align: center;
}

.tableArea {
	width: 750px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}

.searchArea {
	width: 650px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="../adminpage/common/adminpageHeader.jsp"%>
	<div class="col-md-2"></div>
	<div class="col-md-8" style="margin-bottom: 50px; margin-top: 50px;">
		<div class="searchArea">
			<select id="searchCondition" name="searchCondition">
				<option value="name">이름</option>
				<option value="email">이메일</option>
			</select> <input type="search" id="keyword" placeholder="검색어를 입력하세요!">
			<button type="button" onclick="search();">검색하기</button>
		</div>
		<table class="table">
			<tr>
				<th>날짜</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>충전금액</th>
				<th>충전호두</th>
			</tr>

			<% for(Payment p : plist){ %>
			<tr>
				<td><%= p.getPdate() %></td>
				<td><%= p.getMname() %></td>
				<td><%= p.getMemail() %></td>
				<td><%= p.getPmoney() %></td>
				<td><%= p.getPhodu() %></td>
			</tr>
			<% } %>

		</table>
	</div>
	<div class="col-md-2"></div>


<script>
   function search(){
      location.href="<%=request.getContextPath()%>/searchPayment.do?con="
            +$('#searchCondition').val()+"&keyword="+$('#keyword').val();
   }
</script>
</body>
</html>