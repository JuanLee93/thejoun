<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="util.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp" %>
<script>
function loginCheck(){
	if ( $("#id").val().length < 1 ) {
		alert("아이디를 입력해주세요.");
		$("#id").val().focus();
		return false;
	}
	if ( $("#password").val().length < 1 ) {
		alert("비밀번호를 입력해주세요.");
		$("#password").val().focus();
		return false;
	}
	var f = document.board;
	if (f.reg.checked) {
	   document.cookie = "cookie_userid=" + f.id.value + ";path=/;expires=Sat, 31 Dec 2050 23:59:59 GMT;";
	} else {
	   var now = new Date();	
	   document.cookie = "cookie_userid=null;path=/;expires="+now;
	}
	return true;
}

function userid_chk() {
	var f=document.board;
	var useridname = CookieVal("cookie_userid");
	
	if (useridname=="null"){	
		f.id.focus();
		f.id.value="";
	} else {
		f.password.focus();
		f.id.value=useridname;
		f.reg.checked=true;
	}
}

function CookieVal(cookieName) {
	thisCookie = document.cookie.split("; ");
	for (var i = 0; i < thisCookie.length;i++) {
		if (cookieName == thisCookie[i].split("=")[0]) {
			return thisCookie[i].split("=")[1];
		}
	}
	return "null" ;
}
//-->

</script>
</head>
<body onload="userid_chk();">
<div id="login">
	<div class="title">
		<h1>THEJOUN(더조은) <span>관리자모드</span></h1>
		<p>관리자 로그인 후 이용가능합니다.</p>
	</div>
	<div class="login"> 
	<form name="board" id="board" method="post" action="" onsubmit="return loginCheck();">
		<fieldset>
			<legend>관리자모드 로그인</legend>
			<div class="bgBox">
				<div class="infoBox">
					<dl>
						<dt>
							<label for="id"><strong>아이디</strong></label>
						</dt>
						<dd>
							<input type="text" id="id" name="id" value="" title="아이디를 입력해주세요." style="ime-mode:inactive"/>
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="password"><strong>비밀번호</strong></label>
						</dt>
						<dd>
							<input type="password" id="password" name="password" value="" title="비밀번호를 입력해주세요." />
						</dd>
					</dl>
				</div>
				<!-- //infoBox -->
				<input type="image" src="<%=request.getContextPath()%>/images/admin/member_login_btn.gif" alt="로그인" class="loginBtn" title="" />
			</div>
			<!-- //bgBox -->
			<div class="joinList">
				<input type="checkbox" name="reg" id="reg"/> <label for="reg">아이디 저장</label>
			</div>
			<!-- //joinList -->
			<input type="hidden" name="url" id="url" value="<%//=url%>"/>
			<input type="hidden" name="param" id="param" value="<%//=param%>"/>
			<input type="hidden" name="ip" id="ip" value="<%=request.getRemoteAddr()%>"/>
		</fieldset>
	</form>
	</div>
	<div class="footer">
		Copyrights (c) 2021 <a href="#" target="_blank">THEJOUN(더조은)</a>. All Rights Reserved.  
	</div>
</div>
</body>
</html>