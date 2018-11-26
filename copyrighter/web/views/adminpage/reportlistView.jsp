<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.report.model.vo.*, java.util.*"%>
<%
   ArrayList<Report> rlist = (ArrayList<Report>)request.getAttribute("rlist"); 
   System.out.println("rlist : " + rlist);
%>
<!DOCTYPE html>
<html>

<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>


   .table {
      width:800px;
      border:1px solid black;
        border-radius: 3px;
        margin-left:40px;
        margin-right:40px;
        text-align:center;
   }

    table th {
        color:white;
        background:darkgray;
        text-align:center;

    }

   .searchArea {
      width:650px;
      margin-left:600px;
   }
</style>

</head>
<body>
<%@ include file="../adminpage/common/adminpageHeader.jsp" %>
<div class="col-md-2"></div>
   <div class="col-md-8" style="margin-bottom: 50px; margin-top: 50px;">
<!-- <div class="searchArea">
   <select id="searchList" name="searchList">
      <option>---</option>
      <option value="date">날짜순</option>
      <option value="comment">사유순</option>
   </select>
</div> -->
<div>
<table class="table">
   <tr>
      <th>날짜</th>
      <th>사유</th>
      <th>신고자</th>
      <th>작성자</th>
      <th>게시물 번호</th>
      <th>댓글 번호</th>
    </tr>
    <% for(Report r : rlist){ %>
   <tr>
      <td><%= r.getRdate() %></td>
      <td><%= r.getRetc() %></td>
      <td><%= r.getMname() %></td>
      <td><%= r.getCwriter() %></td>      
      <td><%= r.getBid() %></td>
      <td><%=   r.getCid()%></td>
   </tr>
   <% } %>
</table>
</div>
<div class="col-md-2"></div>
</body>
</html>