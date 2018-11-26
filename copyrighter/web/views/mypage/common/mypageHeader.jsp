<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.kh.member.model.vo.*"%>
<% 
   Member m2 = (Member) request.getAttribute("myMember");   
%>
<!DOCTYPE html>
<html>
<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
   .profileArea {
      border: 1px solid black;
      color: black;
      text-align:center;
      font-weight:bold;
      font-size:23px;
      background-image: url("<%=request.getContextPath()%>/resources/images/mypage2.jpg");
   }
   
   .menu {
      border: 1px solid gray;
      padding-top:20px;
      padding-bottom:20px;

   }
   
   .menuMenu a {
      margin-left:15px;
      margin-right:40px;
      text-decoration: none;
      color: darkgray;
      font-size:23px;
      font-weight:bold;
   }
   
   .form {
   
      border: 1px solid black;
   }

   #button {
      border: solid 1px gray;
      color: white;
      background-color: gray;
      border-radius: 5px;
      font-size: 15px;
      margin-right:20px;
        margin-left:20px;
        padding-top:5px;
        padding-bottom: 5px;
        padding-right: 10px;
        padding-left: 10px;
        text-decoration: none;
   }
   
   #profileImg {
      width: 100px;
      height: 100px;
      border-radius: 50px;
      margin-top: 20px;
      margin-left: 20px;
      border: solid 1px gray;
   }      

</style>
</head>
<body>
   <%@ include file="../../common/header.jsp"%>
   <div class="profileArea">
      <form id="updateForm" action="<%=request.getContextPath()%>/mUpdateProfile.me" method="post" encType="multipart/form-data">
         <% if(m2.getMprofile() == null) { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/images/user.png">
         <% } else { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/profileFiles/<%=m2.getMprofile()%>">
         <% }  %>
         <input type="file" name="file" id="btnProfile" style="display:none;" onchange="updateProfile();"/>
      </form>
      <p id="profileName"><%=m2.getMname()%>님의 페이지</p>
      
      <% if(m.getMid() == m2.getMid()) { %>
      <p class=proMenu>
      <a class="profileMenus" id="button" href="/crojecter/myPaymentSelect.do?mid=<%= m.getMid() %>">결제내역</a> 
      <a class="profileMenus" id="button" href="/crojecter/myInfoView.my?mid=<%= m.getMid() %>">정보수정</a>
      <br>
      </p>
      <% } %>
   </div>
   <br>
   <div class="menu" align="center">
      <table>
         <tr>
            <td class="menuMenu"><a
				href="/crojecter/mypageView.do?mpid=<%=m2.getMid()%>">About</a></td>
			<td class="menuMenu"><a
				href="/crojecter/mworkView.do?mpid=<%=m2.getMid()%>">Works</a></td>
			<td class="menuMenu"><a
				href="/crojecter/collectView.do?mpid=<%=m2.getMid()%>">Collect</a></td>
			<td class="menuMenu"><a 
				href="/crojecter/followingView.do?mpid=<%=m2.getMid()%>&mid=<%=m.getMid()%>">Followings</a></td>
			<td class="menuMenu"><a
				href="/crojecter/followerView.do?mpid=<%=m2.getMid()%>&mid=<%=m.getMid()%>">Followers</a></td>
         </tr>
      </table>
   </div>
   
   <script>
      
   function updateProfile() {
      $("#updateForm").submit();
   }
   
   $('#profileImg').click(() => {
      $('#btnProfile').click();   
   });
   
   function LoadImg(value) {
      if(value.files && value.files[0]) {
         var reader = new FileReader();         
         reader.onload = function(e){               
            $('#profileImg').attr('src', e.target.result);   
         }         
         reader.readAsDataURL(value.files[0]);
      }
      
      $.ajax({
         url : '/crojecter/mUpdateProfile.me',
         type : 'get',
         data : {
            mid : '<%= m.getMid() %>',
            mprofile : $('#profileImg').attr('src')
         }, 
         success : function(data){
            if(data == 'success') {
               console.log('프로필 변경 성공');
            } else {
               console.log('프로필 변경 실패');
            }
         }, error : function(data){
            console.log('프로필 변경 에러 발생!!');
         }
      });
   }
   </script>
</body>
</html>