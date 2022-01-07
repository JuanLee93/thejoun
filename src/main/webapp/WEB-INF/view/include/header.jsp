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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

   	<link rel="stylesheet" href="/thejoun/css/bootstrap.min.css">
    <link rel="stylesheet" href="/thejoun/css/unicons.css">
    <link rel="stylesheet" href="/thejoun/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/thejoun/css/owl.theme.default.min.css">

    <!-- MAIN STYLE -->
    <link rel="stylesheet" href="/thejoun/css/tooplate-style.css">
    <style>
    	.depth2 {
    		position:absolute;	
    	}
    	.login01{
    		margin:40px; 20px; 0px;
    		padding: 0px 250px; 
    	}
    	.login01 > .logina{
    		border-top-left-radius: 5px;
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px;
			border-bottom-right-radius: 5px;
			border: 1px solid black; 
			background-color: #a8a8aa;
			padding: 0px 12px; 
			width : 12%;
			text-align: right;
			
    	}
		.login01 > .loginbtn1 > a{
		    border-top-left-radius: 5px;
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px;
			border-bottom-right-radius: 5px;
			color: #fff; 
			background-color: #a8a8aa;
			padding: 10px 25px;
			
		}
    </style>
    
  </head>
	<div class="login01" style="text-align: right;"  style = "margin: 5px 15px;">
		<c:if test="${empty userInfo }">
		<div class="loginbtn1">
			<a href="/thejoun/user/login.do">로그인</a> 
			<a href="/thejoun/user/join.do">회원가입</a>
		</div>
		
		</c:if>
		<div class="loginbtn1">
		<c:if test="${!empty userInfo }">
			<a href="/thejoun/user/logout.do">로그아웃</a>
			<a href="/thejoun/user/join.do">마이페이지</a>
		</c:if>
		</div>
	</div>
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
                        <li><a href="" class="nav-link">문의사항</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="#contact" class="nav-link"><span data-hover="고객지원">고객지원</span></a>
                        <ul class="depth2">
                        <li><a href="" class="nav-link">공지 사항</a></li>
                        <li><a href="" class="nav-link">자주하는 질문</a></li>
                        <li><a href="" class="nav-link">동영상 갤러리</a></li>
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
</body>
</html>