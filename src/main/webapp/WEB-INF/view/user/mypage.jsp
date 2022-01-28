<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script>
	$(function (){
		main = myinfo();
	});
	
	function myinfo(){
		$.ajax({
			url:"/thejoun/user/myinfo.do",
			data : "",
			success:function(res){
				$("#myPageIndex").html(res);
			}
		});
	}
	function btn_useredit1() {
		$.ajax({
			url:"/thejoun/user/infoEdit.do",
			data : "",
			success:function(res){
				$("#myPageIndex").html(res);
				
			}
		});
	}
	function btn_useredit2() {
		$.ajax({
			url:"/thejoun/user/pwdChange.do",
			data : "",
			success:function(res){
				$("#myPageIndex").html(res);
			}
		});
	}
	function btn_useredit3() {
		$.ajax({
			url:"/thejoun/user/myinfo.do",
			data : "",
			success:function(res){
			$("#myPageIndex").html(res);
			}
		});
	}
	function btn_historyBack() {
		$.ajax({
			url:"/thejoun/user/myinfo.do",
			data : "",
			success:function(res){
			$("#myPageIndex").html(res);
			}
		});
	}
	
</script>
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
							<li role="presentation" class="active"><a href="#">나의 정보</a></li>
							<li role="presentation"><a href="#">나의 글 확인</a></li>
							<li role="presentation"><a href="#">친구 정보 확인</a></li>
							<li role="presentation"><a href="#">북마크</a></li>
							<li role="presentation"><a href="#">차단 친구 목록</a></li>
							<li role="presentation"><a href="#">1:1 문의</a></li>
						</ul>
					</div>
				</div>
				<div id="myPageIndex"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>