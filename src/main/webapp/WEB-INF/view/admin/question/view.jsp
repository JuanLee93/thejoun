<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp" %>
<script>
$(function() {
	$("#btn_update, #btn_save").click(function() {
		var reply = $("#txt_reply").val();
		
		if (confirm("답변을 등록하시겠습니까?")) {
			$.ajax({
				url : 'replyUpdate.do',
				data : {qna_no : ${data.qna_no}, reply: reply},
				success : function(res) {
					if (res.trim() == '1') {
						alert('정상적으로 등록되었습니다.');
						location.href='index.do';
					} else {
						alert('등록 오류');
					}
				}
			});
		}
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
					<h2>공지사항 - [읽기]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="bread">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 [문의사항]관리입니다.">
								<colgroup>
									<col width="10%" />
									<col width="10%" />
									<col width="30%" />
									<col width="10%" />
									<col width="10%" />
									<col width="30%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="">제목</label></th>
										<td colspan="10">
											${data.title }
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">작성자</label></th>
										<td colspan="10">
											${data.nickname }
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">내용</label></th>
										<td colspan="10">
											${data.content }
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">첨부파일</label></th>
										<td colspan="10">
			                                <a href="/thejoun/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}" 
	                            			target="_blank">${data.filename_org }</a>
			                            </td>
									</tr>
									<tr>
										<th scope="row"><label for="">답변일</label></th>
										<td colspan="10">
			                                ${data.updatedate}
			                             </td>
									</tr>
									<tr>
										<th scope="row"><label for="">답변</label></th>
										<td colspan="10">
			                                <textarea id="txt_reply">${data.reply}</textarea>
			                             </td>
									</tr>
								</tbody>
							</table>
							<div class="btn">
								<div class="btnLeft">
									<a class="btns" href="index.do"><strong>목록</strong></a>
								</div>
								<div class="btnRight">
									<a class="btns" style="cursor:pointer;" href="javascript:;" id="btn_update"><strong>수정</strong></a>
									<a class="btns" style="cursor:pointer;" href="javascript:;"id="btn_save"><strong>답변</strong></a>
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