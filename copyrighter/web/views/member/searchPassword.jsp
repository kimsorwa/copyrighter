<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//임시비밀번호 발생
	String tempPassword = "";

	for (int i = 0; i < 8; i++) {

		int rndVal = (int) (Math.random() * 62);

		if (rndVal < 10) {
			tempPassword += rndVal;
		} else if (rndVal > 35) {
			tempPassword += (char) (rndVal + 61);
		} else {
			tempPassword += (char) (rndVal + 55);
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
	body {
		font-family: 'Nanum Gothic', sans-serif;
		background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");		
	}
	.any{ margin-left:10px; margin-right:10px; }
	.button{
		background: lightblue;
		border:1px solid lightblue;
		text-align:center;
		border-radius:5px;
		height:50px;
		width:250px;
		font-size:15px;
		margin:10px 10px 10px 10px;
	}
	.wrapper{
		width:780px;
		margin:0 auto;
		margin-top: 50px;
		margin-bottom:105px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="wrapper">
		<div style="display: inline-block;">
			<div style="float: left; background: white; margin-right:50px; border: 1px solid lightgray; height: auto; width: 370px; margin: 10px 10px 10px 10px">
				<div>
					<h3 class="any">닉네임 찾기</h3>
					<hr />
					<p class="any">회원가입 시 사용하신 이메일주소를 입력하세요.</p>
					<hr />
					<form>
						<input type="email" id="email" name="email" class="any"
							placeholder="가입하신 이메일 주소" style="width: 340px; font-size: 20px;" /><br>
						<label id="labelNickname"
							style="margin-left: 10px; margin-top: 10px; visibility: hidden;"></label><br>
						<input type="button" class="button any" value="닉네임 찾기"
							style="width: 340px;" onclick="searchNickName();" />
					</form>
				</div>
				<hr />
				<div>
					<h3 class="any">이메일 찾기</h3>
					<hr />
					<p class="any">회원가입 시 사용하신 닉네임을 입력하세요.</p>
					<hr />
					<form>
						<input type="text" id="nickName" name="nickName" class="any"
							placeholder="가입하신 닉네임" style="width: 340px; font-size: 20px;" /><br>
						<label id="labelEmail"
							style="margin-left: 10px; margin-top: 10px; visibility: hidden;"></label><br>
						<input type="button" class="button any" value="이메일 찾기"
							style="width: 340px;" onclick="searchEmail();" />
					</form>
				</div>
			</div>

			<div
				style="border: 1px solid lightgray; background: white; height: auto; width: 280px; margin: 120px 10px 120px 10px; float: right; margin-left:50px;">
				<h3 class="any">비밀번호 찾기</h3>
				<hr />
				<p class="any">
					회원가입 시 사용하신 닉네임과<br> 이메일주소를 입력하시면<br> 임시 비밀번호를 보내드립니다.
				</p>
				<hr />
				<form action="<%=request.getContextPath()%>/send.do" id="login" name="login">
					<input type="text" name="nickName" class="any" id="chkNickname"
						placeholder="가입하신 닉네임"
						style="width: 250px; font-size: 20px; margin-bottom: 10px;" /><br>
					<input type="email" name="email" class="any" id="chkEmail"
						placeholder="가입하신 이메일 주소" style="width: 250px; font-size: 20px;" /><br>
					<input type="text" name="tempPassword" class="any"
						style="display:none;" value="<%=tempPassword%>" />
					<input type="text" name="tempPwd" class="any"
						style="display:none;" value="<%=tempPassword%>" />
					<input type="button" class="button any" value="임시 비밀번호 받기" onclick="goSubmit();"/>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script>
		function goSubmit() {
			if($("#chkNickname").val() == ""){
				alert("닉네임을 입력하세요.");
				$("#chkNickname").focus();
			} else {
				if ($("#chkEmail").val() == "") {
					alert("이메일을 입력하세요.");
					$("#chkEmail").focus();
				} else {
					var regEmail = /^[a-zA-Z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
					if (!regEmail.test($("#chkEmail").val())) {
						alert("이메일형식이 아닙니다.");
						$("#chkEmail").focus();
					} else {
						$("#login").submit();
					}
				}
			}
		}
		
		function searchNickName() {
			var email = $("#email").val();
			$.ajax({
				data : {
					eMail : email
				},
				url : "/crojecter/searchNickname.do",
				type : "get",
				success : function(data) {
					console.log(data);
					if (data != null) {
						if (data == "fail") {
							$("#labelNickname").css("visibility", "visible");
							$("#labelNickname").css("color", "red");
							$("#labelNickname").text("존재하지 않은 이메일 입니다.");
						} else {
							$("#labelNickname").css("visibility", "visible");
							$("#labelNickname").css("color", "black");
							$("#labelNickname").text(
									"회원님의 닉네임은 ' " + data + " '입니다.");
						}
					} else {
						$("#labelNickname").css("visibility", "hidden");
					}
				}
			});
		}

		function searchEmail() {
			var nickname = $("#nickName").val();
			$.ajax({
				data : {
					nickName : nickname
				},
				url : "/crojecter/searchEmail.do",
				type : "get",
				success : function(data) {
					console.log(data);
					if (data != null) {
						if (data == "fail") {
							$("#labelEmail").css("visibility", "visible");
							$("#labelEmail").css("color", "red");
							$("#labelEmail").text("존재하지 않은 닉네임 입니다.");
						} else {
							$("#labelEmail").css("visibility", "visible");
							$("#labelEmail").css("color", "black");
							$("#labelEmail").text(
									"회원님의 이메일은 ' " + data + " '입니다.");
						}
					} else {
						$("#labelEmail").css("visibility", "hidden");
					}
				}
			});
		}
	</script>
</body>
</html>