<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>THEJOUN(더조은)</title>
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script src="/thejoun/css/common.css"></script>
<link rel="stylesheet" href="/thejoun/css/mypage_index.css" />
<link rel="stylesheet" href="/thejoun/css/mypage_info.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function zipcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                addr += extraAddr; // 하나의 변수에 합치기
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("sample6_extraAddress").value = extraAddr;
            
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode; // 우편번호
            document.getElementById("addr1").value = addr; // 주소
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}

function setThumbnail(event) { 
	var reader = new FileReader(); 
	
	reader.onload = function(event){ 
	var img = document.createElement("img"); 
	img.setAttribute("src", event.target.result); 
	document.querySelector("div#image_container").appendChild(img);
	}; 
	
	reader.readAsDataURL(event.target.files[0]); 
	}
</script>
<style>
div.size > ul {
	position: relative;
	padding: 10px;
	float: right;
	width: 75%;
	text-align: center;
}
div.size > ul > li {
	position: relative;
	padding: 10px 10px 10px 10px;
	vertical-align: middle;
	height : 20%;
}
input[type="checkbox"] {
    width: 1rem;
    height: 1rem;
    border-radius: 50%;
    border: 1px solid #999;
    appearance: none;
    cursor: pointer;
    transition: background 0.2s;
  }

input[type="checkbox"]:checked {
    background: #32e732;
    border: none;
  }
div.size > ul > li > button {
    padding: 5px ;
    margin: 5px;
    background: #221f1f !important;
    border: none;
    color: #fff !important;
    border-radius: 5px;
}
input#image {
    vertical-align: middle;
    padding : 10px;
    height: auto;
    width : auto;
    margin-right : auto;
}

</style>
</head>
<div class="wrap">
	<%@ include file="/WEB-INF/view/include/header.jsp"%>
	<div class="sub">
		<form name="infoUpdate" id="infoUpdate" action="infoUpdate.do" method="post" enctype="multipart/form-data">
		<div class="size">
			<h3 class="sub_title1">마이 페이지</h3>
			<hr>
			<div class="bs-example" data-example-id="simple-nav-stacked">
				<div class="bs-example.left">
					<ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
						<li role="presentation" class="active"><a href="/thejoun/user/mypage.do">나의 정보</a></li>
						<li role="presentation"><a href="/thejoun/user/myBoardConfirm.do">나의 글 확인</a></li>
						<li role="presentation"><a href="/thejoun/user/myFriendsConfirm.do">친구 정보 확인</a></li>
						<li role="presentation"><a href="/thejoun/user/bookmark.do">북마크</a></li>
						<li role="presentation"><a href="/thejoun/user/myInquiry.do">1:1 문의</a></li>
					</ul>
				</div>
			</div>
			<div id="myPageIndex"></div>
			<div class="size">
				<h3 class="sub_title2">회원정보 수정</h3>
				<ul>
					<li><input type="hidden" name="id" value="${userInfo.id}">아이디 : ${userInfo.id}</li>
					<li>닉네임 : <input type="text" id="nickname" name="nickname" value="${userInfo.nickname}">
					</li>
					<li>전화번호 : <input type="text" id="tel" name="tel" value="${userInfo.tel}">
					</li>
					<li><input type="hidden" name="regdate" value="${userInfo.regdate}">회원정보 수정일 : ${userInfo.regdate}</li>
					<hr>
				</ul>
				<ul>
					<li>이름 : ${userInfo.name}</li>
					<li>이메일 : ${userInfo.email}</li>
					
					<li>
						우편번호 : <input type="text" name="zipcode" id="zipcode" value="${userInfo.zipcode}" >
                                <a href="javascript:zipcode();" class="btn bgGray" >수정</a></span>
					</li>
					<li>주소1 : <input type="text" name="addr1" id="addr1" value="${userInfo.addr1}" ></li>
					<li>주소2 : <input type="text" name="addr2" id="addr2" value="${userInfo.addr2}"></li><hr>
				</ul>
				<ul>
					<li>프로필 사진 미리보기 ( 2MB이하의 250PX * 250PX의 사진을 추가해주세요 ) : </li>
					<div id="image_container" ></div>
					<input type="file" id="image" name="file" accept="image/*" onchange="setThumbnail(event);"/> 
    				<div>
    					<input type="submit" value="확인" >
    					<input type="button" value="취소" onclick=location.href="/thejoun/user/mypage.do";>
    				</div>
				</ul>
			</div>
		</div>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</html>