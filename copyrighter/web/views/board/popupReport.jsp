<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.notice.model.vo.*, com.kh.board.notice.model.service.*, com.kh.member.model.vo.Member, 
				 com.kh.boardcomment.model.vo.*, com.kh.boardcomment.model.service.*" %>
<%
	Member m = (Member)session.getAttribute("member");
	int bid = Integer.parseInt(request.getParameter("b"));
	int cid = Integer.parseInt(request.getParameter("c"));
	Notice b = new NoticeService().selectOne(bid);
	BoardComment bc = new BoardCommentService().selectOne(cid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<title>신고하기</title>
<style>

</style>
</head>
<body style="width:400px; margin:auto; margin-top:40px;">
	<div align="center" style="width:400px"> 
		<p style="font-size:25px; text-align:center;">신고하기</p> 
		<hr />
		<p class="writerP" style="font-size:15px; text-align:left;" >작성자 : <span id="rWriter"></span></p>
		<p class="conP" style="font-size:15px; text-align:left;">내용 : <span id="rContent"></span></p> 
		<hr />
		<fieldset style="text-align:left;">
			<legend style="text-align:center;">사유 선택</legend>
			<input type="radio" name="rReason" value="1"> 부적절한 홍보성 게시물 <br />
			<input type="radio" name="rReason" value="2"> 음란성 또는 청소년에게 부적합한 내용 <br />
			<input type="radio" name="rReason" value="3"> 특정인 대상의 비방/욕설 <br />
			<input type="radio" name="rReason" value="4"> 저작권 침해 <br />
			<input type="radio" name="rReason" value="5"> 기타 <br />
			<textarea id="rEtc" cols="40" rows="5" style="resize:none;"></textarea>
		</fieldset>
		<button onclick="doReport();">신고하기</button>
	</div>
	
	<% if(cid < 1) { %>
	<script>
		$(function() {
			$('#rWriter').text('<%= b.getMname() %>');
			$('#rContent').text('<%= b.getBtitle() %>');
		});
	</script>
	<% } else {%>
	<script>
		$(function() {
			$('#rWriter').text('<%= bc.getCwname() %>');
			$('#rContent').text('<%= bc.getCcontent() %>');
		});
	</script>
	<% } %>
	
	<script>		
		function doReport() {	
			$.ajax({
				data : { 
					rreason : $('input[name="rReason"]:checked').val(),
					retc : $('#rEtc').val(),
					mid : '<%= m.getMid() %>',
					cid : '<%= cid %>',
					bid : '<%= bid %>'
				},
				url : "/crojecter/rInsert.re",
				type : "post",
				success : function(data) {
					if(data == "ok") {
						alert("신고가 완료되었습니다.");
						close();
					}else {
						alert("오류가 발생하여 신고를 완료하지 못하였습니다.");
						close();
					}
				},
				error : function() {
					alert("오류다");
				}
			});
		}		
	</script>
</body>
</html>