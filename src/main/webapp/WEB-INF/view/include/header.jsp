<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.friendsButton {font-size:14px; text-decoration:none !important; height:24px; white-space:nowrap; 
	display:inline-block; vertical-align:baseline; position:relative; cursor:pointer; padding:0px 10px; 
	min-width:60px; border:2px solid #221f1f; color:#fff !important; margin:0 2px; text-align:center; 
	font-weight:bold; border-radius:5px; background-color:#221f1f;}
	
	.login > ul > li{
	   float:left;
	   width:70px;
	   position:relative;
	}
	
		
	.button-1 {
	  padding: 5px 3px;
	  width: 130px;
	  height: 40px;
	  line-height: 42px;
	  border: none;
	  background-color: #eae5fa;
	  border-radius: 5px 5px;
	}
	.button-1:hover {
	  color: #c8bee8;
	  background: transparent;
	   box-shadow:none;
	}
	.button-1:before,
	.button-1:after{
	  content:'';
	  position:absolute;
	  top:0;
	  right:0;
	  height:2px;
	  width:0;
	  background: #c8bee8;
	  box-shadow:
	   -1px -1px 5px 0px #fff,
	   7px 7px 20px 0px #0003,
	   4px 4px 5px 0px #0002;
	  transition:400ms ease all;
	}
	.button-1:after{
	  right:inherit;
	  top:inherit;
	  left:0;
	  bottom:0;
	}
	.button-1:hover:before,
	.button-1:hover:after{
	  width:100%;
	  transition:800ms ease all;
	}

</style>
<script>
	$(function (){
		if (${!empty userInfo }){
			chatMain();
			notice();
			setInterval(chatMain,100000);
		}
	});
	
	function chatMain(){
		$.ajax({
			url : "/thejoun/include/chatMain.do",
			type : "get",
			success : function(res){
				$("#chatMain").html(res);
				console.log("3");
			}
		});
	}
	//알림창 ajax로 받아오는거
	function notice(){
		$.ajax({
			url : "/thejoun/include/notice.do",
			type : "get",
			success : function(res){
				$("#notice").html(res);
				console.log("2");
			}
		});
	}
	//알림창 버튼 누르면 알림내용 뜨게하고 다시누르면 사라지게하는거
	function view(){
		$(".noticeMain").toggle();
	}
	
	//알림창에서 누르면 해당 게시판으로 이동하는거
	function goBoard(boardno, tablename){
		if(tablename == 1){
			location.href="/thejoun/freeboard/view.do?board_no="+boardno;
		}else if(tablename == 2){
			location.href="/thejoun/concernboard/view.do?board_no="+boardno;
		}else if(tablename == 3){
			location.href="/thejoun/imageboard/view.do?image_board_no="+boardno;	
		}else if(tablename == 4){
			location.href="/thejoun/videoboard/view.do?board_no="+boardno;
		}
	}
	
	//알림창에서 모두지우기 누르면 알림 다 삭제되는거
	function deleteAnnounce(){
		$.ajax({
			url : "/thejoun/include/deleteAnnounce.do",
			type : "post",
			success : function(res){
				console.log("10");
				alert(res.trim()+"개의 알림이 삭제되었습니다.");
				notice();
			}
		});
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div class="header">
		<div class="size">
			<a href="/thejoun/index.do"><img src="/thejoun/img/logo.png"></a>
			<div class="login">
				<ul>
					<c:if test="${empty userInfo }">
						<li><a href="/thejoun/user/login.do" class="button-1" >로그인</a> </li> 
						<li><a href="/thejoun/user/join.do"  class="button-1">회원가입</a></li>
					</c:if>
					<c:if test="${!empty userInfo }">
						<li><a href="/thejoun/user/logout.do" class="button-1">로그아웃</a> </li> 
						<li><a href="/thejoun/user/mypage.do" class="button-1">마이페이지</a> </li>
						<li><div id="chatMain"></div></li>
						<li><div id="notice" style="cursor : pointer;"></div></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
    <div class="menu">
		<ul class="depth1">
			<li><a href="/thejoun/company/index.do">소개</a>
				<ul class="depth2">
					<li><a href="/thejoun/company/index.do">더조은 소개</a></li>
				</ul></li>
			<li><a href="">커뮤니티</a>
				<ul class="depth2">
					<li><a href="/thejoun/freeboard/index.do">자유 게시판</a></li>
					<li><a href="/thejoun/concernboard/index.do">고민 게시판</a></li>
				</ul></li>
			<li><a href="">갤러리</a>
				<ul class="depth2">
					<li><a href="/thejoun/imageboard/index.do">자유 갤러리</a></li>
					<li><a href="/thejoun/videoboard/index.do">동영상 갤러리</a></li>
				</ul></li>
			<li><a href="">고객지원</a>
				<ul class="depth2">
					<li><a href="/thejoun/notice/index.do">공지 사항</a></li>
					<li><a href="/thejoun/question/index.do">문의 사항</a></li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>