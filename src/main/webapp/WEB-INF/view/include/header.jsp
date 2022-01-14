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
<<<<<<< HEAD
      <nav class="navbar navbar-expand-sm navbar-light">
        <div class="container">
            <a class="navbar-brand" href="/thejoun/index.do"><i class='uil uil-user'></i> THEJOUN(더조은)</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                        <a href="#about" class="nav-link"><span data-hover="소개">소개</span></a>
						<ul class="depth2">
                        <li><a href="/thejoun/company/index.do" class="nav-link">더조은 소개</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link"><span data-hover="커뮤니티">커뮤니티</span></a>
                        <ul class="depth2">
                        <li><a href="" class="nav-link">자유 게시판</a></li>
                        <li><a href="" class="nav-link">고민 게시판</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="#resume" class="nav-link"><span data-hover="갤러리">갤러리</span></a>
                        <ul class="depth2">
                        <li><a href="" class="nav-link">자유 갤러리</a></li>
                        <li><a href="" class="nav-link">동영상 갤러리</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="#contact" class="nav-link"><span data-hover="고객지원">고객지원</span></a>
                        <ul class="depth2">
                        <li><a href="" class="nav-link">공지 사항</a></li>
                        <li><a href="/thejoun/question/index.do" class="nav-link">문의사항</a></li>
                        </ul>
                    </li>
                </ul>
                
                <ul class="navbar-nav ml-lg-auto">
                    <div class="ml-lg-4">
                      <div class="color-mode d-lg-flex justify-content-center align-items-center">
                        <i class="color-mode-icon"></i>
                        다크 모드
                      </div>
                    </div>
                </ul>
            </div>
        </div>
    </nav>
=======
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
					<li><a href="">문의 사항</a></li>
				</ul>
			</li>
		</ul>
	</div>
>>>>>>> branch 'master' of https://github.com/JuanLee93/thejoun.git
</body>
</html>