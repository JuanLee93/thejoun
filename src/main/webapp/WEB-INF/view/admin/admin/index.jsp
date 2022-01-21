<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
</head>
<script>
	$(function() {
		$(".board_tr").click(function() {
			location.href = 'view.do?admin_no=' + $(this).data("admin_no");
		});
	});
</script>
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
					<h2>관리자관리 - [관리자 목록]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="blist">
							<p>
								<span><strong>총 ${totCount }개</strong> |	${adminVo.page }/${totPage }페이지</span>
							</p>
							<form name="frm" id="frm" action="process.do" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									summary="관리자 관리목록입니다.">
									<colgroup>
										<col class="w3" />
										<col class="w5" />
										<col class="w5" />
										<col class="w5" />
										<col class="" />
									</colgroup>
									<thead>
										<tr>
											<th scope="col" class="first"><input type="checkbox" name="allChk" id="allChk" onClick="check(this, document.frm.no)" /></th>
											<th scope="col">관리자번호</th>
											<th scope="col">아이디</th>
											<th scope="col">이름</th>
											<th scope="col">접속기록</th>
										</tr>
									</thead>
									<tbody>

										<c:if test="${empty list }">
											<tr>
												<td class="first" colspan="5">등록된 글이 없습니다.</td>
											</tr>
										</c:if>
										<c:if test="${!empty list }">
											<c:forEach var="vo" items="${list }" varStatus="status">
												<tr class="board_tr" data-admin_no="${vo.admin_no }" style="cursor: pointer;">
													<td scope="col" class="first"><input type="checkbox" name="allChk" id="allChk" onClick="check(this, document.frm.no)" /></td>
													<td>${vo.admin_no }</td>
													<td>${vo.id }</td>
													<td>${vo.name }</td>
													<td>최신 접속기록 뜨는 곳</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</form>
							<div class="btn">
								<div class="btnLeft">
									<a class="btns" href="#" onclick=""><strong>삭제</strong> </a>
								</div>
								<div class="btnRight">
									<a class="wbtn" href="write.do"><strong>등록</strong> </a>
								</div>
							</div>
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
											<option value="admin_no">관리자번호</option>
											<option value="id">아이디</option>
											<option value="name">이름</option>
										</select> <input type="text" id="sval1" name="searchWord" value=""
											title="검색어 입력"> <input type="image"
											src="<%=request.getContextPath()%>/images/admin/btn_search.gif"
											class="sbtn" alt="검색" />
									</div>
								</form>
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
</html>
