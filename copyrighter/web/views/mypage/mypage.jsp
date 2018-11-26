<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>

	.form{
		width:300px;
		margin:0 auto;
		margin-top:160px;
		margin-bottom:160px;
		padding:10px 10px 10px 10px;
		}

	.inputContent{
		width:250px;
		height:25px;
		margin:1px 10px 1px 10px;
		}
		
	.label{
		color:red;
		text-align:center;
		font-size:10px;
		width:auto;
		margin:1px 10px 1px 10px;
		}
		
	.submit{
		color:white;
		text-align:center;
		border-radius:5px;
		height:40px;
		width:100px;
		font-size:15px;
		margin:1px 10px 1px 10px;
		}

</style>
</head>
<body>
<%@ include file="../mypage/common/mypageHeader.jsp" %>
<div class="form">
<form id="updateForm" action="<%=request.getContextPath()%>/mUpdate.do" method="post">
	        <table>
            <tr>
                <td><label class="inputContent">닉네임</label></td>
            </tr>
            <tr>
                <td colspan="2"><input type="text" class="inputContent" name="nickName" id="nickName" oninput="checkNickName();" 
                value="<%= m.getMname()%>"><br>
                <label id="labelNickname" class="label">2~10 글자의 닉네임을 입력하세요. 특수문자 불가</label></td>
            </tr>
            <tr>
                <td><label class="inputContent">이메일</label></td>
            </tr>
            <tr>
                <td colspan="2"><input type="email" class="inputContent" name="email" id="email" value="<%= m.getMemail()%>" readonly></td>
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
				<td><input type="password" name="passwordCheck" id="passwordCheck" class="inputContent" 
							placeholder="비밀번호를 확인합니다" oninput="checkedPassword();" /><br> 
							<label id="labelPwd" class="label">6~12 글자의 비밀번호를 입력하세요.<br>&nbsp;&nbsp;영대문자
								특수문자 숫자 최소 1개 이상 포함
						</label></td>
			</tr>
            <tr>
                <td><input type="submit" class="submit" onclick="updateMember();" style="border: 1px solid gray; background-color: gray;" value="수정하기">
                <input type="button" class="submit" onclick="deleteMember();" style="border: 1px solid gray; background-color: gray;" value="탈퇴하기"></td>
            </tr> 
        </table>
	</form>
</div>

<script>
	function updateMember() {
		$("#updateForm").submit();
	}
	
	function deleteMember() {
		location.href = "/crojecter/mDelete.do?mid=<%=m.getMemail()%>";
	}
	
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

</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
