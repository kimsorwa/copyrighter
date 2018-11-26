<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.gallery.model.vo.*, com.kh.member.model.vo.Member, java.util.*, com.kh.boardcomment.model.vo.*" %>

<%
   Gallery g = (Gallery)request.getAttribute("gallery");
   ArrayList<BoardComment> clist = (ArrayList<BoardComment>) request.getAttribute("clist"); 
   
   System.out.println("g : " + g);
   System.out.println("clist : " + clist);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<style>  

	body {
		font-family: 'Nanum Gothic', sans-serif;
		background-image: url("<%=request.getContextPath()%>/resources/images/background2.jpg");
	}
	
   .titleArea {
      width: 100%;
      height: 34px;
      border: 1px solid lightgray;   
      border-radius: 3px;
   }
   
   .contentArea {
      min-height: 500px;
      padding: 15px;
      border: 1px solid lightgray;   
   }
   
   .title {
      padding-top: -5px;
      margin-top: 5px;
      font-size: 18px;
   }
   
   .count {
      margin-top: 7px;    
   }
   
   .commentOuter {
      width:auto;
      height:auto;
      color:black;
      margin-left:auto;
      margin-right:auto;
      margin-top:20px;
   }
   
   #profileImg {
      margin-top: 10px;
      margin-bottom: 5px;
      width: 120px;
      height: 120px;
      border-radius: 50px;
      border: 1px solid lightgray;      
   }
   
   .sidebar {
      width: 100%;
      height: 50px;
      border: 1px solid lightgray;
      padding-top:15px;
      padding-left:5px; 
   }
   
   .btnArea1 {
      width: 90%;
      height: 40px;
      border: 1.5px solid lightblue;
      background: lightblue;
      color: black;
      border-radius: 5px;
   }
   
    .btnComment {
      width: 100px;
      height: 40px;
      margin: 10px;
      background: lightblue;
      color: black;
      border: 1.5px solid lightblue;
      border-radius: 5px;
      float: right; 
   } 
   
   .bottomBtn {
      width: 20%;
      height: 25px;
      background: lightblue;
      border: 1.5px solid lightblue;
      border-radius: 5px;
      color: black;
      float: right;
      margin-right:2px;
      font-size: 12px;
   }
   
   .insertBtn{
      width: 25%;
   }
   
   .content img {
      display: inline-block;
  	  vertical-align: middle;
      max-height: 100%;
      max-width: 100%;
   }
   
   .active {   	  
   	  background: orange;
   	  border: 1.5px solid orange;
   }
   
</style>

