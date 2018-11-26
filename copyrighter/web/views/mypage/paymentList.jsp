<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import = "com.kh.payment.model.vo.*, com.kh.member.model.vo.*, com.kh.spon.model.vo.*, java.util.*"%>
<% 
	ArrayList<Spon> slist = (ArrayList<Spon>)request.getAttribute("slist");
	ArrayList<Spon> rslist = (ArrayList<Spon>)request.getAttribute("rslist");
	ArrayList<Payment> plist = (ArrayList<Payment>)request.getAttribute("plist"); 
%>
<!DOCTYPE html>
<html>
 
<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
        .tables {
            width:400px;
            height: auto;
            padding: 20px 20px;
            margin-top:160px;
            margin:0 auto;
		    margin-bottom:160px;
		    float:left;
			width:33%;
            
        }
        .table {
            width: 400px;
            border: 1px solid black;
        }
        .table th {
            border: 1px solid black;
            background:lightgray;
            text-align:center;
            vertical-align:middle;
        }
        .table td {
           
            text-align:center;
            vertical-align:middle;
            
        }
        .subject {
            color:black;
            font-size: 20px;
            width:400px;
            text-align:center;
            magin-bottom:10px;
            
        }
</style>


</head>
<body>
<%@ include file="../mypage/common/mypageHeader.jsp" %>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="tables">
<label class="subject">후원 한 내역</label>
<table class="table">
	<tr> 
		<th>날짜</th>
		<th>받는이</th>
		<th>후원호두</th>
	</tr>
		<% for(Spon s : slist){ %>
	<tr>
		<td><%=s.getSdate()%></td>
		<td><%=s.getMname() %></td>
		<td><%=s.getShodu() %></td>
	</tr>
	<% } %>
</table>
</div>
<div class="tables">
<label class="subject">후원 받은 내역</label>
<table class="table">
	<tr> 
		<th>날짜</th>
		<th>후원자</th>
		<th>호두</th>
	</tr>
		<% for(Spon s :rslist){ %>
	<tr>
		<td><%=s.getSdate()%></td>
		<td><%=s.getMname() %></td>
		<td><%=s.getShodu() %></td>
	</tr>
	<% } %>
</table>
</div>
<div class="tables">
<label class="subject">충전 내역</label>
<table class="table">
	<tr> 
		<th>날짜</th>
		<th>충전 호두</th>
		<th>충전 금액</th>
	</tr>
	<% for(Payment p : plist){ %>
	<tr>
		<td><%=p.getPdate()%></td>
		<td><%=p.getPhodu() %></td>
		<td><%=p.getPmoney_1() %></td>
	</tr>
	<% } %>
</table>
</div>
	</div>
	<div class="col-md-2"></div>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>