<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고민게시판</title>
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/common.css?after" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script>
	$(function() {
		$(".board_tr").click(function() {
			location.href = 'view.do?board_no=' + $(this).data("board_no");
		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
		<div class="sub">
			<div class="size">
				<h3 class="sub_title">고민게시판</h3>
				<div class="btn1Set" style="text-align: left;">
					<a class="btn1"
						href="http://localhost:8080/thejoun/concernboard/index.do?orderby=readcount&category=${concernBoardVo.category }">조회수
						순</a> <a class="btn1"
						href="http://localhost:8080/thejoun/concernboard/index.do?orderby=l_count&category=${concernBoardVo.category }">좋아요
						순</a>
				</div>
				<div class="menu1">
					<ul class="category">
						<li><a
							href="http://localhost:8080/thejoun/concernboard/index.do">전체</a></li>
						<li><a
							href="http://localhost:8080/thejoun/concernboard/index.do?category=1">취업/진로</a></li>
						<li><a
							href="http://localhost:8080/thejoun/concernboard/index.do?category=2">외모</a></li>
						<li><a
							href="http://localhost:8080/thejoun/concernboard/index.do?category=3">연애</a></li>
						<li><a
							href="http://localhost:8080/thejoun/concernboard/index.do?category=4">대인관계</a></li>
					</ul>
				</div>
				<div class="hg" style="height: 25px"></div>
				<div class="bbs">
					<table class="list">
						<p>
							<span><strong>총 ${totCount }개</strong> |
								${concernBoardVo.page }/${totPage }페이지</span>
						</p>
						<caption>고민게시판 목록</caption>
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
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>등록일</th>
								<th>조회수</th>
								<th>좋아요</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty list }">
								<tr>
									<td class="first" colspan="6">등록된 글이 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${!empty list }">
								<c:forEach var="vo" items="${list }" varStatus="status">
									<tr class="board_tr" data-board_no="${vo.concern_board_no }" style="cursor: pointer;">
                            	<c:if test="${vo.noticeYN == 'Y' }">
                            	<td>[공지]</td>
                            	</c:if>
                            	<c:if test="${vo.noticeYN == 'N' }">
                                <td>${(totCount-status.index) - ((ConcernBoardVo.page-1)*10) }</td>
                                </c:if>
	                                <td class="txt_l" style="text-align:left;">
	                                <c:if test="${(vo.new_time) <= 3}">
	                                	<span><img src="/thejoun/images/admin/new_ico.gif"></span>
	                                </c:if>
	                                    ${vo.title } [${vo.c_count }]
	                                </td>
	                                <td class="writer">
	                                    <c:if test="${!empty vo.nickname }">
	                                    ${vo.nickname }
	                                    </c:if>
	                       			    <c:if test="${empty vo.nickname}">
                                  		  관리자
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
					<div class="btn1Set" style="text-align: right;">
						<c:if test="${!empty userInfo }">
							<a class="btn1" href="write.do">글작성 </a>
						</c:if>
						<c:if test="${empty userInfo }">
							<a class="btn1"
								href="javascript:alert('로그인 후 사용가능합니다.'); location.href='/thejoun/user/login.do';">글작성
							</a>
						</c:if>
					</div>
					${pageArea }

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
		<%@ include file="/WEB-INF/view/include/footer.jsp"%>
	</div>
</body>
</html>