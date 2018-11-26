<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, com.kh.board.gallery.model.vo.*, com.kh.member.model.vo.Member, com.kh.board.attachedfile.model.vo.*"%>
<%
	Gallery g = (Gallery)request.getAttribute("gallery");
	AttachedFile af = (AttachedFile)request.getAttribute("attachedfile");	
%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>갤러리 수정 페이지</title>
<!-- 폰트 설정 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic"
	rel="stylesheet">

<style>

body {
	font-family: 'Nanum Gothic', sans-serif;
	background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
}

.sidebar {
	width: 100%;
	height: 50px;
	border: 1px solid lightgray; 
}

.thumbnailArea {
	width: 100%;
	height: 150px;
	border: 1px solid lightgray; 
	text-align: center;
	margin-bottom: 5px;
}

#titleImg {
	width: 100%;
	height: 100%;
	border: none;
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
</style>
</head>


<body>
<%@ include file="../../common/header.jsp" %>
	<%
		if (m != null) {
	%>
	<form id="insertform" action="<%=request.getContextPath()%>/gUpdate.ga?bid=<%=g.getBid() %>" method="post" encType="multipart/form-data">
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-2"></div>
			<div id="fileArea">
				<input type="file" name="thumbnailInput" id="thumbnailInput" onchange="LoadImg(this)">
			</div>
			
			<div class="col-md-6"
				 style="background: white; padding-top: 10px; 
				 background-color: rgba( 255, 255, 255, 0.5 ); margin-bottom: 50px;">
				<input type="text" class="form-control" id="title" name="title" value="<%=g.getBtitle()%>">
				<textarea id="summernote" name="content"><%=g.getBcontent()%></textarea>
			</div>
			<div class="col-md-2"
				 style="background: white; padding-top: 10px; padding-right: -5px; background-color: rgba( 255, 255, 255, 0.5 );">
				<input type="hidden" id="userId" name="userId" value="<%=m.getMid()%>" /> 
				<div class="thumbnailArea" id="thumbnailArea" name="thumbnailArea">					
					<img id="titleImg">
					<div id="thumbnailLabel" class="tagText"></div>
				</div>
				<select class="sidebar" name="category" id="category">
					<option value="1">TEXT</option>
					<option value="2">IMAGE</option>
					<option value="3">AUDIO</option>
					<option value="4">VIDEO</option>
				</select> 
				<select class="sidebar" name="cclid" id="cclid">
					<option value="1">저작자 표시</option>
					<option value="2">저작자-비영리</option>
					<option value="3">저작자-동일조건변경허락</option>
					<option value="4">저작자-변경금지</option>
					<option value="5">저작자-비영리-변경금지</option>
					<option value="6">저작자-비영리-동일조건변경허락</option>
				</select> 
				<input type="text" name="tags" data-role="tagsinput" value="<%=g.getGtag()%>" id="tagsinput" class="tagsinput">

				<button class="uploadBtn" id="insertBtn" onclick="insertGallery();">업로드</button>
				
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
		    
		      
		      $('#titleImg').attr('src', "resources/uploadFiles/<%=af.getFname()%>");
		      $('#category').val(<%=g.getGcategoryid()%>);
		      $('#cclid').val(<%=g.getCclid()%>);
		      
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
		
		$(function(){
			$('#fileArea').hide();
			
			$('#thumbnailArea').click(() => {
				$('#thumbnailInput').click();
			});
		});
		function LoadImg(value) {
			if(value.files && value.files[0]) {
				var reader = new FileReader();
				
				reader.onload = function(e){					
					$('#titleImg').attr('src', e.target.result);
					$('#thumbnailLabel').hide();
				}
				
				reader.readAsDataURL(value.files[0]);
			}
		}
		
		function insertGallery() {							
			if($("#title").val() == "") {
				alert("제목을 입력하세요.");
				$("#title").focus();
			}
			else if(!$("#summernote").val()){
				alert("내용을 입력해주세요.");	
			}
			else if($("#category").val() == null) {
				alert("카테고리를 선택해주세요.");				
			}
			else if($('#cclid').val() == null) {
				alert("ccl을 선택해주세요.");				
			}
			else if(!$('#tagsinput').val()) {
				alert("태그를 한 개 이상 입력해주세요.");				
			}
			else $("#insertform").submit();
			
			event.preventDefault();			
		}
		
		$('#tagsinput').tagsinput({maxTags: 10});
		
		</script>

		<%@ include file="../../common/footer.jsp" %>

</body>
</html>