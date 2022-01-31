<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
<script>
function goSave() {
	var data = $("#frm").serialize();
	$("#frm").submit();
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
					<h2>관리자관리 - [관리자 정보 수정페이지]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="bread">
							<form name="frm" id="frm" action="update.do" method="post">
							<input type="hidden" name="admin_no" value="${data.admin_no }">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 정보 수정화면입니다.">
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
				                            <th>이름</th>
				                            <td><input type="text" name="name" id="name" style="float:left;" value="${data.name }"></td>
				                        </tr>
									</tbody>
								</table>
								<input type="hidden" name="cmd" value="write" />
							</form>
							<div class="btn">
								<div class="btnLeft">
									<a class="btns" style="cursor:pointer;" href="index.do"><strong>목록</strong></a>
								</div>
								<div class="btnRight">
									<a class="btns" style="cursor:pointer;" href="javascript:goSave();" ><strong>저장</strong></a>
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