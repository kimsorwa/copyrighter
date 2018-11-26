<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.*"%>
<%
	String nickName = request.getParameter("nickName");
	String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
	.inputContent{
		width:250px;
		height:25px;
		margin:1px 10px 1px 10px;
	}
	#submit{
		color:white;
		text-align:center;
		border-radius:5px;
		height:40px;
		width:250px;
		font-size:15px;
		margin:1px 10px 1px 10px;
	}
	.label{
		text-align:center;
		font-size:10px;
		width:auto;
		margin:1px 10px 1px 10px;
	}
	.wrapper{
		width:290px;
		margin:0 auto;
		margin-top:160px;
		margin-bottom:160px;
		padding:10px 10px 10px 10px;
	}
	body {
		font-family: 'Nanum Gothic', sans-serif;
		background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="wrapper">
		<form name="form" action="<%=request.getContextPath()%>/signUp.do"
			method="post">
			<div style="border: 1px solid lightgray; border-radius:20px; background: white;">
				<div style="margin: 10px 10px 1px 10px;" class="fb-login-button"
					data-max-rows="1" data-size="large" data-button-type="login_with"
					data-show-faces="false" data-auto-logout-link="false"
					data-use-continue-as="false"></div>
				<table style="boarder: 1px solid black;">
					<tr>
						<td><label class="inputContent">닉네임</label></td>
					</tr>
					<tr>
						<td>
							
							<input type="text" name="nickName" id="nickName"
							class="inputContent" placeholder="사용하실 닉네임을 입력해주세요"
							oninput="checkNickName();" <% if(nickName != null) { %> value="<%= nickName %>"/>
							<% } %>
							<br> <label id="labelNickname"
							class="label" style="color:red;">2~10 글자의 닉네임을 입력하세요. 특수문자 불가</label></td>
					</tr>
					<tr>
						<td><label class="inputContent">이메일 주소</label></td>
					</tr>
					<tr>
						<td>
							
							<input type="email" name="email" id="email"
							class="inputContent" placeholder="이메일 주소를 입력해주세요"
							oninput="checkEmail();"<% if(email != null) { %> value="<%= email %>" /></td>
							<% } %>
					</tr>
					<tr>
						<td><input type="email" name="emailCheck" id="emailCheck"
							class="inputContent" placeholder="이메일 주소를 확인합니다"
							oninput="checkedEmail();" <% if(email != null) { %> value="<%= email %>" <% } %>/><br> <label id="labelEmail"
							class="label" style="color:red;">이메일 형식에 맞지 않습니다.</label></td>
					</tr>
					<tr>
						<td><label class="inputContent">비밀번호</label></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="password"
							class="inputContent" placeholder="비밀번호를 입력해주세요"
							oninput="checkedPassword();" /></td>
					</tr>
					<tr>
						<td><input type="password" name="passwordCheck"
							id="passwordCheck" class="inputContent" placeholder="비밀번호를 확인합니다"
							oninput="checkedPassword();" /><br>
							<label class="label" style="color:gray;">영대문자특수문자 숫자 최소 1개 이상 포함</label><br>
							<label id="labelPwd" class="label" style="color:red;">6~12 글자의 비밀번호를 입력하세요.</label>
							</td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><button name="submit" id="submit"
								style="border: 1px solid gray; background-color: gray;"
								onclick="singUpCheck();" disabled>회원가입</button></td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><p align="center">
								이미 계정이 있으신가요?<br> <a href="./login.jsp"
									style="color: red; text-decoration: none;">기존 계정으로 로그인하기</a>
							</p></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
	<script>
		var nnChk = 0;
		var eChk = 0;
		var pChk = 0;
		<% if(nickName != null) { %>
		window.onload = function() {
			apiNickNameCheck();
			apiEmailCheck();
		};
		<% } %>
		
		function checkNickName() {
			var nickname = $("#nickName").val();			
			$.ajax({
				data : { nickName : nickname },
				url : "/crojecter/checkNickName.do",
				type : "post",
				success : function(data) {
					if(nickname == "") {
						$("#nickName").css("background-color", "white");
						$("#labelNickname").css("visibility", "visible");
						$("#labelNickname").text('2~10 글자의 닉네임을 입력하세요. 특수문자 불가');
						nnChk = 0;
						
						memberVerify();
					} else if(data == "success") {
						// 닉네임 체크 성공시 정규식으로 한번더 검사해서 성공할 경우 아래 실행
						var regNickname = /^[가-힣|ㄱ-ㅎ|a-z|A-Z|0-9]{2,10}$/;
						
						if(regNickname.test(nickname)) {
							nnChk = 1;
							$("#nickName").css("background-color", "#B7F400");
							$("#labelNickname").css("visibility", "hidden");
							
							memberVerify();
						} else {
							nnChk = 0;
							$("#nickName").css("background-color", "#FFA5A5");
							$("#labelNickname").css("visibility", "visible");
							$("#labelNickname").text('2~10 글자의 닉네임을 입력하세요. 특수문자 불가');
							
							memberVerify();
						}
					} else {
						nnChk = 0;
						$("#nickName").css("background-color", "#FFA5A5");
						$("#labelNickname").css("visibility", "visible");
						$("#labelNickname").text('중복된 닉네임 입니다.');
						
						memberVerify();
					}
				}
			});
			memberVerify();
		}
		
		function checkEmail() {
			var email = $("#email").val();
			$.ajax({
				data : { eMail : email },
				url : "/crojecter/checkEmail.do",
				type : "post",
				success : function(data) {
					if(email == "") {
						$("#email").css("background-color", "white");
						$("#labelEmail").css("visibility", "visible");
						$("#labelEmail").text('이메일 형식에 맞지 않습니다.');
						eChk = 0;
						
						memberVerify();
					} else if(data == "success") {
						// 이메일 체크 성공시 정규식으로 한번더 검사
						var regEmail = /^[a-zA-Z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
						
						if(regEmail.test(email)) {
							
							var emailChk = $("#emailCheck").val();
							
							if(emailChk == "") {
								$("#email").css("background-color", "#B7F400");
								$("#emailCheck").css("background-color", "white");
								$("#labelEmail").css("visibility", "visible");
								eChk = 0;
								
								checkedEmail()
							} else if(email == emailChk) {
								$("#email").css("background-color", "#B7F400");
								$("#emailCheck").css("background-color", "#B7F400");
								$("#labelEmail").css("visibility", "hidden");
								eChk = 1;
								
								checkedEmail()
							} else {
								$("#email").css("background-color", "#B7F400");
								$("#emailCheck").css("background-color", "#FFA5A5");
								$("#labelEmail").css("visibility", "visible");
								eChk = 0;
								
								checkedEmail()
							}
							
							memberVerify();
						} else {
							eChk = 0;
							$("#email").css("background-color", "#FFA5A5");
							$("#labelEmail").css("visibility", "visible");
							$("#labelEmail").text('이메일 형식에 맞지 않습니다.');
							
							memberVerify();
						}
					} else {
						eChk = 0;
						$("#email").css("background-color", "#FFA5A5");
						$("#labelEmail").css("visibility", "visible");
						$("#labelEmail").text('중복된 이메일 입니다.');
						
						memberVerify();
					}
				}
			});		
			memberVerify();
		}
		
		function checkedEmail() {
			var email = $("#email").val();
			var emailChk = $("#emailCheck").val();
			var regEmail = /^[a-zA-Z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
			
			if(emailChk == "" && regEmail.test(email)) {
				$("#emailCheck").css("background-color", "white");
				$("#labelEmail").css("visibility", "visible");
				$("#labelEmail").text('이메일 확인부분 미입력');
				eChk = 0;
				
				memberVerify();
			} else if(email == emailChk) {
				if(regEmail.test(emailChk)) {
					$("#emailCheck").css("background-color", "#B7F400");
					$("#labelEmail").css("visibility", "hidden");
					eChk = 1;
					
					memberVerify();
				} else {
					$("#emailCheck").css("background-color", "#FFA5A5");
					$("#labelEmail").css("visibility", "visible");
					$("#labelEmail").text('이메일 형식에 맞지 않습니다.');
					eChk = 0;
					
					memberVerify();
				}
				
			} else {
				$("#emailCheck").css("background-color", "#FFA5A5");
				$("#labelEmail").css("visibility", "visible");
				$("#labelEmail").text('이메일 미일치');
				eChk = 0;
				
				memberVerify();
			}
			memberVerify();
		}
		
		
		function checkedPassword() {
			var pwd = $("#password").val();
			var pwdChk = $("#passwordCheck").val();
			
			if (pwd != "" && pwdChk != "" && pwd == pwdChk) {
				// 비밀번호 정규식으로 검사
				var regPwd = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,12}$/;
				
				if(regPwd.test(pwd)) {
					pChk = 1;
					$("#passwordCheck").css("background-color", "#B7F400");
					$("#labelPwd").css("visibility", "hidden");
					
					memberVerify();
				} else {
					pChk = 0;
					$("#passwordCheck").css("background-color", "#FFA5A5");
					$("#labelPwd").css("visibility", "visible");
					
					memberVerify();
				}
				
			} else if (pwdChk == "" || pwd == "") {
				$("#passwordCheck").css("background-color", "white");
				$("#labelPwd").css("visibility", "visible");
				pChk = 0;
				
				memberVerify();
			} else {
				pChk = 0;
				$("#passwordCheck").css("background-color", "#FFA5A5");
				$("#labelPwd").css("visibility", "visible");
				
				memberVerify();
			}
			memberVerify();
		}
		function memberVerify() {
			if (nnChk == 1 && eChk == 1 && pChk == 1) {
				$("#submit").removeAttr("disabled");
				$("#submit").css("background-color", "lightblue");
				$("#submit").css("border", "1px solid lightblue");
				$("#submit").css("color", "black");
			} else {
				$("#submit").attr("disabled", "disabled");
				$("#submit").css("background-color", "gray");
				$("#submit").css("border", "1px solid gray");
			}
		}
		function singUpCheck() {
			$("#form").submit();
		}
		
		function apiNickNameCheck() {
			$.ajax({
				data : { nickName : "<%=nickName%>" },
				url : "/crojecter/checkNickName.do",
				type : "post",
				success : function(data) {
					if(data == "success") {
						// 닉네임 체크 성공시 정규식으로 한번더 검사해서 성공할 경우 아래 실행
						var regNickname = /^[가-힣|ㄱ-ㅎ|a-z|A-Z|0-9]{2,10}$/;
						
						if(regNickname.test("<%=nickName%>")) {
							nnChk = 1;
							$("#nickName").css("background-color", "#B7F400");
							$("#labelNickname").css("visibility", "hidden");
							
							memberVerify();
						} else {
							nnChk = 0;
							$("#nickName").css("background-color", "#FFA5A5");
							$("#labelNickname").css("visibility", "visible");
							$("#labelNickname").text('2~10 글자의 닉네임을 입력하세요. 특수문자 불가');
							
							memberVerify();
						}
					} else {
						nnChk = 0;
						$("#nickName").css("background-color", "#FFA5A5");
						$("#labelNickname").css("visibility", "visible");
						$("#labelNickname").text('중복된 닉네임 입니다.');
						
						memberVerify();
					}
				}
			});
		}
		
		function apiEmailCheck(){
			$.ajax({
				data : { eMail : "<%= email %>" },
				url : "/crojecter/checkEmail.do",
				type : "post",
				success : function(data) {
					if(data == "success") {
						// 이메일 체크 성공시 정규식으로 한번더 검사
						var regEmail = /^[a-zA-Z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
						
						if(regEmail.test("<%=email%>")) {
							
							var emailChk = "<%=email%>";
							
							if(email == emailChk) {
								$("#email").css("background-color", "#B7F400");
								$("#emailCheck").css("background-color", "#B7F400");
								$("#labelEmail").css("visibility", "hidden");
								eChk = 1;
								
								checkedEmail()
							} else {
								$("#email").css("background-color", "#B7F400");
								$("#emailCheck").css("background-color", "#FFA5A5");
								$("#labelEmail").css("visibility", "visible");
								eChk = 0;
								
								checkedEmail()
							}
							
							memberVerify();
						} else {
							eChk = 0;
							$("#email").css("background-color", "#FFA5A5");
							$("#labelEmail").css("visibility", "visible");
							$("#labelEmail").text('이메일 형식에 맞지 않습니다.');
							
							memberVerify();
						}
					} else {
						eChk = 0;
						$("#email").css("background-color", "#FFA5A5");
						$("#labelEmail").css("visibility", "visible");
						$("#labelEmail").text('중복된 이메일 입니다.');
						
						memberVerify();
					}
				}
			});
		}
	</script>
</body>
</html>