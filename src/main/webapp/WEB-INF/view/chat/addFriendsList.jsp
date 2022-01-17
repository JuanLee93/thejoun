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
								<div class="avatar-icon">
									<img src="https://bootdey.com/img/Content/avatar/avatar1.png">
								</div>
							</div>
							<div class="col-sm-9 col-xs-9 sideBar-main">
								<div class="row">
									<div class="col-sm-8 col-xs-8 sideBar-name">
										<span class="name-meta">${vo.name } </span>
										<span class="messageNotice" >${vo.messagecount }</span>
									</div>
									<div class="col-sm-4 col-xs-4 pull-right sideBar-time">
										<span class="time-meta pull-right">${vo.chatregdate } </span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</c:if>
	</div>
	<button class="friendsButton" type="button" onclick="" >채팅목록</button>


</div>