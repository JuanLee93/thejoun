<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>

.friendsButton {
	font-size: 14px;
	text-decoration: none !important;
	height: 24px;
	white-space: nowrap;
	display: inline-block;
	vertical-align: baseline;
	position: relative;
	cursor: pointer;
	padding: 0px 10px;
	min-width: 60px;
	border: 2px solid #221f1f;
	color: #fff !important;
	margin: 0 2px;
	text-align: center;
	font-weight: bold;
	border-radius: 5px;
	background-color: #221f1f;
}
.fc > ul > li {
	float:left;
	width:140px;
	position:relative;
}

.noticeMain{
	position : absolute;
	top:30px;
	width: 300px;
	height: 400px;
	z-index: 1000;
	background-color : #ffffff;
	display : none;
	border : 1px solid black;
	align : right;
	overflow: auto;
}
.noticeMain > li{
	border : 1px solid black;
	text-align : left;
}
.noticeMain > li:hover{
	background-color : #eeeeee;
}

</style>
<script>

</script>
<meta charset="UTF-8">
</head>
<body>
	<button class="friendsButton" onclick="view();">알림</button>
	<ul class="noticeMain">
		<c:forEach var="vo" items="${userVoList}" varStatus="status">
			<c:if test="${vo.board_or_comment == 0 }">
				<li onclick="goBoard(${vo.boardno},${vo.tablename });">친구 ${vo.nickname} 님이 게시글을 올렸어요 <br> 게시글내용 : ${vo.content} <br> 알림일 : ${vo.regdate }</li>
			</c:if>
			<c:if test="${vo.board_or_comment == 1 }">
				<li onclick="goBoard(${vo.boardno},${vo.tablename });">${vo.nickname} 님이 내 글에 댓글을 달았어요 <br> 댓글내용 : ${vo.content} <br> 알림일 : ${vo.regdate }</li>
			</c:if>
			<c:if test="${vo.board_or_comment == 2 }">
				<li onclick="goBoard(${vo.boardno},${vo.tablename });">${vo.nickname} 님이 내 댓글에 대댓글을 달았어요 <br> 대댓글내용 : ${vo.content} <br> 알림일 : ${vo.regdate }</li>
			</c:if>
		</c:forEach>
	</ul>
</body>
</html>









