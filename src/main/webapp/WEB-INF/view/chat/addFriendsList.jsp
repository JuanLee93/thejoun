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
<div class="side-one">
	<div class="row heading">
		<div class="col-sm-3 col-xs-3 heading-avatar">
			<div class="heading-avatar-icon">
				<img src="https://bootdey.com/img/Content/avatar/avatar1.png">
			</div>
		</div>
		<div class="col-sm-1 col-xs-1  heading-dot  pull-right">
			<i class="fa fa-ellipsis-v fa-2x  pull-right" aria-hidden="true"></i>
		</div>
		<div class="col-sm-2 col-xs-2 heading-compose  pull-right">
			<i class="fa fa-comments fa-2x  pull-right" aria-hidden="true"></i>
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
									<img src="https://bootdey.com/img/Content/avatar/avatar1.png">
								</div>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.nickname } </span>
										<span class="name-meta" style="font-size:13px;">신청일 : ${vo.regdate } </span>
									</div>
									<div class="col-sm-4 col-xs-4 pull-right sideBar-time">
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
				<form name="frm" id="frm${vo.userno }" method="post">
					<input type="hidden" name="to_id" value="${vo.userno }">
						<div class="row sideBar-body">
							<div class="col-sm-3 col-xs-3 sideBar-avatar">
								<div class="avatar-icon">
									<img src="https://bootdey.com/img/Content/avatar/avatar1.png">
								</div>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.nickname } </span>
										<span class="name-meta" style="font-size:13px;">요청일 : ${vo.regdate } </span>
									</div>
									<div class="col-sm-4 col-xs-4 pull-right sideBar-time">
										
									</div>
								</div>
							</div>
						</div>
				</form>
			</c:forEach>
		</c:if>
	</div>
	<button class="friendsButton" type="button" onclick="friendsListToChatFriendsList()" >채팅목록</button>


</div>