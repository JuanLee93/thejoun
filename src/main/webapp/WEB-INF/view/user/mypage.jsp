<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>THEJOUN(더조은)</title>
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script src="/thejoun/css/common.css"></script>
<link rel="stylesheet" href="/thejoun/css/mypage_index.css" />
<link rel="stylesheet" href="/thejoun/css/mypage_info.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<style>
div.userinfo > ul > img {
	max-height: 200px;
    max-width: 200px;
    margin: 20px 20px 20px 20px;
    float: left;
}
</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
		<div class="sub">
			<div class="size">
				<h3 class="sub_title1">마이 페이지</h3>
				<hr>
				<div class="bs-example" data-example-id="simple-nav-stacked">
					<div class="bs-example.left">
						<ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
							<li role="presentation" class="active"><a href="/thejoun/user/mypage.do">나의 정보</a></li>
							<li role="presentation"><a href="/thejoun/user/myBoardConfirm.do">나의 글 확인</a></li>
							<li role="presentation"><a href="/thejoun/user/myFriendsConfirm.do">친구 정보 확인</a></li>
							<li role="presentation"><a href="/thejoun/user/bookmark.do">북마크</a></li>
							<li role="presentation"><a href="/thejoun/user/myInquiry.do">1:1 문의</a></li>
						</ul>
					</div>
				</div>
				<div class="userinfo">
					<ul>
						<h3>나의 정보</h3><hr>
							<c:if test="${!empty userInfo.filename_org }">
								<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${userInfo.filename_org}&real=${userInfo.filename_real}"
									target="_blank"'/>">
							</c:if>
							<c:if test="${empty userInfo.filename_org}">
								<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
							</c:if>
						<li>
							<a>아이디 : ${userInfo.id}</a><br><br><br>
							<a>닉네임 : ${userInfo.nickname}</a><br><br><br>
							<a>이메일 : ${userInfo.email}</a><br><br><br>
						</li>
					</ul>
					<ul><br><br>
						<div id="btn_group" style="text-align:center;">
    						<button class="btn_useredit1"><a href="/thejoun/user/infoEdit.do">회원정보 수정</button>
    						<button class="btn_useredit1"><a href="/thejoun/user/pwdChange.do">비밀번호 변경</button>
						</div>
					</ul>
				</div>
				<div>
					
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>