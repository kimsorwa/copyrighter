<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
.form {
	width: 300px;
	margin: 0 auto;
	margin-top: 100px;
	margin-bottom: 160px;
	padding: 10px 10px 10px 10px;
	background:white;
	border-radius: 5px;
	
}

.label {
	color: red;
	text-align: center;
	font-size: 10px;
	width: 130px;
	margin: 1px 10px 1px 10px;
}

.myTable{

	font-size:15px;
	width:auto;
	height: 300px;
	color:black;
	background: white;

	

}


.tdStyle{

color:black;
width:auto;
text-align:center;


}

.tdArea {

width:150px;
text-align:center;
}

		th {
  			background-color: white;
  
  		}
  
  		th, td {
   			border-bottom: 1px solid lightgray;    
   			background-color: white;
 		}
 		
 		td {
 			padding-left: 10px; padding-right: 10px;
 		}


</style>

</head>
<body>
	<%@ include file="../mypage/common/mypageHeader.jsp"%>
	<div class="form" style="padding:0; border: 1px solid lightgray;">
			<table class="myTable" align="center">
				<tr>
					<td class="tdArea"><label class="tdStyle">닉네임</label></td>
					<td colspan="2" ><%=m2.getMname()%></td>
				</tr>
				<tr>
					<td class="tdArea"><label class="tdStyle">이메일</label></td>
					<td colspan="2" ><%=m2.getMemail()%></td>
				</tr>
				<tr>
					<td class="tdArea"><label class="tdStyle">호두</label></td>
					<td colspan="2"><%=m2.getMhodu()%></td>
				</tr>
				<tr>
					<td class="tdArea"><label class="tdStyle">가입일</label></td>
					<td colspan="2"><%=m2.getMdate()%></td>
				</tr>
			</table>
	</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>