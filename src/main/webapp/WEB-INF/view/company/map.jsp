<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="/project/css/common.css"/>
    <link rel="stylesheet" href="/project/css/sub.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
    <script src="/project/js/common.js"></script>
</head>
<body>
    <div class="wrap">
       <%@ include file="/WEB-INF/view/include/header.jsp" %>
        <div class="sub">
        	<div class="sub_visual">
        		<div class="slogan">
        			오시는길
        		</div>
        	</div>
        	<div class="size">
        		<div id="map" style="width:100%;height:500px;"></div>
        		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b038000f5a39d076634791a2e7ad405b&libraries=services"></script>
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
</body>
</html>