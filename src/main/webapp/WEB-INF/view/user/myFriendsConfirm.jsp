<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script src="/thejoun/js/main.js"></script>
<script src="/thejoun/css/common.css"></script>
<link rel="stylesheet" href="/thejoun/css/mypage_index.css" />
<link rel="stylesheet" href="/thejoun/css/mypage_info.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<style>
	.bs-example > .indexPage{
	float : left;
	width : 75%;	
	}
	div.indexPage > ul > h3 {
	text-align: center;
	padding:10px 0px 15px 0px;
	font-size: 20px;
}
</style>
<script>
	function goDel(userno,nickname){
		if(confirm(nickname+" 님을 삭제하시겠습니까?")){
			$("#deleteFriendsFrm"+userno).submit();
		}
	}
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
		<div class="sub">
			<div class="size">
				<h3 class="sub_title1">마이 페이지</h3>
				<hr>
				<div class="bs-example" data-example-id="simple-nav-stacked">
					<div class="bs-example.left">
						<ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
							<li role="presentation" ><a href="/thejoun/user/mypage.do">나의 정보</a></li>
							<li role="presentation"><a href="/thejoun/user/myBoardConfirm.do">나의 글 확인</a></li>
							<li role="presentation" class="active"><a href="/thejoun/user/myFriendsConfirm.do">친구 정보 확인</a></li>
							<li role="presentation"><a href="/thejoun/user/bookmark.do">북마크</a></li>
							<li role="presentation"><a href="/thejoun/user/myInquiry.do">1:1 문의</a></li>
						</ul>
					</div>
					<div class="indexPage">
						<ul>
							<h3>&nbsp;&nbsp;&nbsp;친구목록</h3>
							<div class="bbs">
								<table class="list">
			                        <caption>자유게시판 목록</caption>
			                        <colgroup>
			                            <col width="200px" />
			                            <col width="100px" />
			                            <col width="150px" />
			                        </colgroup>
			                        <thead>
			                            <tr>
			                                <th>닉네임</th>
			                                <th>친구 등록일</th>
			                                <th>친구삭제</th>
			                            </tr>
			                        </thead>
			                        <tbody>
									<c:if test="${empty list }">
			                            <tr>
			                                <td class="first" colspan="6" style="text-align:center">등록된 친구가 없습니다.</td>
			                            </tr>
									</c:if>
			                        <c:if test="${!empty list }">
			                        <c:forEach var="vo" items="${list }" varStatus="status">
			                            <tr class="board_tr1" >
			                                <td class="txt_l">
			                                    ${vo.nickname } 
			                                </td>
			                                <td class="date">${vo.regdate }</td>
			                                <td class="writer">
			                                	<form method="post" id="deleteFriendsFrm${vo.userno }" action="deleteFriend.do" enctype="multipart/form-data">
			                                	<input type="hidden" name="friends_userno" value="${vo.userno }">
		                                  	  		<input type="button" class="friendsButton" onclick="goDel(${vo.userno}, '${vo.nickname }');" value="친구삭제">
			                                  	</form>
			                                </td>
			                            </tr>
			                        </c:forEach>
			                        </c:if>
			                        </tbody>
		                    	</table>
	                    	</div>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>