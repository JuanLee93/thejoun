<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>THEJOUN(๋์กฐ์)</title>
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
div.selectMyBoard > ul {
	position:relative;
	padding: 10px 15px 15px;
	float:right;
	width:75%;
	height: 570px
}
div.selectMyBoard > ul > table > tbody > tr > td {
	text-align:center;
}

div.selectMyBoard > ul > h3 {
    text-align: center;
    padding: 10px 0px 15px 0px;
    font-size: 20px;
}

.board_tr{
	padding : 5px auto;
}

div.selectMyBoard > ul > div > ul {
 	list-style: none;
    vertical-align: middle;
}
</style>
<script>
function goMyBoard(board_no, tablename){
	if(tablename == 1){
		location.href="/thejoun/freeboard/view.do?board_no="+board_no;
	}else if(tablename == 2){
		location.href="/thejoun/concernboard/view.do?board_no="+board_no;
	}else if(tablename == 3){
		location.href="/thejoun/imageboard/view.do?image_board_no="+board_no;	
	}else if(tablename == 4){
		location.href="/thejoun/videoboard/view.do?board_no="+board_no;
	}
}
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
		<div class="sub">
			<div class="size">
				<h3 class="sub_title1">๋ง์ด ํ์ด์ง</h3>
				<hr>
				<div class="bs-example" data-example-id="simple-nav-stacked">
					<div class="bs-example.left">
						<ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
							<li role="presentation" ><a href="/thejoun/user/mypage.do">๋์ ์?๋ณด</a></li>
							<li role="presentation" class="active"><a href="/thejoun/user/myBoardConfirm.do">๋์ ๊ธ ํ์ธ</a></li>
							<li role="presentation"><a href="/thejoun/user/myFriendsConfirm.do">์น๊ตฌ ์?๋ณด ํ์ธ</a></li>
							<li role="presentation"><a href="/thejoun/user/bookmark.do">๋ถ๋งํฌ</a></li>
							<li role="presentation"><a href="/thejoun/user/myInquiry.do">1:1 ๋ฌธ์</a></li>
						</ul>
					</div>
				</div>
				<div class="selectMyBoard">
					<ul>
						<h3>๋์ ๊ธ ํ์ธ</h3>
						<div class="bbs">
						<table class="list">
                    <p><span><strong>์ด ${totCount }๊ฐ</strong>  |  ${userVo.page }/${totPage }ํ์ด์ง</span></p>
                        <caption>์์?๊ฒ์ํ ๋ชฉ๋ก</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>๋ฒํธ</th>
                                <th>์?๋ชฉ</th>
                                <th>๊ธ์ด์ด</th>
                                <th>๋ฑ๋ก์ผ</th>
                                <th>์กฐํ์</th>
                                <th>์ข์์</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:if test="${empty list }">
                            <tr>
                                <td class="first" colspan="6" style="text-align:center">๋ฑ๋ก๋ ๊ธ์ด ์์ต๋๋ค.</td>
                            </tr>
						</c:if>
                        <c:if test="${!empty list }">
                        <c:forEach var="vo" items="${list }" varStatus="status">
                            <tr class="board_tr" data-boardno="${vo.board_no }" style="cursor: pointer;" onclick="goMyBoard(${vo.board_no},${vo.tablename })">
                            	<c:if test="${vo.noticeYN == 'Y' }">
                            	<td>[๊ณต์ง]</td>
                            	</c:if>
                            	<c:if test="${vo.noticeYN != 'Y' }">
                                <td>${(totCount-status.index) - ((freeBoardVo.page-1)*10) }</td>
                                </c:if>
                                <td class="txt_l" style="text-align:left;">
	                                <c:if test="${(vo.new_time) <= 3}">
	                                	<span><img src="/thejoun/images/admin/new_ico.gif"></span>
	                                </c:if>
                                    ${vo.title } [${vo.c_count }]
                                </td>
                                <td class="writer">
                                	<c:if test="${!empty vo.nickname}">
                                  	  ${vo.nickname }
                                   </c:if>
                                   <c:if test="${empty vo.nickname}">
                                  	  ๊ด๋ฆฌ์
                                   </c:if>
                                </td>
                                <td class="date">${vo.regdate }</td>
                                <td>${vo.readcount }</td>
                                <td>${vo.l_count }</td>
                            </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                         ${pageArea }
                    </div>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>