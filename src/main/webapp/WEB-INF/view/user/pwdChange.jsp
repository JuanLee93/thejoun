<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>THEJOUN(더조은)</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script src="/thejoun/css/common.css"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script>

function pwdCheck(){
	
	if ($("#pwd").val() == $("#newPwd").val()){
		alert("동일한 비밀번호는 사용 할 수 없습니다");
		return false;
	}
	
	if ($("#newPwd").val() !== $("#newPwdConfirm").val()){
			alert("새 비밀번호를 확인해주세요");
			$("#newPwd").val("").focus();
			$("#newPwdConfirm").val("");
			return false;
		}
		
	if ($.trim($("#newPwd").val()) !== $("#newPwd").val()){
			alert("공백은 입력이 불가능합니다.");
			return false;
		}

	if ($("#newPwd").val().length < 8) {
		alert("비밀번호는 8자 이상으로 설정해야 합니다.");
		$("#newPwd").val("").focus();
		return false;
	}
	
	var reg = "/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/";
	if( !/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test($("#pwd").val()) ) {
	    alert("비밀번호는 문자+숫자 조합으로 8자이상 입력해 주세요");
	    $("#pwd").focus();
	    return false;
	}

	$("#pwdChange").submit();
}
</script>
<style>
#pwdChange > div > ul {
    position: relative;
    padding: 10px 15px 10px 15px;
    float: right;
    width: 75%;
    height: 350px;
    text-align:center;
}
div > input[type=button] {
	border: 2px solid;
}

div > ul > div {
	padding: 10px;	
}


</style>
</head>
	<form action="pwdChange.do" method="post" id="pwdChange" name="pwdChange" >
	<div class="size">
		<ul>	
			<h3 class="sub_title2" >비밀번호 변경</h3>
			<i class="bi bi-file-lock2-fill"></i>
			<li>
				현재 비밀번호 : &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="password" id="pwd" name="pwd" placeholder="현재 비밀번호">
			</li>
			<li>
				새 비밀번호 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="password" id="newPwd" name="newPwd" placeholder="새 비밀번호">
			</li>
			<li>
				새 비밀번호 확인 : 
				<input type="password" id="newPwdConfirm" name="newPwdConfirm" placeholder="새 비밀번호 확인">
			</li>
			<div class="login_btn">
				<input type="button" value="비밀번호 수정" alt="비밀번호 수정" onclick="pwdCheck()">
				<input type="button" value="이전 페이지로" onclick="myinfo()">
			</div>
		</ul>
	</div>
	</form>
</html>