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
<link rel="stylesheet" href="/chat/css/bootstrap-chat.css">
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
 int first = 0;
	setInterval(function(){
		$(function(){
			$.ajax({
				url : "/chat/chat/chatFriendsList.do",
				type : "post",
				success : function(res){
					$("#chatFriendsListArea").html(res);
					if(first == 0){
						first += 1000;	
					}
					consol.log("1")
				}
				
			});
		});
	},(int)first);
	
	function findChattingList(chatFriendsListUserno){
		$.ajax({
			url : "/chat/chat/findChattingTab.do",
			type : "post",
			data : $("#frm"+chatFriendsListUserno).serialize(),
			success : function(res){
				$("#messageArea").html(res);
			}
		});
	}
	
	function sendMessage(){
		$.ajax({
			url : "/chat/chat/sendMessage.do",
			type : "post",
			data : $("#inputForm").serialize(),
			success : function(res){
				$("#messageArea").html(res);
			}
		})
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
				<div id="messageArea"></div>
			</div>
		</div>
	</div>
</body>