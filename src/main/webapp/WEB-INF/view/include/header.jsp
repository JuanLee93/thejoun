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
		position: relative;
		background-color: #eae5fa;
		border: 1px solid #eae5fa;
		border-radius: 4px;
		color: #484855;
		line-height: 52px;
		-webkit-transition: none;
		transition: none;
		text-shadow: 0 1px 1px rgba(0, 0, 0, .5);
	}

</style>
<script>
	$(function (){
		if (${!empty userInfo }) {
			$.ajax({
				url : "/thejoun/include/chatMain.do",
				type : "get",
				success : function(res){
					$("#chatMain").html(res);
					console.log("3");
				}
			});
		}
		if (${!empty userInfo }) {
			$.ajax({
				url : "/thejoun/include/notice.do",
				type : "get",
				success : function(res){
					$("#notice").html(res);
					console.log("2");
				}
			});
		}
	});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
</head>
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
						<li><div id="notice"></div></li>
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
					<li><a href="/thejoun/imageboard/index.do">이미지 갤러리</a></li>
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