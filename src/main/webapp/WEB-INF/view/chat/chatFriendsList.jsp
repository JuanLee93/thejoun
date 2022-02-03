<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<head>
	<style>
		.messageNotice{
			width : 20px;
			height : 20px;
			border-radius : 50%;
			background : #ff0000;
			font-size : 12px;
			color : white;
			float : right;
			text-align : center;
			line-height : 20px;
			
		}
		.friendsButton{
		 	padding : 10px;
			width : 350px;
			height : 50px;
			font-size : 25px;
			color : black;
			background-color : green;
			text-align : center;
			line-height : 5px;
		}
	</style>
</head>
<div class="side-one">
	<div class="row heading" style="width:370px;">
		<div class="col-sm-3 col-xs-3 heading-avatar">
			<c:if test="${!empty userInfo.filename_org }">
				<div class="heading-avatar-icon">
					<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${userInfo.filename_org}
											&real=${userInfo.filename_real}"target="_blank"'/>">
				</div>
			</c:if>
			<c:if test="${empty userInfo.filename_org }">
				<div class="heading-avatar-icon">
					<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
				</div>
			</c:if>
		</div>
		<div class="col-sm-8 col-xs-7 heading-name" style="width:170px;">${userInfo.nickname }</div>
		<div class="col-sm-2 col-xs-2 heading-compose  pull-right homeIcon">
			<a href="/thejoun/index.do">
				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-house" viewBox="0 0 20 20">
					<path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
					<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
				</svg>
			</a>
		</div>
	</div>


	<div class="row sideBar">

		<c:if test="${empty friendsList }">
			<div class="row sideBar-body">
				<div class="col-sm-9 col-xs-9 sideBar-main">
					<div class="row">
						<div class="col-sm-8 col-xs-8 sideBar-name">
							<span class="name-meta">등록된 친구가 없습니다. </span>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty friendsList }">
			<c:forEach var="vo" items="${friendsList }" varStatus="status">
				<form name="frm" id="frm${vo.userno }" method="post">
					<div onclick="findChattingList(${vo.userno })">
						<input type="hidden" name="to_id" value="${vo.userno }">
						<div class="row sideBar-body">
							<div class="col-sm-3 col-xs-3 sideBar-avatar">
								<c:if test="${empty vo.filename_org}">
									<div class="avatar-icon">
										<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
									</div>
								</c:if>
								<c:if test="${!empty vo.filename_org}">
									<div class="avatar-icon">
										<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${vo.filename_org}
										&real=${vo.filename_real}"target="_blank"'/>">
									</div>
								</c:if>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.nickname } </span>
										<c:if test="${vo.messagecount != 0}">
										<span class="messageNotice">${vo.messagecount }</span>
										</c:if>
									</div>
									<div class="col-sm-4 col-xs-4 pull-right sideBar-time">
											<span class="time-meta pull-right">${vo.ccc }</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</c:if>
	</div>
	<button class="friendsButton" type="button" onclick="getFriendsAddList()" >친구요청 목록</button>


</div>