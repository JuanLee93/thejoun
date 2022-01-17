<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
<!--  All snippets are MIT license http://bootdey.com/license -->
<title>Whatsapp web chat template - Bootdey.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/thejoun/css/bootstrap-chat.css">
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	var chat;
	$(function() {
		getFriendsList();
		setInterval(getFriendsList,1000);
	})
	
	function getFriendsList() {
		$(function(){
			$.ajax({
				url : "/thejoun/chat/chatFriendsList.do",
				type : "post",
				success : function(res){
					$("#chatFriendsListArea").html(res);
					console.log("1")
				}
			});
		});
	}
	
	function getAddFriendsList(){
		$.ajax({
			url:"/thejoun/chat/"
		});
	}
	
	function findChattingList(friendsUserno){
		$("#to_id").val(friendsUserno);
			findChatList(friendsUserno);
			function aaa(){
				findChatList(friendsUserno);
			}
			clearInterval(chat);
			chat = setInterval(aaa,1000);
	}
	
	function findChatList(friendsUserno){
		$(function(){
			$.ajax({
				url : "/thejoun/chat/findChattingTab.do",
				type : "post",
				data : $("#frm"+friendsUserno).serialize(),
				success : function(res){
					$("#messageArea").html(res);
					$('#conversation').scrollTop($('#conversation')[0].scrollHeight);
					console.log("2");
				}
			});
		});
	}
	
	function sendMessage(){
		$.ajax({
			url : "/thejoun/chat/sendMessage.do",
			type : "post",
			data : $("#inputForm").serialize(),
			success : function(res){
				$("#messageArea").html(res);
				$('#conversation').scrollTop($('#conversation')[0].scrollHeight);
				clearMessage();
			}
		});
	}
	//메세지 보내면 메세지창에있던 내용 지우는거임
	function clearMessage(){
		$("#chatconttent").val("");
	}
</script>
</head>
<body>

	<div class="container app">
		<div class="row app-one">
			<div class="col-sm-4 side">
			
			<!--  좌측 친구창임 -->
				<div id="chatFriendsListArea"></div>
			</div>
			<!--  우측 페이지 -->
			<div class="col-sm-8 conversation">
				<div>
					<div id="messageArea" style="height:93.5%;"></div>
						<div class="row reply">
							<form name="inputForm" id="inputForm" method="post">
							<input type="hidden" name="to_id" id="to_id" value="">
							<input type="hidden" name="from_id" id="from_id" value="${userInfo.userno }">
								<div class="col-sm-9 col-xs-9 reply-main">
									<input type="text" class="form-control" name="chatcontent" id="chatconttent">
								</div>
								<div class="col-sm-1 col-xs-1 reply-send">
									<i class="fa fa-send fa-2x" aria-hidden="true" onclick="sendMessage()"></i>
								</div>
							</form>
						</div>
				</div>
			</div>
		</div>
	</div>
</body>