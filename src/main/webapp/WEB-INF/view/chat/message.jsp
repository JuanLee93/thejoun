<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row heading">
		<div class="col-sm-2 col-md-1 col-xs-3 heading-avatar">
			<div class="heading-avatar-icon">
				<img src="https://bootdey.com/img/Content/avatar/avatar6.png">
			</div>
		</div>
		<div class="col-sm-8 col-xs-7 heading-name">
			<a class="heading-name-meta">${otherUserno.name } </a> <span
				class="heading-online">Online</span>
		</div>
		<div class="col-sm-1 col-xs-1  heading-dot pull-right">
			<i class="fa fa-ellipsis-v fa-2x  pull-right" aria-hidden="true"></i>
		</div>
	</div>

	<div class="row message" id="conversation">
		<div class="row message-previous">
			<div class="col-sm-12 previous">
				<a onclick="previous(this)" id="ankitjain28" name="20"> Show
					Previous Message! </a>
			</div>
		</div>

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


