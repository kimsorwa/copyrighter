<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.kh.follow.model.vo.*, com.kh.member.model.vo.*,
          com.kh.board.gallery.model.vo.*, java.util.*"%>
<%
   ArrayList<Follow> list = (ArrayList<Follow>)request.getAttribute("list");
   Gallery g = (Gallery)request.getAttribute("gallery");
   Follow f = (Follow)request.getAttribute("follow");
   System.out.println("list : " + list);
%>
<!DOCTYPE html>
<html>
<head>
<script src="/crojecter/resources/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CopyRight 홈페이지에 오신걸 환영합니다.</title>
<style>
   #btnFollow {
   
   width: 130px;
   height: 35px;
   margin-bottom:20px;
   }
    #btnFollow.btn.btn-follow active{
        background:lightblue;
        color:black;
        height:30px;
        padding-top:10px;
        padding-bottom:10px;
        text-align: center;
        
    }
    
     #btnFollow.btn.btn-follow{
      background:lightblue;
        color:black;
        height:40px;
        padding-top:10px;
        padding-bottom:10px;
        text-align: center;
    }
   
   .inputName {
   
   color:darkgray;
   padding-right:50px;
   padding-left:100px;
   padding-top:20px;
   font-size:20px;
   border:white;
   text-align:center;
   
   
   }
   
   .form {
   
   margin-bottom:20px;
   }
   
   .table {
   
   width:50%;
   
   }
   
    .followList {
        border:1px black solid;
    }
    
    .p-follow {
    
    padding-bottom:30px;
    }

   .alarm {
   
   color:black;
   padding-top:100px;
   font-weight:bold;
   
   }
    
</style>

</head>
<body>
<%@ include file="../mypage/common/mypageHeader.jsp"%><br><br>
<form action="<%=request.getContextPath()%>/followingView.do" method="post">
<div class="col-md-2"></div>
   <div class="col-md-8" style="margin-bottom: 50px; margin-top: 50px;">
   <% if(list.size() > 0) { %>
   <table class="table">  
      <% for(Follow f1 : list){ %>
      <tr class="set">
         <td class="name" name="name"><input class="inputName"type="text" value="<%=f1.getFollowname() %>" readonly></td>
         <td class="button" align="center">    
            <input type="hidden" name="fid" value="<%= f1.getFollowid() %>"/> <br>
            <% if(f1.getChk().equals("Y")){ %>
               <div id="btnFollow" class="btn btn-follow active" onclick="switchfollow(this);">
               <p class="p-follow" style="color: black">언팔로우</p></div>
            <% } else {%>
               <div id="btnFollow" class="btn btn-follow" onclick="switchfollow(this);">
               <p class="p-follow" style="color: black">팔로우</p></div>
            <% } %>
         </td>
      </tr>
      <% } %>
   </table>
   <% } else { %>
      <div class="alarm" align="center">아직 팔로잉 한 회원이 없습니다.</div>
   <% } %>
</div>
<div class="col-md-2"></div>
</form>
<script>
   function switchfollow(obj){
      $.ajax({
         url : '/crojecter/fSwitch.fo',
         type : 'get',
         data : {
            mid : '<%= m.getMid() %>',
            wid : $(obj).siblings('input[name="fid"]').val()
         }, 
         success : function(data){
            if(data == 'insert') {
               $(obj).addClass('active');
               $(obj).children('p[class="p-follow"]').html('언팔로우');
            } else if (data == 'delete') {
               $(obj).removeClass('active');
               $(obj).children('p[class="p-follow"]').html('팔로우');
            } else {
               console.log('btnFollow() 에러 발생!');
            }
         }, error : function(data){
            console.log('btnFollow() 에러 발생!!');
         }
      });
   }
</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>