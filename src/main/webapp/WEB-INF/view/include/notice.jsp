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
	cursor : pointer;
}
.noticeMain > li{
	border : 1px solid black;
	text-align : left;
}
.noticeMain > li:hover{
	background-color : #eeeeee;
	 cursor : pointer;
}
.nav-counter {
 position:absolute;
 top: -1px;
 right: 1px;
 min-width: 8px;
 height: 20px;
 line-height: 20px;
 margin-top: -11px;
 padding: 0 6px;
 font-weight: normal;
 font-size: small;
 color: white;
 text-align: center;
 text-shadow: 0 1px rgba(0, 0, 0, 0.2);
 background: #e23442;
 border: 1px solid #911f28;
 border-radius: 11px;
 background-image: -webkit-linear-gradient(top, #e8616c, #dd202f);
 background-image: -moz-linear-gradient(top, #e8616c, #dd202f);
 background-image: -o-linear-gradient(top, #e8616c, #dd202f);
 background-image: linear-gradient(to bottom, #e8616c, #dd202f);
 -webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
 box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
}
.noticeSub > svg{
		width : 28px;
		height : 28px;
	}

</style>
<script>

</script>
<meta charset="UTF-8">
</head>
<body>

	<a class="noticeSub" onclick="view();"> 
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bell" viewBox="0 0 16 16">
	  	<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
		</svg>
		<span class="nav-counter">${announceCount }</span>
	</a>
	<ul class="noticeMain">
		<li onclick="deleteAnnounce();" style="text-align : right;">모두 지우기</li>
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









