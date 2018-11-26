<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.alarm.model.vo.*, java.util.*" %>
<% 
	// 읽지않은 알람목록 조회용
	ArrayList<Alarm> alarmList = (ArrayList<Alarm>)request.getAttribute("alarmList");
	System.out.println("alarmDetail alist : "+ alarmList);
	System.out.println("alarmDetail sz : "+ alarmList.size());
	int Mid = Integer.parseInt(request.getParameter("Mid"));
	int result = 0;
	int count = 0;
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<title>알람메세지 페이지입니다.</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<style>
		#alarmTB, #alarmTR, #alarmTB {background:lightyellow; border:solid 3px;}
		
		body {
		font-family: 'Nanum Gothic', sans-serif;
		background-color: #fdf5e6  ;
		}
		
		.wrapper{
		margin:10px auto;
		border:1px solid lightgray;
		border-radius:20px;
		background-color: white;
		padding-bottom: 20px;
		}
		
		th {
  			background-color: white;
  
  		}
  
  		th, td {
   			border-bottom: 1px solid lightgray;    
 		}
 		
 		button {
			color:white;
			padding: 0;
			text-align:center;
			border-radius:5px;
			background-color:#30B2A0;
			border:1px solid #30B2A0;
 		
 		}
	</style>
</head>
<body>
	<p style="padding-left: 20px; margin-bottom: 0; font-size: 18px; font-weight: bold;">[알람]</p>
	<div class="wrapper">
		<br>
		<script>
		$(function(){
			console.log(sessionStorage.getItem("myAlarmList"));
		});
		</script>
		
		<div class="tableArea">
		<% if(alarmList != null){ %>
			<table align="center" id="alarmList">
			<tr>
				<th id="alarmTH">내용</th>
				<th id="alarmTH">날짜</th>
				<th id="alarmTH">확인</th>
			</tr>
			<% for(Alarm al : alarmList){ %>
				<% if(al.getAFlag().equals("Y")) { %>
				<tr>
					<td><%= al.getAMsg() %></td>
					<td><%= al.getADate() %></td>
					<td><button onclick="updateAlarm(this, '<%=al.getAid()%>');" style="font-size:15px;">미확인</button></td>
				</tr>
				<% count++; } %>
			<% } %>
			<% if(count == 0) { %>
			<tr>
				<td colspan="3">확인하지 않은 알람이 없습니다.</td>
			</tr>
			<% } else { count = 0; } %>			
			</table>
			<script>

			function updateAlarm(obj, alarmNo){
					$.ajax({
						data : { Aid : alarmNo},
						url : "/crojecter/aUpdate.al",
						type : "post",
						async: false,
						success : 
							function(data){ 
							if (data > 0){
								obj.parent().parent().remove();
							}
						}
					});
					location.reload();
			}
			
				</script>
		<% } else { %>
			현재 전송된 알람이 없습니다.
		<% } %>
		
		</div>
		</div>
		
		<script>
			$(function(){
				$("#alarmList td").mouseenter(function(){
					var bno = $(this).parent().find("input").val();
					location.href="<%=request.getContextPath()%>/aList.al?Aid=" + Aid;
				});
			});
			
			var result = 0;
			<%-- 
			$(document).ready(function(){
				$.ajax({
					data : { Mid : <%= mid %>},
					url : "/crojecter/aList.al",
					type : "post",
					success : function(data){}
				});
			}); --%>
		</script>
		</div>

</body>
</html>