<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
</head>
<!--  
<script>
	$(function() {
		$(".board_tr").click(function() {
			location.href = 'view.do?board_no=' + $(this).data("board_no");
		});
	});
</script>
-->
<body>
	<div id="wrap">
		<!-- canvas -->
		<div id="canvas">
			<!-- S T A R T :: headerArea-->
			<%@ include file="/WEB-INF/view/admin/include/top.jsp"%>
			<!-- E N D :: headerArea-->

			<!-- S T A R T :: containerArea-->
			<div id="container">
				<div id="content">
					<div class="con_tit">
						<h2>회원관리 - [회원 목록]</h2>
					</div>
					<!-- //con_tit -->
					<div class="con">
						<!-- 내용 : s -->
						<div id="bbs">
							<div id="blist">
								<p>
									<span><strong>총 ${totCount }개</strong> |	${userVo.page }/${totPage }페이지</span>
								</p>
								<form name="frm" id="frm" action="process.do" method="post">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										summary="관리자 관리목록입니다.">
										<colgroup>
											<col class="w3" />
											<col class="w6" />
											<col class="" />
											<col class="" />
											<col class="w5" />
											<col class="w6" />
											<col class="w6" />
											<col class="w6" />
										</colgroup>
										<thead>
											<tr>  
												<th scope="col">회원번호</th>
												<th scope="col">이름</th>
												<th scope="col">아이디</th>
												<th scope="col">닉네임</th>
												<th scope="col">성별</th>
												<th scope="col">이메일</th>
												<th scope="col">전화번호</th>
												<th scope="col" class="last" >상태</th>
											</tr>
										</thead>
										<tbody>

											<c:if test="${empty list }">
												<tr>
													<td class="first" colspan="8">등록된 글이 없습니다.</td>
												</tr>
											</c:if>
											<c:if test="${!empty list }">
												<c:forEach var="vo" items="${list }" varStatus="status">
													<tr class="board_tr" data-board_no="${vo.userno }"	style="cursor: pointer;">
														<td>${vo.userno }</td>
														<td class="txt_l">${vo.name }</td>
														<td >${vo.id }</td>
														<td >${vo.nickname }</td>
														
														<td class="date">
														<c:if test="${vo.gender == 1}"> 
														남성
														</c:if>
														<c:if test="${vo.gender == 2}"> 
														여성
														</c:if></td>
														<td class="writer">${vo.email }</td>
														<td class="Last">${vo.tel }</td>
														<td class="last">정상</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</form>
								<!--//btn-->
								<!-- 페이징 처리 -->
								<div class='page'>${pageArea }</div>
								<!-- //페이징 처리 -->
								<div class="bbsSearch">
									<form method="get" name="searchForm" id="searchForm" action="">
										<div class="search">
											<select id="stype" name="searchType" class="dSelect"
												title="검색분류 선택">
												<option value="">전체</option>
												<option value="id">아이디</option>
												<option value="nickname">닉네임</option>
											</select> <input type="text" id="sval1" name="searchWord" value=""
												title="검색어 입력"> <input type="image"
												src="<%=request.getContextPath()%>/images/admin/btn_search.gif"
												class="sbtn" alt="검색" />
										</div>
									</form>
								</div>
							</div>
							<!-- //search -->
						</div>
						<!-- //blist -->
					</div>
					<!-- //bbs -->
					<!-- 내용 : e -->
				</div>
				<!--//con -->
			</div>
			<!--//content -->
		</div>
		<!--//container -->
		<!-- E N D :: containerArea-->
	</div>
	<!--//canvas -->
	</div>
	<!--//wrap -->

</body>
<style>
.pagenate {
	width: 100%;
	clear: both;
}

.pagenate {
	text-align: center;
	margin: 20px auto 0;
}

.pagenate li {
	display: inline-block;
}

.pagenate li:first-child {
	margin-left: 0px;
}

.pagenate li a {
	display: inline-block;
	text-decoration: none;
	padding: 0;
	width: 30px;
	height: 30px;
	line-height: 30px;
	border: 1px solid #c7c8cc;
	box-sizing: border-box;
	margin-left: -1px;
	vertical-align: middle;
}

.pagenate li a:hover {
	background: #f6f6f6;
	font-weight: bold;
	text-decoration: none !important;
}

.pagenate li a.board {
	text-indent: -9999em;
	margin-left: 4px;
}

.pagenate li a.board.first {
	background: #f3f3f3 url('/img/ico_first.png') no-repeat center center;
}

.pagenate li a.board.prev {
	margin-right: 30px;
	background: #efefef url('/img/ico_prev.png') no-repeat center center;
}

.pagenate li a.board.next {
	margin-left: 30px;
	background: #efefef url('/img/ico_next.png') no-repeat center center;
}

.pagenate li a.board.last {
	background: #f3f3f3 url('/img/ico_last.png') no-repeat center center;
}

.pagenate li a.current {
	color: #fff;
	background-color: #221f1f;
	font-weight: bold;
	border: 1px solid #221f1f;
}
</style>
</html>
