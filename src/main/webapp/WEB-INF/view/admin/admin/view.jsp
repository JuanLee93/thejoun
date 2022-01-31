<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
<script>
function del() {
	if (confirm("삭제하시겠습니까?")) {
		$.ajax({
			url : 'deleteAjax.do',
			data : {admin_no : ${data.admin_no}},
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
</head>
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
					<h2>관리자관리 - [관리자 상세정보]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="bread">
							<form name="frm" id="frm" action="" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 상세정보화면입니다.">
									<colgroup>
				                        <col width="20%" />
				                        <col width="*" />
									</colgroup>
									<tbody>
										<tr>
				                            <th scope="row"><label for="">관리자번호</label></th>
				                            <td colspan="10">${data.admin_no}</td>
				                        </tr>
										<tr>
				                            <th scope="row"><label for="">아이디</label></th>
				                            <td colspan="10">${data.id}</td>
				                        </tr>
				                        <tr>
				                            <th scope="row"><label for="">이름</label></th>
				                            <td colspan="10">${data.name }</td>
				                        </tr>
									</tbody>
								</table>
							</form>
							<div class="btn">
								<div class="btnLeft">
									<a class="btns" href="index.do"><strong>목록</strong></a>
								</div>
								<div class="btnRight">
									<a class="btns" style="cursor:pointer;" href="edit.do?admin_no=${data.admin_no }"><strong>수정</strong></a>
									<a class="btns" style="cursor:pointer;" href="javascript:del();"><strong>삭제</strong></a>
								</div>
							</div>
							<!--//btn-->
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