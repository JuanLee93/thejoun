<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/view/include/header.jsp"%>
	<!-- MENU -->
	<div class="wrap">
		<div class="visual">
			<div class="swiper mySwiper">
				<div class="swiper-wrapper">
					<div class="swiper-slide"
						style="background-image: url('/thejoun/img/visual1.png');"></div>
					<div class="swiper-slide"
						style="background-image: url('/thejoun/img/visual2.png');"></div>
					<div class="swiper-slide"
						style="background-image: url('/thejoun/img/visual3.png');"></div>
					<div class="swiper-slide"
						style="background-image: url('/thejoun/img/visual4.png');"></div>
				</div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</div>
	</div>
	<div class="info">
		<div class="content">
			<div class="board_area">
				<div class="board_title on ">공지사항</div>
				<div class="board_title">자료실</div>
				<div class="board_content" id="board_notice">
					<ul>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
						<li>공지사항입니다.<span>2021-12-09</span></li>
					</ul>
				</div>
				<div class="board_content" id="board_data">
					<ul>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
						<li>자료실입니다.<span>2021-12-09</span></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="youtube_area">
				<iframe width="100%" height="400"
					src="https://www.youtube.com/embed/FNMOf8tlOVM"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>