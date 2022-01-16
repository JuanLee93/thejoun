<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
				<a href="/thejoun/user/mypage.do">마이페이지</a>
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
					<li><a href="">공지 사항</a></li>
					<li><a href="/thejoun/question/index.do">문의 사항</a></li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>