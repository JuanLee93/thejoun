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
		$(".title").click(function() {
			location.href = 'view.do?board_no=' + $(this).data("board_no");
		});
		
		$('#allChk').click(function(){  
			var checked = $('#allChk').is(':checked');
		
			if(checked) {
				$("input[name='no']").each(function() {
					$(this).prop('checked',true);
				});
			} else {
				$("input[name='no']").each(function() {
					$(this).prop('checked',false);
				});
			}
		});
	});
	
	function goDelete() {//체크박스 삭제처리
		if (confirm("삭제하시겠습니까?")) {
			$("#frm").submit();
		}
	}
	
	function goNoticeUpdate() {//체크박스 공지글로 적용
		if (confirm("이 글을 공지글로 적용하시겠습니까?")) {
			$.ajax({
				url : 'noticeUpdateAjax.do',
				type : 'POST',
				data : $("#frm").serialize(),
				success : function(res) {
					alert('선택하신 글을 공지글로 적용하였습니다.');
					location.reload();
				}
			});
		}
	}
	
	function goNoticeNotUpdate() {//체크박스 공지글로 적용
		if (confirm("이 글을 일반글로 적용하시겠습니까?")) {
			$.ajax({
				url : 'noticeNotUpdateAjax.do',
				type : 'POST',
				data : $("#frm").serialize(),
				success : function(res) {
					alert('선택하신 글을 일반글로 적용하였습니다.');
					location.reload();
				}
			});
		}
	}
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
						<h2>게시물관리 - 고민 게시판[목록]</h2>
					</div>
					<!-- //con_tit -->
					<div class="con">
						<!-- 내용 : s -->
						<div id="bbs">
							<div id="blist">
								<p>
									<span><strong>총 ${totCount }개</strong> |
										${concernBoardVo.page }/${totPage }페이지</span>
								</p>
								<form name="frm" id="frm" action="boardDeleteAjax.do" method="post">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										summary="관리자 관리목록입니다.">
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
												<th scope="col" class="first"><input type="checkbox" name="allChk" id="allChk"/></th>
												<th scope="col">번호</th>
												<th scope="col">제목</th>
												<th scope="col">작성자</th>
												<th scope="col">작성일</th>
												<th scope="col" >조회수</th>
												<th scope="col" class="last" >상태</th>
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
														<td class="first"><input type="checkbox" name="no" value="${vo.concern_board_no }"/></td>
														<c:if test="${vo.noticeYN == 'Y' }">
		                           						 	<td>[공지]</td>
		                            					</c:if>
		                            					<c:if test="${vo.noticeYN =='N' }">
														<td>${(totCount-status.index) - ((concernboardVo.page-1)*10) }</td>
														</c:if>
						                                <td class="title" data-board_no="${vo.concern_board_no }" style="cursor: pointer;">
						                                    ${vo.title }
						                                </td>
						                                <td class="writer">
						                                    <c:if test="${!empty vo.nickname}">
				                                  	  			${vo.nickname }
				                                   			</c:if>
				                                   			<c:if test="${empty vo.nickname}">
				                                  	  			${vo.admin_name }
				                                   			</c:if>
						                                </td>
														<td class="date">${vo.regdate }</td>
														<td>${vo.readcount }</td>
														<td class="Last">
															<c:if test="${vo.state == 1 }">
															신고
															</c:if>
															<c:if test="${vo.state == 0 }">
															정상
															</c:if>
														</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</form>
								<div class="btn">
									<div class="btnLeft">
										<a class="btns" href="#" onclick="goDelete()"><strong>삭제</strong> </a>
										<a class="btns" href="#" onclick="javascript:goNoticeUpdate();"><strong>공지글로 적용</strong> </a>
										<a class="btns" href="#" onclick="javascript:goNoticeNotUpdate();"><strong>공지글 취소</strong></a>
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
												<option value="title">제목</option>
												<option value="contents">내용</option>
												<option value="nickname">닉네임</option>
												<option value="name">관리자이름</option>
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
</html>
