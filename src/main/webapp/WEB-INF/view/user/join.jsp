<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	<script>
		$(function(){
			$("#idCheckBtn").click(function(){
				if($("#id").val().trim() == ''){
					alert("이메일을 입력해주세요.");
					$("#id").focus();
				}else{
					$.ajax({
						url : "idCheck.do",
						data : {
							email : $("#id").val()
						},
						async : false,
						success : function(res){
							if(res.trim() == '1'){
								alert("중복된 이메일 입니다.");
								$("#id").val("");
								$("#id").focus();
							}else{
								alert("사용 가능한 이메일입니다.");
							}
						}
					})
				}
			});
			
			selectDate("birthday");
		});
	</script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    
	    function goSave(){
	    	if($("#id").val().trim() == ""){
	    		alert("이메일을 입력해 주세요.");
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
    <div class="wrap">
    	<!--  헤더 인클루드할 공간 -->
		 <div class="sub">
            <div class="size">
                <h3 class="sub_title">회원가입</h3>
                <form name="frm" id="frm" action="insert.do" method="post">
                <table class="board_write">
                    <caption>회원가입</caption>
                    <colgroup>
                        <col width="20%" />
                        <col width="*" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>*아이디</th>
                            <td>
                                <input type="text" name="id" id="id" class="inNextBtn" style="float:left;">
                                <span class="email_check"><a href="javascript:;" id="idCheckBtn" class="btn bgGray" style="float:left; width:auto; clear:none;">중복확인</a></span>
                            </td>
                        </tr>
                        <tr>
                            <th>*비밀번호</th>
                            <td><input type="password" name="pwd" id="pwd" style="float:left;"> <span class="ptxt">비밀번호는 숫자, 영문 조합으로 8자 이상으로 입력해주세요.</span> </td>
                        </tr>
                        <tr>
                            <th>*비밀번호<span>확인</span></th>
                            <td><input type="password" name="pw_check" id="pw_check" style="float:left;"></td>
                        </tr>
                        <tr>
                            <th>*이름</th>
                            <td><input type="text" name="name" id="name" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*별명</th>
                            <td><input type="text" name="nickname" id="nickname" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*성별</th>
                            <td>
                            <select name="gender" id="gender">
	                            <option value="1">남성</option>
	                            <option value="2">여성</option>
                            </select> 
                            </td>
                        </tr>
                        <tr>
                            <th>*휴대폰</th>
                            <td><input type="text" name="tel" id="tel" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*이메일</th>
                            <td><input type="text" name="email" id="email" style="float:left;"> </td>
                        </tr>
                    </tbody>
                </table>
                        <input type="hidden" name="cmd" value="write.do"/>
                        <input type="hidden" name="checkEmail" id="checkEmail" value="0"/>
                </form>
                <!-- //write--->
                <div class="btnSet clear">
                    <div><a href="javascript:;" class="btn" onclick="goSave();">가입</a> <a href="javascript:;" class="btn" onclick="history.back();">취소</a></div>
                </div>
            </div>
        </div>
    	<!--  footer 인클루드할 공간 -->
    </div>
</body>
</html>