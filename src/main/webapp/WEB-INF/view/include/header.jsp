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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
</head>
	 <div class="header">
		<div class="size">
			<div>
				<a href="/thejoun/index.do"><img src="/thejoun/img/logo.png"></a>
			</div>
			<div class="login">
			<c:if test="${empty userInfo }">
				<a href="/thejoun/user/login.do">로그인</a> | 
				<a href="/thejoun/user/join.do">회원가입</a>
			</c:if>
			<c:if test="${!empty userInfo }">
				<a href="/thejoun/user/logout.do">로그아웃</a> | 
				<a href="/thejoun/user/mypage.do">마이페이지</a> |
				<a href="/thejoun/chat/index.do">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
						<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
						<path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
					</svg>
				</a>
				<div class="">
					<button class="friendsButton">알림</button>
				</div>
				<ul class="">
					<c:forEach var="vo" items="" varStatus="status">
						
					</c:forEach>
				</ul>
			</c:if>
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