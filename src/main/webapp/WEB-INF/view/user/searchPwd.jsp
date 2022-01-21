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
    <link rel="stylesheet" href="/thejoun/css/reset.css"/>
    <link rel="stylesheet" href="/thejoun/css/common.css"/>
    <link rel="stylesheet" href="/thejoun/css/contents.css"/>
    <link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/contents.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	<script src="/thejoun/js/common.js"></script>
	<script>
		function searchPwd(){
			if($("#email").val() == ""){
				alert("이메일을 입력해주세요");
				$("#email").focus();
				return false;
			}
			if($("#name").val() == ""){
				alert("이름 입력해주세요");
				$("#name").focus();
				return false;
			}
			$.ajax({
				url : "searchPwd.do",
				method : "post",
				data:{
					name : $("#name").val(),
					email : $("#email").val()
				},
				success : function(res){
					if(res.trim() == "ok"){
						alert("임시 비밀번호를 이메일로 발송함");
						location.href ="login.do";
					}else{
						alert("이메일/이름이 올바르지 않음");
					}
				}
			});
			return false;
		}
	</script>
	
</head>
<body>
    <div class="wrap">
    	<%@ include file="/WEB-INF/view/include/header.jsp" %>
		 <form action="login.do" method="post" id="board" name="board" onsubmit="return searchPwd();"><!-- header에서 id="board"이미 사용중이라서 board2로 함 -->
            <div class="sub">
                <div class="size">
                    <h3 class="sub_title">비밀번호 찾기</h3>
                    
                    <div class="member">
                        <div class="box">
                            <fieldset class="login_form">
                                <ul>
                                    <li><input type="text" id="email" name="email" placeholder="이메일"></li>
                                    <li><input type="text" id="name" name="name" placeholder="이름"></li>
                                    <li id="emailArea"></li>
                                </ul>
                                <div class="login_btn"><input type="submit" value="비밀번호 찾기" alt="비밀번호 찾기" /></div>
                            </fieldset>
                            <div class="btnSet clear" style="text-align:center">
                                <div>
                                    <a href="searchId.do" class="btn">아이디찾기</a> 
                                    <a href="searchPwd.do" class="btn">비밀번호 찾기</a>
                                </div>
                            </div>
                        </div>
                    </div>
        
                </div>
            </div>
        </form>
    	<%@ include file="/WEB-INF/view/include/footer.jsp" %>
    </div>
</body>
</html>