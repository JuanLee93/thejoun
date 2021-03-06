<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
 	<script src="/thejoun/js/common.js"></script> 
    <!--  <script src="/thejoun/js/jquery-3.3.1.min.js"></script>-->
    <link rel="stylesheet" href="/thejoun/css/common.css?after"/>
	<link rel="stylesheet" href="/thejoun/css/sub.css"/>
	<link rel="stylesheet" href="/thejoun/css/reset.css" />

</head>
<body>
    <div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
        <div class="sub">
        	<div class="sub_visual">
        		<div class="slogan">
        			회사소개
        		</div>
        	</div>
        	<div class="size">
        		<div class="box">
        			<div class="txt">
        				<h4 class="title">THEJOUN입니다.</h4><br>
        				<br>
        				<br>
        				<p>
        				나쁜 기억을 글로 쓰는 행위만으로도<br>
        				정서적 고통이 감소되는 효과가 있다고 해요.<br>
        				
        				</p>
        				<p>
						여러분의 고민과 생각들 <br>
						그밖에 다양하게 소통하고 싶은 내용들을 <br>
						자유롭게 소통해보세요. <br>
						또한 내 주변 친구들과 대화할 수 있어요.
        				</p>
        				<p>
        				나쁜 기억을 글로 쓰는 행위만으로도 <br>
        				정서적 고통이 감소되는 효과가 있다고 해요.<br>
						누구나 마음속에 고민을 갖고 있어요.<br>
						THE JOUN에서 자신의 마음을 털어놓는 시간을 가져보세요
        				</p>
        				<p>
        				<br>
						더 조은과 함께 마음의 소통을 시작해보세요.
        				</p>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="sub">
        <div class="btn04">
	    <form>
			<div id="btn_group" style="text-align : center;"}> 
				<button type="button" id="test_btn1" style="cursor: pointer;" onclick="location.href='/thejoun/freeboard/index.do';" >자유게시판</button> 
				<button type="button" id="test_btn2" style="cursor: pointer;" onclick="location.href='/thejoun/concernboard/index.do';">고민게시판</button> 
				<button type="button" id="test_btn3" style="cursor: pointer;" onclick="location.href='/thejoun/imageboard/index.do';">자유갤러리</button> 
				<button type="button" id="test_btn4" style="cursor: pointer;" onclick="location.href='/thejoun/videoboard/index.do';">동영상갤러리</button> 
			</div>
		</form>
		</div>
		</div>
        <div class="wrap">
        	<div class="sub">
				<div class="load">
        			<p>오시는길</p>
				</div>
        	<div class="size">
        		<div id="map" style="width:90%;height:500px;margin:0 auto; text-align:center;"></div>
        		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=011e25fb01a63c0ba70c18414e7adf5c&libraries=services"></script>
        		<script>
					var container = document.getElementById('map');
					var options = {
						center: new kakao.maps.LatLng(37.58017097279185, 126.97796092598705),
						level: 3
					};
			
					var map = new kakao.maps.Map(container, options);
					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색합니다
					geocoder.addressSearch('서울특별시 종로구 삼일대로17길 51', function(result, status) {

						// 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {
	
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });
	
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					            content: '<div style="width:150px;text-align:center;padding:6px 0;">더조은</div>'
					        });
					        infowindow.open(map, marker);
	
					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
					    } 
					});    
				</script>
        	</div>
        </div>
        <%@ include file="/WEB-INF/view/include/footer.jsp" %>
    </div>
  </div>
</body>
</html>