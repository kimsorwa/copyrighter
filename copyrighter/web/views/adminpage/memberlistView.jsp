<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.*, java.util.*"%>
<%  
   ArrayList<Member> mlist = (ArrayList<Member>)request.getAttribute("mlist");
   System.out.println("mlist : " + mlist);
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
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
	margin-left: 200px;
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
				<th>회원번호</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>호두</th>
				<th>상태</th>
				<th>상태 변경</th>
			</tr>
			<% if(mlist.size() > 0) { %>
			<% for(Member m2 : mlist){ %>
			<tr>
				<td><%= m2.getMid()%></td>
				<td><%= m2.getMname()%></td>
				<td><%= m2.getMemail()%></td>
				<td><%= m2.getMhodu()%></td>
				<td><%= m2.getMstatus()%></td>
				<td><select name="status">
						<option value="1">활성</option>
						<option value="2">비활성</option>
						<option value="3">탈퇴</option>
				</select> <input type="hidden" name="mid" value="<%= m2.getMid()%>" />
					<button onclick="chageStatusSelect(this);">설정</button></td>
			</tr>
			<% } } else { %>
			<tr>
				<td colspan="5"><p>검색 결과가 존재하지 않습니다.</p></td>
			</tr>
			<% } %>
		</table>
		</div>
		<div class="col-md-2"></div>
		<script>

   function chageStatusSelect(obj){
       $.ajax({
          type:'get',
          url : '/crojecter/mStatusChange.do',
          data : {
             changeSel : $(obj).siblings('select').val(),
             chageMid : $(obj).siblings('input[name="mid"]').val()
          },      
          success : function(data){
             if(data == 'ok') {
                alert("회원 상태가 변경되었습니다.");
                 } else if (data == 'no') {
                   alert("회원 상태 변경에 실패하였습니다.");
                 }
          }
       });   
   }
   
   function search(){
      location.href="<%=request.getContextPath()%>/searchMember.do?con="
            +$('#searchCondition').val()+"&keyword="+$('#keyword').val();
   }

</script>
</body>
</html>