<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<style>
		.tttt {font-size:14px; text-decoration:none !important; height:24px; width:130px; white-space:nowrap; 
	display:inline-block; position:relative; cursor:pointer; padding:0px 10px; top:8px; left:10px;
	min-width:60px; border:2px solid #221f1f; color:#fff !important; margin:0 2px; text-align:center; 
	font-weight:bold; border-radius:5px; background-color:#221f1f;}
	
	</style>
</head>
	<div class="row heading">
		<div class="col-sm-2 col-md-1 col-xs-3 heading-avatar">
			<div class="heading-avatar-icon">
				<c:if test="${!empty otherUserno.filename_org }">
						<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${otherUserno.filename_org}
												&real=${otherUserno.filename_real}"target="_blank"'/>">
				</c:if>
				<c:if test="${empty otherUserno.filename_org }">
						<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
				</c:if>
			</div>
		</div>
		<div class="col-sm-8 col-xs-7 heading-name">
			<a class="heading-name-meta">${otherUserno.nickname } </a> <span
				class="heading-online">Online</span>
		</div>
		<button type="" class="tttt" onclick="chattingStop(${otherUserno.userno})" >이전내용 보기</button>
	</div>

	<div class="row message" id="conversation">
		<br>
		<c:forEach var="vo" items="${findChatting}" varStatus="status">
			<c:if test="${userInfo.userno != vo.from_id }"> 
				<div class="row message-body">
					<div class="col-sm-12 message-main-receiver">
						<div class="receiver">
							<div class="message-text">${vo.chatcontent }</div>
							<span class="message-time pull-right"> ${vo.chatregdate } </span>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${userInfo.userno == vo.from_id }">		
				<div class="row message-body">
					<div class="col-sm-12 message-main-sender">
						<div class="sender">
							<div class="message-text">${vo.chatcontent }</div>
							<span class="message-time pull-right"> ${vo.chatregdate } </span>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>


