<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
</head>
<script>
function del() {
	if (confirm("삭제하시겠습니까?")) {
		$.ajax({
			url : 'deleteAjax.do',
			data : {board_no : ${data.board_no}},
			success : function(res) {
				if (res.trim() == '1') {
					alert('정상적으로 삭제되었습니다.');
					location.href='index.do';
				} else {
					alert('삭제 오류');
				}
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
						<h2>게시물 관리- 자유 게시판[상세 페이지]</h2>
					</div>
					<!-- //con_tit -->
					<div class="con">
						<!-- 내용 : s -->
						<div id="bbs">
							<div id="bread">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									summary="관리자 자유게시판 상세페이지입니다.">
									<colgroup>
										<col width="10%" />
										<col width="15%" />
										<col width="10%" />
										<col width="15%" />
										<col width="25%" />
										<col width="25%" />
									</colgroup>
									<tbody>
										<tr>
											<th scope="row"><label for="">제목</label></th>
											<td colspan="10">${data.title }</td>
										</tr>
										<tr>
											<th scope="row"><label for="">작성자</label></th>
											<td colspan="10">
											<c:if test="${!empty data.nickname}">
                                  	  			${data.nickname }
                                   			</c:if>
                                   			<c:if test="${empty data.nickname}">
                                  	  			관리자
                                   			</c:if>
											</td>
										</tr>
										<tr>
											<th scope="row"><label for="">작성일</label></th>
											<td colspan="10">${data.regdate }</td>
										</tr>
										<tr>
											<th scope="row"><label for="">내용</label></th>
											<td colspan="10">${data.contents }</td>
										</tr>
										<tr>
											<th scope="row"><label for="">첨부파일</label></th>
											<td colspan="10">
												<div class="weidtFile">
													<dd>
														<a href="/thejoun/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}"
															target="_blank">${data.filename_org }</a>
													</dd>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="btn">
									<div class="btnLeft">
										<a class="btns" href="index.do"><strong>목록</strong></a>
									</div>
									<div class="btnRight">
										<a class="btns" style="cursor: pointer;" href="javascript:del();"><strong>삭제</strong></a>
									</div>
								</div>
								<!--//btn-->
							</div>
							<!-- //bread -->
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