<title>갤러리 상세보기</title>
</head>
<body style="height: 100%;">
   <%@ include file="../../common/header.jsp"%>
   
   <div class="row">
      <div class="col-md-2"></div>

      <div class="col-md-6" 
      	   style="background: white; padding-top: 10px; background-color: rgba( 255, 255, 255, 0.5 );
      	   margin-bottom: 70px;">
            
         <%if(m != null)  {  %>
         <span id="parentGetmid" style="display:none;"><%= m.getMid() %></span> <% } %>
         
         <div class="row titleArea" style="margin: 0; background: white;">
            <div class="col-md-10"><p class="title"><%= g.getBtitle() %></p></div>
            <div class="col-md-2 count">(조회수 :  <%= g.getBcount() %>)</div>
         </div>
         
         
         <div class="row contentArea" style="margin: 0; background: white;">
            <div class="content"><%= g.getBcontent() %></div>
         </div>
         
         <div class="commentOuter" >
            <div class="commentWriteArea">
               <div><input type="hidden" name="crefmid" value="-1" /></div>               
               <div class="row contentArea1">
                  <div class="col-md-10"><textarea rows="3" id="ccontent" name="ccontent" style="width:100%;"></textArea></div>
                  <div class="col-md-2"><button class="btnComment" id="addComment" onclick="insertComment(this);"style="width:100%;" >댓글 등록</button></div>                     
               </div>            
            </div>
         </div>

         <div class="commentListArea">
               <% if( clist != null ) { %>
               <% for(BoardComment bc : clist) { %>
               <div class="comment">
                  <div class="row commentInfo" style="display:inline;">
                     <div class="col-md-5" style="padding:0;">
                     <input type="hidden" name="cid" value="<%=bc.getCid()%>"/>
                     <span>닉네임 : <%=bc.getCwname() %></span> &nbsp;&nbsp;
                     <span>작성날짜 : <%=bc.getCdate() %></span>
                     </div>
                   
                     <% if(m != null) { // 로그인한 회원의 경우 댓글달기 버튼 출력 %>
                     <input type="hidden" name="crefmid" value="<%= bc.getCwriter() %>" />
                     <div class="col-md-5" style="padding:0; float:right;">                  
                         <% if(m.getMid() == bc.getCwriter()) { // 댓글쓴이 본인인 경우 수정,삭제버튼 추가 출력 %>   
                         <div class="" style="padding:0;">
                        	<button type="button" class="bottomBtn updateBtn" onclick="updateComment(this);">수정</button>
                     		<button type="button" class="bottomBtn updateConfirm" onclick="updateConfirm(this);"
                            		style="display:none;">수정완료</button>
                         </div>
	                     <div class="" style="padding:0;"><button type="button" class="bottomBtn deleteBtn" onclick="deleteComment(this);">삭제</button></div>
	                        <% } %>                     
	                     <div class="" style="padding:0;"><button type="button" class="bottomBtn insertBtn" onclick="reComment(this);">댓글 달기</button></div>
	                     <div class="" style="padding:0;"><button type="button" class="bottomBtn reportBtn" onclick="showReport(<%=g.getBid()%>, <%=bc.getCid()%>);">신고</button></div>          
                        </div>
                        <% } %>
                                          
                  </div>
                  
                  <div class="comment commentContent">                 
                     <textarea class="comment-content" cols="80" rows="3" style="display:none;"><%= bc.getCcontent() %></textarea>                                     
                     <p style="border:1px solid lightgray; background:white; height:70px;">
                        <% if(bc.getCrefmid()>0) { %><a href=
                        	"/crojecter/mypageView.do?mpid=<%=bc.getCrefmid()%>">@<%= bc.getCfname() %></a> <% } %>
                        <%= bc.getCcontent() %>
                     </p>                                     
                  </div>
                     
                  <hr />
               </div>
              <% } } %>
         </div>
         
      </div>
      
      <div class="col-md-2" style="background: white; padding-top: 10px; padding-right: -5px; background-color: rgba( 255, 255, 255, 0.5 );">      
         <div align="center">
         <a href="/crojecter/mypageView.do?mpid=<%= g.getBwriter() %>">
         <% if(g.getMprofile() == null) { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/images/user.png">
         <% } else { %>
         <img id="profileImg" src="<%=request.getContextPath()%>/resources/profileFiles/<%=g.getMprofile()%>">
         <% }  %></a>
         </div>
         
         <h4 align="center" id="parentGetName"><%=g.getMname()%></h4>

         <div class="row" align="center" id="" style="margin-top: 10px;">
            <div class="col-md-6" style="padding:0;"><button class="btn-follow btnArea1" id="btnFollow">팔로우</button></div>         
            <div class="col-md-6" style="padding:0;"><button class="btn-likeit btnArea1" id="btnLikeit">좋아요</button></div>                        
         </div>         
         <hr />
         <div class="sidebar" style="margin-top: 10px; background: white">카테고리 : <%=g.getGcategoryname() %></div>
         <div class="sidebar" style="background: white">CCL : <%=g.getCclname() %></div>
         <input type="text" value=<%=g.getGtag()%> data-role="tagsinput" id="tagsinput" class="tagsinput" disabled>      
         
         <div class="row" align="center" id="btnArea2" style="margin-top: 10px;">            
            <div class="col-md-6" style="padding:0;"><button class="btnArea1" onclick="showSpon();">후원</button></div>
            <div class="col-md-6" style="padding:0;"><button class="btnArea1" onclick="showReport(<%=g.getBid()%>, 0);">신고</button></div>   

         </div>
         <div class="row" align="center" id="btnArea3" style="margin-top: 10px;">
            <% if(m != null && m.getMid() == g.getBwriter()){ // 글쓴이 본인인 경우 %>
            <div class="col-md-6" style="padding:0;">
            <button class="btnArea1" onclick="location.href='<%= request.getContextPath() %>/gUpView.ga?bid='+<%=g.getBid()%>">수정하기</button>
            </div>
            <div class="col-md-6" style="padding:0;">
            <button class="btnArea1" onclick="location.href='<%= request.getContextPath() %>/gDelete.ga?bid='+<%=g.getBid()%>">삭제하기</button>
            </div>
            <% } %>
         </div>
      
      </div>
         
      
      
      <div class="col-md-2"></div>
      
   </div>

   
   <!-- -----------------------여기서부터  스크립트----------------------- -->

   <% if(m != null) { // 회원 %>
   <script> 
      // 초기화 함수
      $(function(){      
         // 팔로우 버튼 초기화
         $.ajax({
            url : '/crojecter/fCheck.fo',
            type : 'get',
            data : {
               mid : '<%= m.getMid() %>',
               wid : '<%= g.getBwriter() %>'
            }, 
            success : function(data){
               if(data == 'ok') {
                  $("#btnFollow").addClass('active');
                  $('#btnFollow').text('언팔로우');
               } else if (data == 'no') {
                  $("#btnFollow").removeClass('active');
               } 
            }, error : function(data){
               console.log('팔로우버튼 초기화 에러 발생!');
            }
         });
         
         // 좋아요 버튼 초기화
         $.ajax({
            url : '/crojecter/lCheck.li',
            type : 'get',
            data : {
               mid : '<%= m.getMid() %>',
               bid : '<%= g.getBid() %>'
            }, 
            success : function(data){
               if(data == 'ok') {
                  $("#btnLikeit").addClass('active');
               } else if (data == 'no') {
                  $("#btnLikeit").removeClass('active');
               } 
            }, error : function(data){
               console.log('좋아요버튼 초기화 에러 발생!');
            }
         });
      });
      
      // 댓글 삽입 
      function insertComment(obj) {
   
         var bid = '<%= g.getBid() %>';
         var btype = '<%= g.getBtype() %>';
         var cwriter = '<%= m.getMid() %>';
         var ccontent = $(obj).parent().siblings().children('textarea').val();
         var crefmid = $(obj).parent().parent().siblings().children('input[name="crefmid"]').val();
         
         if(ccontent.length == 0){
            alert("댓글 내용을 입력해 주세요.");
         } else {
            location.href='/crojecter/cInsert.co?bid=' + bid
                + '&btype=' + btype + '&cwriter=' + cwriter
                + '&ccontent=' + ccontent + '&crefmid=' + crefmid; 
         }
      }
      
      // 댓글 수정
      function updateComment(obj) {   
         $(obj).parent().parent().parent().siblings().children('textarea').css('display', 'block');
         $(obj).parent().parent().parent().siblings().children('p').css('display', 'none');
         $(obj).siblings('.updateConfirm').css('display','inline');
         $(obj).css('display', 'none');
      }
      
      // 댓글 수정 완료 
      function updateConfirm(obj) {
         var cid = $(obj).parent().parent().siblings().children('input[name="cid"]').val();
         var bid = '<%=g.getBid()%>';
         var btype = '<%= g.getBtype() %>';
         var content = $(obj).parent().parent().parent().siblings().children('textarea').val();
         
         location.href="/crojecter/cUpdate.co?cid="+cid+"&bid="+bid+"&btype="+btype+"&content="+content;
      }
      
      // 대댓글
      function reComment(obj){
         // 클릭한 버튼 숨기기
         $(obj).css('display', 'none');
         
         // 내용 입력 공간 만들기
         var htmlForm = '<div class="row contentArea1" style="">'
            + '<div class="col-md-1"></div>'
            + '<div class="col-md-9"><textarea rows="3" cols="70" id="ccontent" name="ccontent" style=""></textArea></div>'
            + '<div class="col-md-2"><button class="btnComment" id="addComment" onclick="insertComment(this);">댓글 등록</button></div>   '
            + '</div>';
         
         $(obj).parents('.comment').append(htmlForm);
      }      
      
      // 댓글 삭제 
      function deleteComment(obj) {
         var cid = $(obj).siblings('input[name="cid"]').val();
         var bid = '<%= g.getBid() %>';
         var btype = '<%= g.getBtype() %>';
         
         location.href="/crojecter/cDelete.co?cid="+cid+"&bid="+bid+"&btype="+btype;
      }   
   </script>
   
   <% if(m.getMid() == g.getBwriter()) { // 글쓴이 본인 %>    
   <script>
      $('#btnFollow').click(function(){
         alert("본인은 팔로우할 수 없습니다.");
      });
      $('#btnLikeit').click(function(){
         alert("본인 작품은 좋아요 할 수 없습니다.");
      });
      function showSpon() {
         alert("본인에게는 후원할 수 없습니다.");
      }
      function showReport() {
         alert("본인은 신고할 수 없습니다.");
      }   
   </script>
   <% } else { // 뷰어 입장 %>
   <script>
      // 후원하기
      function showSpon() {
            var windowObj = null;
         var xPos = (document.body.clientWidth / 2) - 250; 
          xPos += window.screenLeft;
          var yPos = (screen.availHeight / 2) - 150;
          windowObj = window.open('<%= request.getContextPath() %>/views/board/galleryboard/popupSpon.jsp', 
                '후원', 'width=500,height=300,top='+yPos+',left='+xPos
                +',toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
      }
      
      // 신고하기
      function showReport(bid, cid) {
         var windowObj = null;
         var xPos = (document.body.clientWidth / 2) - 250; 
          xPos += window.screenLeft;
          var yPos = (screen.availHeight / 2) - 150;
          var b = bid;
          var c = cid;
          
          windowObj = window.open('<%= request.getContextPath() %>/views/board/popupReport.jsp?b='+b+'&c='+c,
                '신고', 'width=500,height=550,top='+yPos+',left='+xPos
                +',toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
      }
      
      // 팔로우 버튼 클릭
      $('#btnFollow').click(function(){
         $.ajax({
            url : '/crojecter/fSwitch.fo',
            type : 'get',
            data : {
               mid : '<%= m.getMid() %>',
               wid : '<%= g.getBwriter() %>',
               mname : '<%= m.getMname() %>'
            }, 
            success : function(data){
               if(data == 'insert') {
                  $("#btnFollow").addClass('active');
                  $('#btnFollow').text('언팔로우');
               } else if (data == 'delete') {
                  $("#btnFollow").removeClass('active');
                  $('#btnFollow').text('팔로우');
               } else {
                  console.log('btnFollow() 에러 발생!');
               }
            }, error : function(data){
               console.log('btnFollow() 에러 발생!!');
            }
         });
      });
      
      // 좋아요 버튼 클릭
      $('#btnLikeit').click(function(){
         $.ajax({
            url : '/crojecter/lSwitch.li',
            type : 'get',
            data : {
               mid : '<%= m.getMid() %>',
               wid : '<%= g.getBwriter() %>',
               bid : '<%= g.getBid() %>', 
               mname : '<%= m.getMname() %>',
               btitle : '<%= g.getBtitle() %>'
            }, 
            success : function(data){
               if(data == 'insert') {
                  $("#btnLikeit").addClass('active');
               } else if (data == 'delete') {
                  $("#btnLikeit").removeClass('active');
               } else {
                  console.log('btnLikeit() 에러 발생!');
               }
            }, error : function(data){
               console.log('btnLikeit() 에러 발생!!');
            }
         });
      });
   
   </script>
   <% } %> <% } else { // 비회원 %>
	<script>
		$('button').click(function(){
			alert("로그인이 필요한 기능입니다.");
		});
		$('.btn').click(function(){
			alert("로그인이 필요한 기능입니다.");
		});
	</script>
	<% } %>
   
   <%@ include file="../../common/footer.jsp"%>
   
   
</body>
</html>