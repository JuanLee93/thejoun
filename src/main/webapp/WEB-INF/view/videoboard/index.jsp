<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>동영상 갤러리</title>
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css"/>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>

<script>
	$(function() {
		$(".section").click(function() {
			location.href='view.do?board_no='+$(this).data("board_no");
		});
	});
	

</script>

</head>
<body>

    <div class="wrap">
        <%@ include file="/WEB-INF/view/include/header.jsp" %>
        <div class="sub">
			<div class="size">
            <p><span><strong>총 ${totCount }개</strong>  |  ${videoBoardVo.page }/${totPage }페이지</span></p>
			<h3 class="sub_title" style="text-align:center;">동영상 갤러리</h3>      		
      			<tbody>
      				<div class="btn1Set" style="text-align: left;">
					<a class="btn1" href="http://localhost:8080/thejoun/concernboard/index.do?orderby=readcount">조회수순</a> <a class="btn1" href="http://localhost:8080/thejoun/concernboard/index.do?orderby=l_count">좋아요순</a>
				</div>
	            <c:if test="${empty list }">
	                 <tr>
	               	    <td class="first" colspan="">등록된 글이 없습니다.</td>
	                 </tr>
				</c:if>
       		 	<c:if test="${!empty list }">
			        <div class="container" style="cursor: pointer;">
			            <div class="size">
       		  	<c:forEach var="vo" items="${list }" varStatus="status">
			                <div class="section"  data-board_no="${vo.video_board_no }" >              
			               	<tr>
								<li>
								<td valign="top" class="">
									<div class="gallery_list  ">
				                    <img src="${CommonUtil.getYoutubeImage(vo.url)}"><br>
				                    <font class="thumb_list_title">&nbsp;&nbsp;${vo.title }</font><br>
				                    <td>좋아요:${vo.l_count  }</td>
				                    <td>조회수:${vo.readcount  }</td><br>
				                    <span class="gallery_data ">작성자:${vo.nickname }</span>
				                </div>
				                </td>
				                </li>
			                </tr>
			                </div>
       			 </c:forEach>
			            </div>
			        </div>
        		</c:if>
        	</tbody>
        <div class="info">
			<div class="btn1Set" style="text-align: right;">
				<c:if test="${!empty userInfo }">
					<a class="btn1" href="write.do">글작성 </a>
				</c:if>
				<c:if test="${ empty userInfo }">
					<a class="btn1" href="javascript:alert('로그인 후 사용 가능합니다.'); location.href='/thejoun/user/login.do';">글작성 </a>
				</c:if>
			</div>
			${pageArea }

        </div>
			<!-- 페이지처리 -->
			<div class="bbsSearch">
				<form method="get" name="searchForm" id="searchForm" action="">
					<span class="srchSelect"> 
					<select id="stype"	name="searchType" class="dSelect" title="검색분류 선택">
							<option value="">전체</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="nickname">닉네임</option>
					</select> <span class="searchWord"> 
					<input type="text" id="sval"	name="searchWord" value="" title="검색어 입력"> 
					<input	type="button" id="" value="검색" title="검색">
					</span>
				</form>
			</div>
		</div>
		</div>
		</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>

</body>
</html>