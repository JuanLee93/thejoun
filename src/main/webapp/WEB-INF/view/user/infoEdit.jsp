<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>THEJOUN(더조은)</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script src="/thejoun/css/common.css"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script>
	function infoEditDetails() {
		if ($("#id").val() == '') {
			alert("아이디를 입력해 주세요");
			$("#id").focus();
			return false;
		}
		if ($("#pwd").val() == '') {
			alert("비밀번호를 입력해 주세요");
			$("#pwd").focus();
			return false;
		}
	}
</script>
<style>
#board > div > ul {
    position: relative;
    padding: 10px 15px 15px;
    float: right;
    width: 75%;
    height: 350px;
    text-align:center;
}
div > input[type=button] {
	border: 2px solid;
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
							<li role="presentation"><a href="/thejoun/user/mypage.do">나의
									정보</a></li>
							<li role="presentation"><a
								href="/thejoun/user/myBoardConfirm.do">나의 글 확인</a></li>
							<li role="presentation"><a
								href="/thejoun/user/myFriendsConfirm.do">친구 정보 확인</a></li>
							<li role="presentation" class="active"><a
								href="/thejoun/user/bookmark.do">북마크</a></li>
							<li role="presentation"><a href="/thejoun/user/myInquiry.do">1:1
									문의</a></li>
						</ul>
					</div>
				</div>
				<form action="infoEdit.do" method="post" id="board" name="board"
					onsubmit="return loginCheck();">
					<div class="size">
						<ul>
							<h3 class="sub_title2">회원정보 수정</h3>
							<h4>비밀번호(재확인) 인증</h4>
							<br>
							<li><i class="bi bi-person"></i></i>&nbsp;&nbsp;&nbsp; <input
								type="text" id="id" name="id" placeholder="아이디"></li>
							<li><i class="bi bi-key"></i>&nbsp;&nbsp;&nbsp; <input
								type="password" id="pwd" name="pwd" placeholder="비밀번호">
							</li>
							<br>
							<div class="login_btn">
								<input type="submit" value="로그인" alt="로그인"> <input
									type="button" value="이전 페이지로" onclick="myinfo()">
							</div>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>