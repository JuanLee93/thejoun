<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row reply">
	<form name="inputForm" id="inputForm" method="post">
	<input type="hidden" name="to_id" value="${otherUserno.userno }">
	<input type="hidden" name="from_id" value="${userInfo.userno }">
		<div class="col-sm-9 col-xs-9 reply-main">
			<input type="text" class="form-control" name="chatcontent" id="chatconttent">
		</div>
		<div class="col-sm-1 col-xs-1 reply-send">
			<i class="fa fa-send fa-2x" aria-hidden="true" onclick="sendMessage()"></i>
		</div>
	</form>
</div>