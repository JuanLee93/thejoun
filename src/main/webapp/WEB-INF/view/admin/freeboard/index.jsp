<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp" %>
<script>
	$(function() {
		$(".title").click(function() {
			location.href='view.do?board_no='+$(this).data("board_no");
		});
	});
</script>
</head>
<body> 
<div id="wrap">
	<!-- canvas -->
	<div id="canvas">
		<!-- S T A R T :: headerArea-->
		<%@ include file="/WEB-INF/view/admin/include/top.jsp" %>
		<!-- E N D :: headerArea--> 
		
		<!-- S T A R T :: containerArea-->
		<div id="container">
			<div id="content">
				<div class="con_tit">
					<h2>게시물관리 - 자유게시판[목록]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="blist">
							<p><span><strong>총 ${totCount }개</strong>  |  ${freeBoardVo.page }/${totPage }페이지</span></p>
							<form name="frm" id="frm" action="process.do" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 자유게시판 관리목록입니다.">
								<colgroup>
									<col class="w3" />
									<col class="w4" />
									<col class="" />
									<col class="w10" />
									<col class="w5" />
									<col class="w6" />
									<col class="w6" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="first"><input type="checkbox" name="allChk" id="allChk" onClick="check(this, document.frm.no)"/></th>
										<th scope="col">번호</th>
										<th scope="col">제목</th> 
										<th scope="col">작성자</th> 
										<th scope="col">작성일</th> 
										<th scope="col">조회수</th>
										<th scope="col" class="last">상태</th>
									</tr>
								</thead>
								<tbody>
								<c:if test="${empty list }">
		                            <tr>
		                                <td class="first" colspan="7">등록된 글이 없습니다.</td>
		                            </tr>
								</c:if>
		                        <c:if test="${!empty list }">
		                        <c:forEach var="vo" items="${list }" varStatus="status">
		                            <tr>
		                            	<td class="first"><input type="checkbox" name="no" id="no" value=""/></td>
		                                <td>${(totCount-status.index) - ((freeBoardVo.page-1)*10) }</td>
		                                <td class="title" data-board_no="${vo.board_no }" style="cursor: pointer;">
		                                    ${vo.title }
		                                </td>
		                                <td class="writer">
		                                    ${vo.nickname }
		                                </td>
		                                <td class="date">${vo.regdate }</td>
		                                <td>${vo.readcount }</td>
		                                <td class="last">정상</td>
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
							
							<div class='page'>
							${pageArea }
							</div>
							
							<!-- //페이징 처리 -->
							<div class="bbsSearch">
								<form method="get" name="searchForm" id="searchForm" action="">
									<div class="search">
										<select id="stype" name="searchType" class="dSelect"
											title="검색분류 선택">
											<option value="">전체</option>
											<option value="title">제목</option>
											<option value="content">내용</option>
											<option value="nickname">닉네임</option>
										</select> <input type="text" id="sval1" name="searchWord" value="" title="검색어 입력"> 
										<input type="image" src="<%=request.getContextPath()%>/images/admin/btn_search.gif" class="sbtn" alt="검색" />
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