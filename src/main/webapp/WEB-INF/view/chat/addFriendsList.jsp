<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<head>
	<style>
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
<div class="side-one" >
	<div class="row heading" style="width:370px;">
		<div class="col-sm-3 col-xs-3 heading-avatar">
			<div class="heading-avatar-icon">
				<img src="https://bootdey.com/img/Content/avatar/avatar1.png">
			</div>
		</div>
		<div class="col-sm-8 col-xs-7 heading-name" style="width:170px;">${userInfo.nickname }</div>
		<div class="col-sm-2 col-xs-2 heading-compose  pull-right">
			<a href="/thejoun/index.do">
				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-house" viewBox="0 0 20 20">
					<path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
					<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
				</svg>
			</a>
		</div>
	</div>


	<div class="row sideBar" style="height : 43%;">
		<!--  내가 받은 친구신청 리스트 -->
		<c:if test="${empty getFriendsList }">
			<div class="row sideBar-body">
				<div class="col-sm-9 col-xs-9 sideBar-main">
					<div class="row">
						<div class="col-sm-8 col-xs-8 sideBar-name">
							<span class="name-meta">받은 친구신청목록</span>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty getFriendsList }">
			<span class="name-meta">받은 친구신청 목록 </span>
			<c:forEach var="vo" items="${getFriendsList }" varStatus="status">
					<input type="hidden" name="to_id" value="${vo.userno }">
						<div class="row sideBar-body">
							<div class="col-sm-3 col-xs-3 sideBar-avatar">
								<div class="avatar-icon">
									<c:if test="${!empty vo.filename_org }">
											<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${vo.filename_org}
																	&real=${vo.filename_real}"target="_blank"'/>">
									</c:if>
									<c:if test="${empty vo.filename_org }">
											<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
									</c:if>
								</div>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.nickname } </span>
										<span class="name-meta" style="font-size:13px;">신청일 : ${vo.regdate } </span>
									</div>
									<div class="col-sm-4 col-xs-4"style="float:right;">
										<form name="toFriendUser" id="toFriendUser${vo.userno }" method="post">
											<input type="hidden" name="from_userno" value="${vo.userno }">
											<button type="button" class="acceptButtonAgree" onclick="addFriends(${vo.userno})">수락</button>
										</form>
										<form name="deleteFriendUser" id="deleteFriendUser${vo.userno }" method="post">
											<input type="hidden" name="from_userno" value="${vo.userno }">
											<button type="button" class="acceptButtonDisagree" onclick="deleteFriends(${vo.userno})">거절</button>
										</form>
									</div>
								</div>
							</div>
						</div>
			</c:forEach>
		</c:if>
		</div>
		<div class="row sideBar" style="height : 43%;">
		<!---------------내가 신청한 친구 리스트------------->
		<c:if test="${empty addFriendsList }">
			<div class="row sideBar-body">
				<div class="col-sm-9 col-xs-9 sideBar-main">
					<div class="row">
						<div class="col-sm-8 col-xs-8 sideBar-name">
							<span class="name-meta">보낸 친구신청 목록</span>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty addFriendsList }">
		<span class="name-meta">보낸 친구신청 목록 </span>
			<c:forEach var="vo" items="${addFriendsList }" varStatus="status">
						<div class="row sideBar-body">
							<div class="col-sm-3 col-xs-3 sideBar-avatar">
								<div class="avatar-icon">
									<c:if test="${!empty vo.filename_org }">
											<img class="profilePhotoImg" src="<c:url value='/common/download.jsp?path=/upload/&org=${vo.filename_org}
																	&real=${vo.filename_real}"target="_blank"'/>">
									</c:if>
									<c:if test="${empty vo.filename_org }">
											<img class="profilePhotoImg" src="<c:url value='/img/none-user-img.png'/>">
									</c:if>
								</div>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.nickname } </span>
										<span class="name-meta" style="font-size:13px;">요청일 : ${vo.regdate } </span>
									</div>
									<div class="col-sm-4 col-xs-4 pull-right sideBar-time">
										<form id="requestCancel${vo.userno }" method="post">
											<input type="hidden" name="to_userno" value="${vo.userno }">
											<button type="button" class="" style="width:70px;font-size:12px;"onclick="requestCancel(${vo.userno});">요청 취소</button>
										</form>
									</div>
								</div>
							</div>
						</div>
			</c:forEach>
		</c:if>
	</div>
	<button class="friendsButton" type="button" onclick="friendsListToChatFriendsList()" >채팅목록</button>


</div>