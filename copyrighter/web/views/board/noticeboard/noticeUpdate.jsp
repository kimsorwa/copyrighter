<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.kh.board.notice.model.vo.*, com.kh.member.model.vo.Member"%>
<% 
	Notice n = (Notice)request.getAttribute("notice"); 
	System.out.println("NoticeUpdate.jsp bid : "+n);
%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>공지사항 수정 페이지</title>

<!-- 폰트 설정 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic"
	rel="stylesheet">
	
<style>
body {
	font-family: 'Nanum Gothic', sans-serif;
	background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
}

.uploadBtn {
	width: 100%;
    height: 50px;
    border: 1.5px solid lightblue;
    background: lightblue;
    color: black;
    border-radius: 5px;
    margin-top: 5px;
    font-size: 16px;
}

.thumbnailArea {
	width: 100%;
	height: 150px;
	border: 1px solid lightgray;
	text-align: center;
}

#titleImg {
	width: 100%;
	height: 100%;
	border: none;
}
</style>
</head>


<body>
	<%@ include file="../../common/header.jsp"%>
	<%
		if (m != null) {
	%>
	<form id="insertform" action="<%=request.getContextPath()%>/nUpdate.no?bid=<%=n.getBid()%>" method="post" >
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-2"></div>
			<div class="col-md-6" style="background: white; padding-top: 10px; 
				 background-color: rgba( 255, 255, 255, 0.5 ); margin-bottom: 100px;">
				<input type="text" class="form-control" id="title" name="title" value="<%=n.getBtitle()%>">
				<textarea id="summernote" name="content"><%=n.getBcontent()%></textarea>
			</div>
			<div class="col-md-2" style="background: white; padding-top: 10px; padding-right: -5px; background-color: rgba( 255, 255, 255, 0.5 );">				
				<input type="hidden" id="userId" name="userId" value="<%=m.getMid()%>" />
				<button class="uploadBtn" id="insertBtn" type="submit" onclick="insertNotice();">업로드</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</form>

	<%
		} else {
			request.getRequestDispatcher("../member/login.jsp").forward(request, response);
		}
	%>

	<script type="text/javascript">
		$(document).ready(function() {
		      $('#summernote').summernote({
		        height: 500,
		        minHeight: 500,
		        maxHeight: 500,
		        focus: true,
		        callbacks: {
		          onImageUpload: function(files, editor, welEditable) {
		            for (var i = files.length - 1; i >= 0; i--) {
		              sendFile(files[i], this);
		            }
		          },
		        }		      
		      });
		    });
    
		function sendFile(file, el) {
				var form_data = new FormData();
		      	form_data.append('file', file);
		      	$.ajax({
		        	data: form_data,
		        	type: "post",
		        	url: "/crojecter/sInsert.fo",
					cache : false,
					contentType : false,
					enctype : 'multipart/form-data',
					processData : false,
					success : function(url) {
						url.replace("\/","/");
						$(el).summernote('editor.insertImage', url);
					}, error : function(){						
						console.log("실패!!");
						
					}
				});
			}
		
		function insertNotice() {							
			if($("#title").val() == "") {
				alert("제목을 입력하세요.");
				$("#title").focus();
			}
			else if(!$("#summernote").val()){
				alert("내용을 입력해주세요.");	
			}
			else $("#insertform").submit();
			
			event.preventDefault();			
		}
		</script>
		
		<%@ include file="../../common/footer.jsp"%>

</body>
</html>