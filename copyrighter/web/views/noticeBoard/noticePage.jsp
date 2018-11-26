<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.notice.model.vo.*" %>
<%
	ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
	System.out.println("jsp noticeList"+ noticeList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항  페이지입니다.</title>
<style>

body {
	font-family: 'Nanum Gothic', sans-serif;
	background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
}

p {
	text-align: left;
	font-size: 24px;
}

.container  {
	margin-top: 100px;
	margin-bottom: 100px;
	text-align: center;
	background-color: rgba( 255, 255, 255, 0.5 );
	padding: 50px;

}

  table {
    width: 100%;
	border-top: 1px solid white;
    border-collapse: collapse;
  }
  
  th {
  	background-color: lightblue;
  
  }
  
  th, td {
    border-bottom: 1px solid white;    
  }

</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
		
		<div class="container" style="width: 600px; position: 50% absolute; padding-left: 50px;
	padding-right: 50px; padding-bottom: 100px;">
		<p>[공지사항]</p>
		
		<% System.out.println("Mid : "+ m.getMid()); %>
		<% if(noticeList != null){ %>
			<table align="center" id="noticeList" >
			<tr id="noticeTR">
				<th id="noticeTH" width="10px" style="text-align: center; padding: 5px;"></th>
				<th id="noticeTH" width="300px" style="text-align: center; padding: 5px;" >제목</th>
				<th id="noticeTH" width="150px" style="text-align: center; padding: 5px;">날짜</th>
				<th id="noticeTH" width="100px" style="text-align: center; padding: 5px;">조회수</th>
			</tr>
			<% for(Notice n : noticeList){ %>
			<tr id="noticeTR">				
				<td><input type="hidden" style="padding: 5px;" value="<%= n.getBid() %>"/></td>
				<td id="noticeTD" style="padding: 5px;"><%= n.getBtitle() %></td>
				<td id="noticeTD" style="padding: 5px;"><%= n.getBdate() %></td>
				<td id="noticeTD" style="padding: 5px;"><%= n.getBcount() %></td>
			</tr>
			<% } %>
	
			</table>
		<% } else { %>
				현재 공지사항이 없습니다.
		<% } %>
		</div>
		<% if(m != null && m.getMname().equals("관리자")){ %>
		<!-- 인라인 호출방식 -->
		<button onclick="location.href='<%= request.getContextPath() %>/views/board/noticeboard/noticeInsert.jsp'">작성하기</button>
		<% } %>

		<a name="bottom"></a>
		<script>
		// 성일씨 이거 이벤트 좀 무난하게 변경해주세욥~!
		$(function(){
			$("#noticeList td").mouseenter(function(){				
				$(this).parent().css({"background":"purple", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"lightgray"});
			}).click(function(){
				var bid = $(this).parent().children().eq(0).val();
				console.log(bid);
				location.href='<%=request.getContextPath()%>/nSelectOne.no?bid='+bid;
			});
		});
		//
		</script>
				<!-- 리모컨 -->
		<div style="position: fixed; right: 50%; top: 80%; margin-right: -720px; text-align:center; width: 10%;">
		<button><a href="#top"><img src="<%= request.getContextPath()%>/resources/images/icon/up.png" alt="" style="height:30px;"></a></button><br>
		<button><a href="#bottom"> <img src="<%= request.getContextPath()%>/resources/images/icon/down.png" alt="" style="height:30px;"></a></button>
		</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>