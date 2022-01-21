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
		function searchEmail(){
			if($("#name").val() == ""){
				alert("이름을 입력해주세요");
				$("#name").focus();
				return false;
			}
			if($("#tel").val() == ""){
				alert("전화번호 입력해주세요");
				$("#tel").focus();
				return false;
			}
			$.ajax({
				url : "searchId.do",
				method : "post",
				data:{
					name : $("#name").val(),
					tel : $("#tel").val()
				},
				success : function(res){
					if(res.trim() == ""){
						$("#idArea").text("아이디가 존재하지 않습니다");
					}else{
						$("#idArea").text("아이디는" + res.trim() + "입니다");
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
		 <form action="login.do" method="post" id="board" name="board" onsubmit="return searchEmail();"><!-- header에서 id="board"이미 사용중이라서 board2로 함 -->
            <div class="sub">
                <div class="size">
                    <h3 class="sub_title">아이디 찾기</h3>
                    
                    <div class="member">
                        <div class="box">
                            <fieldset class="login_form">
                                <ul>
                                    <li><input type="text" id="name" name="name" placeholder="이름"></li>
                                    <li><input type="text" id="tel" name="tel" placeholder="전화번호"></li>
                                    <li id="idArea"></li>
                                </ul>
                                <div class="login_btn"><input type="submit" value="아이디찾기" alt="아이디찾기" /></div>
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