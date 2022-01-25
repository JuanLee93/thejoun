<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function(){
		$("#idCheckBtn").click(function(){
			if($("#id").val().trim() == ''){
				alert("아이디를 입력해주세요.");
				$("#id").focus();
			}else{
				$.ajax({
					url : "idCheck.do",
					data : {
						id : $("#id").val()
					},
					async : false,
					success : function(res){
						if(res.trim() == '1'){
							alert("중복된 아이디 입니다.");
							$("#id").val("");
							$("#id").focus();
						}else{
							alert("사용 가능한 아이디입니다.");
						}
					}
				})
			}
		});
	
	});

    function goSave(){
    	if($("#id").val().trim() == ""){
    		alert("아이디를 입력해 주세요.");
    		$("#id").val("");
    		$("#id").focus();
    		return;
    	}
    	var con = true;
    	$.ajax({
			url : "idCheck.do",
			data : {
				id : $("#id").val()
			},
			async : false,
			success : function(res){
				if(res.trim() == '1'){
					alert("중복된 아이디 입니다.");
					$("#id").val("");
					$("#id").focus();
					con = false;
				}
			}
		});
    	if(con == false){
    		return;
    	}
    	if($("#pwd").val().trim() == ""){
    		alert("비밀번호를 입력해주세요.");
    		$("#pwd").focus();
    		return;
    	}
    	
    	var reg = "/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/";
    	if( !/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test($("#pwd").val()) ) {
    	    alert("비밀번호는 문자+숫자 조합으로 8자이상 입력해 주세요");
    	    $("#pwd").focus();
    	    return false;
    	}
    	
    	if($("#pwd").val() != $("#pw_check").val()){
    		alert("비밀번호가 일치하지 않습니다.");
    		return;
    	}
    	if($("#name").val().trim() == ""){
    		alert("이름을 입력해 주세요.");
    		$("#name").focus();
    		return;
    	}
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
					<h2>관리자관리 - [관리자 등록]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="blist">
							<form name="frm" id="frm" action="insert.do" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 등록화면입니다.">
									<colgroup>
				                        <col width="20%" />
				                        <col width="*" />
									</colgroup>
									<tbody>
										<tr>
				                            <th>*아이디</th>
				                            <td>
				                                <input type="text" name="id" id="id" class="inNextBtn" style="float:left;">
				                                <span class="id_check"><a href="javascript:;" id="idCheckBtn" class="bgGray" style="float:left; width:auto; clear:none;">중복확인</a></span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <th>*비밀번호</th>
				                            <td class="lef"><input type="password" name="pwd" id="pwd" style="float:left;"> <span class="ptxt">비밀번호는 문자+숫자 조합으로 8자이상 입력해 주세요</span> </td>
				                        </tr>
				                        <tr>
				                            <th>*비밀번호<span>확인</span></th>
				                            <td><input type="password" name="pw_check" id="pw_check" style="float:left;"></td>
				                        </tr>
				                        <tr>
				                            <th>*이름</th>
				                            <td><input type="text" name="name" id="name" style="float:left;"> </td>
				                        </tr>
									</tbody>
								</table>
									<input type="hidden" name="cmd" value="write.do"/>
							</form>
							<div class="btn">
								<div class="btnRight">
									<a href="javascript:;" class="btn1" onclick="goSave();">등록</a> <a href="javascript:;" class="btn1" onclick="history.back();">취소</a>
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