<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유갤러리</title>
<link rel="stylesheet" href="/thejoun/css/reset.css"/>
<link rel="stylesheet" href="/thejoun/css/common.css?after"/>
<link rel="stylesheet" href="/thejoun/css/contents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script>
	$(function() {
		$(".section").click(function() {
			location.href='view.do?image_board_no='+$(this).data("image_board_no");
		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">자유갤러리</h3>
		        <div class="btn1Set"  style="text-align:left;">
					<a class="btn1" href="/thejoun/imageboard/index.do?orderby=readcount&category=${imageBoardVo.category }">조회수 순</a>
		            <a class="btn1" href="/thejoun/imageboard/index.do?orderby=l_count&category=${imageBoardVo.category }">좋아요 순</a>
		        </div>
		         <div class="menu1">
		            <ul class="category" >
		                <li><a href="/thejoun/imageboard/index.do">전체</a></li>
		                <li><a href="/thejoun/imageboard/index.do?category=1">인물</a></li>
		                <li><a href="/thejoun/imageboard/index.do?category=2">음식</a></li>
		                <li><a href="/thejoun/imageboard/index.do?category=3">풍경</a></li>
		                <li><a href="/thejoun/imageboard/index.do?category=4">기타</a></li>
		            </ul>
		        </div>
	        	<div class="hg" style="height: 25px"></div>
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총 ${totCount }개</strong>  |  ${imageBoardVo.page }/${totPage }페이지</span></p>
	                <tbody>
			            <c:if test="${empty list }">
			                 <tr>
			               	    <td class="first" colspan="">등록된 글이 없습니다.</td>
			                 </tr>
						</c:if>
		       		 	<c:if test="${!empty list }">
					        <div class="container" style="cursor: pointer;">
					            <div class="size">
		       		  				<c:forEach var="vo" items="${list }" varStatus="status">
					                <div class="section"  data-image_board_no="${vo.image_board_no }">
					                    <img src="${CommonUtil.getImgUrl(vo.content) }">
					                    <div class="txt_l" style="text-align:left;">
					                    [제목] ${vo.title } [${vo.c_count }]<br>
					                    [작성자] ${vo.nickname }<br>
					                    [조회수] ${vo.readcount }<br>
					                    [좋아요] ${vo.l_count }<br>
					                    </div>
					                </div>
		       			 			</c:forEach>
					            </div>
					        </div>
		        		</c:if>
        			</tbody>
                    </table>
                    <div class="info">
	                    <div class="btn1Set"  style="text-align:right;">
							<c:if test="${!empty userInfo }">
		                        <a class="btn1" href="write.do">글작성 </a>
		  					</c:if>
		  					<c:if test="${empty userInfo }">
		                        <a class="btn1" href="javascript:alert('로그인 후 사용가능합니다.'); location.href='/thejoun/user/login.do';">글작성 </a>
		                    </c:if>
	                    </div>
	                    
                    	${pageArea }
                    
					</div>
                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="stype" name="searchType" class="dSelect" title="검색분류 선택">
                                    <option value="">전체</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="nickname">닉네임</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="searchWord" value="" title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색">
                            </span>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>