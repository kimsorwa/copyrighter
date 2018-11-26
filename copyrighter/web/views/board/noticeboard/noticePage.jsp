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

/* .container  {
	margin-top: 100px;
	margin-bottom: 100px;
	text-align: center;
	background-color: rgba( 255, 255, 255, 0.5 );
	padding: 50px;
	min-height: 500px;

} */

  table {
    width: 100%;
	border-top: 1px solid white;
    border-collapse: collapse;
  }
  
  th {
  	background-color: lightgray;
  
  }
  
  th, td {
    border-bottom: 1px solid white;    
  }
  
  button {
  	  height: 30px;
  	  background: lightblue;
      border: 1.5px solid lightblue;
  }

</style>
</head>
<body>
	<%@ include file="../../common/header.jsp"%>
		
		<div class="container" style="width: 600px; position: 50% absolute; padding-left: 50px;
		padding-right: 50px; padding-bottom: 100px; margin-top: 100px;">
		<div class=row">
			<div class="col-md-9"><p>[공지사항]</p></div>
			<div class="col-md-3" style="padding-top: 5px;">
			<% if(m != null && m.getMid()==-1){ %>			
			<button style="border-radius: 5px;" 
					onclick="location.href='<%= request.getContextPath() %>/views/board/noticeboard/noticeInsert.jsp'">
				작성하기</button>
			<% } %>
			</div>
		</div>
		
		<% if(m != null){ %>
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
			<% } else { %>
				<% if(noticeList != null){ %>
				<table align="center" id="noticeList" >
				<tr id="noticeTR">
					<th id="noticeTH" width="10px" style="text-align: center; padding: 5px;"></th>
					<th id="noticeTH" width="300px" style="text-align: center; padding: 5px;" >제목</th>
					<th id="noticeTH" width="150px" style="text-align: center; padding: 5px;">날짜</th>
					<th id="noticeTH" width="100px" style="text-align: center; padding: 5px;">조회수</th>
				</tr>
				<% for(Notice n : noticeList){ %>
				<tr class="noticeTR">				
					<td>
						<input type="hidden" style="padding: 5px;" value="<%= n.getBid() %>"/>
					</td>
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
		<% } %>
		<a name="bottom"></a>
		<script>
		 $(function(){
	         $("#noticeList td").mouseenter(function(){            
	            $(this).parent().css({"background":"white", "cursor":"pointer"});
	         }).mouseout(function(){
	            $(this).parent().css({"background":""});
	         }).click(function(){
	            var bid = $(this).parent().children().eq(0).children().val();
	            location.href="<%=request.getContextPath()%>/nSelectOne.no?bid="+ bid;            
	         });
     	});
		//
		</script>

	<%@ include file="../../common/footer.jsp"%>
</body>
</html>