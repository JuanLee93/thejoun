<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고민게시판</title>
<link rel="stylesheet" href="/thejoun/css/reset.css" />
<link rel="stylesheet" href="/thejoun/css/common.css" />
<link rel="stylesheet" href="/thejoun/css/contents.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script>
	function del() {
		if (confirm("삭제하시겠습니까?")) {
			$.ajax({
				url : 'deleteAjax.do',
				data : {concern_board_no : ${data.concern_board_no}},
				success : function(res) {
					if (res.trim() == '1') {
						alert('정상적으로 삭제되었습니다.');
						location.href='index.do';
					} else {
						alert('삭제 오류');
					}
				}   
			});
		}
	}
	
	function goSave() {
		if (confirm("댓글을 등록하시겠습니까?")) {
			$.ajax({
				url : '/thejoun/comment/insert.do',
				type : 'post',
				data : $("#frm").serialize(),
				success : function(res) {
					if (res.trim() == '1') {
						alert('댓글이 등록되었습니다.');
						commentList(2 , ${data.concern_board_no});
						$("#content").val("");
					} else {
						alert('댓글 등록 오류');
					}
				}
			});
		}
	}
	
	function commentList(tablename, board_no) {
		$.ajax({
			url : '/thejoun/comment/list.do',
			data : {tablename : tablename, board_no : board_no},
			success : function(res) {
				$("#commentArea").html(res);
			}
		});
	}
	$(function() {
		commentList(2 , ${data.concern_board_no});
	});
	
	
	function showForm(comment_no) {
		$("#comment_"+comment_no).show();
	}
	
	function goEdit(comment_no) {
		var content = $("#content_"+comment_no).val();
		$.ajax({
			url : "/thejoun/comment/update.do",
			data : {comment_no : comment_no, content:content},
			success : function(res) {
				if (res.trim() == '1') {
					alert('정상적으로 수정되었습니다.');
					commentList(2, ${data.concern_board_no});
				} else {
					alert('수정오류');
				}
			}
		});
	}
	function replyForm(comment_no) {
		$("#commentReply_"+comment_no).show();
	}
	
	function goCommentReply(userno, comment_no, gno, ono, nested) {
		var content = $("#contentReply_"+comment_no).val();
		$.ajax({
			url : "/thejoun/comment/insertCommentReply.do",
			type:'post',
			data : {userno:${userInfo.userno}, gno:gno, ono:ono, nested:nested, board_no:${data.concern_board_no}, tablename:2, comment_no : comment_no, content:content},
			success : function(res) {
				if (res.trim() == '1') {
					alert('정상적으로 답글이 등록되었습니다.');
					commentList(2, ${data.concern_board_no});
				} else {
					alert('답글등록 오류');
				}
			}
		});
	}
	
	function goDel(comment_no) {
		if (confirm('댓글을 삭제하시겠습니까?')) {
			$.ajax({
				url : "/thejoun/comment/delete.do",
				data : {comment_no : comment_no},
				success : function(res) {
					if (res.trim() == '1') {
						alert('정상적으로 삭제되었습니다.');
						commentList( 2 , ${data.concern_board_no});
					} else {
						alert('삭제오류');
					}
				}
			});
		}
	}
	function likeUpdate() {
		$.ajax({
			url : "/thejoun/likeupdate",
			data : {board_no :  ${data.concern_board_no}, userno : ${userInfo.userno}, tablename:2},
			success : function(res) {
				if (res.trim() == '1') {
					// 삭제
					$("#likeCount").text(Number($("#likeCount").text()) - 1 );
				} else {
					// 추가
					$("#likeCount").text(Number($("#likeCount").text()) + 1 );
				}
			}
		});
	}	
	function bookmarkUpdate() {
		$.ajax({
			url : "/thejoun/bookmarkupdate",
			data : {board_no : ${data.concern_board_no}, userno : ${userInfo.userno}, tablename:2},
			success : function(res) {
				if (res.trim() == '1') {//등록된 게시물 -> 중복확인
					alert('이미 북마크에 등록된 게시글입니다.');
				} else {
					// 추가
					alert('북마크에 해당 게시글을 추가했습니다.');
				}
			}
		});
	}
	function report() {
		
		$.ajax({
			url : "/thejoun/report",
			data : {board_no : ${data.concern_board_no}, userno : ${userInfo.userno}, tablename:2},
			success : function(res) {
				if (parseInt(res) === 1) {//등록된 신고건 -> 중복확인
					alert('이미 신고된 게시글입니다.');
				} else {
					// 추가
					alert('이 게시글을 신고하였습니다.');
				}
			}
		});
	}

</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp"%>
		<div class="sub">
			<div class="size">
				<h3 class="sub_title">고민게시판</h3>
				<div class="bbs">
					<div class="view">
						<div class="title">
							<dl>
								<dt class="tit" style="text-align: center;">|&emsp;&emsp;&emsp;&emsp;${data.title }&emsp;&emsp;&emsp;&emsp;|
								</dt>
								<dt class="title_nic" style="text-align: right;">작성자 :
									${data.nickname }
									<c:if test="${userInfo.userno  != data.userno }">
										<form id="friendsAddButton" method="post" action="/thejoun/friendsAdd/addFriendsButton.do">
											<input type="hidden" name="to_userno" value="${data.userno }">
											<input type="hidden" name="pageUrl" value="${requestScope['javax.servlet.forward.request_uri']}?board_no=${data.concern_board_no}">
											<button type="submit" value="친구추가">친구추가</button>
										</form>
									</c:if>
								</dt>
								<dd class="date" style="text-align: right;">작성일 :
									${data.regdate }</dd>
							</dl>
						</div>
						<div class="cont">
							<p>${data.content }</p>
						</div>

						<dl class="file">
							<dt>첨부파일</dt>
							<dd>
								<a
									href="/thejoun/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}"
									target="_blank">${data.filename_org }</a>
							</dd>
						</dl>
						<dl class="file">
						<div>
							<dt>좋아요</dt>
							<c:if test="${!empty userInfo }">
								<dd><a href="javascript:likeUpdate();" id="likeCount">${data.l_count }</a></dd>
							</c:if>
							<c:if test="${empty userInfo }">
							<dd><a href="javascript:alert('로그인 후 사용가능합니다.'); location.href='/thejoun/user/login.do';">${data.l_count }</a></dd>
							</c:if>
									
                    		<dt><button type="button" class="bm_image" id="bookmarkUpdate" onclick="javascript:bookmarkUpdate();"><img src="/thejoun/images/bookmark.png"></button></dt>
                    		<dt><button type="button" class="bm_image" id="buttonReport" onclick="javascript:report();">신고하기</button></dt>
						</div>
						</dl>
						<div class="btnSet clear" style="text-align: center;">
							<div class="fl_l">
								<a href="index.do" class="btn1">목록으로</a>
								<c:if test="${data.userno == userInfo.userno }">
									<a href="edit.do?board_no=${data.concern_board_no }"
										class="btn1">수정</a>
									<a href="javascript:del();" class="btn1">삭제</a>
								</c:if>
							</div>
						</div>
					</div>
					<div style="height: 30px">
						<p>[댓글]</p>
					</div>
					<div>
						<div id="commentArea"></div>

						<c:if test="${!empty userInfo }">
							<!--   로그인하지 않은 상태에서는 댓글작성 불가 -->
							<form method="post" name="frm" id="frm" action=""
								enctype="multipart/form-data">
								<input type="hidden" name="tablename" value='2'> <input
									type="hidden" name="board_no" value="${data.concern_board_no }">
								<input type="hidden" name="userno" value="${userInfo.userno }">
								<table class="board_write">
									<colgroup>
										<col width="*" />
										<col width="100px" />
									</colgroup>
									<tbody>
										<tr>
											<td><textarea name="content" id="content"
													style="height: 50px;"></textarea></td>
											<td>
												<div class="btn1Set" style="text-align: right;">
													<a class="btn1" href="javascript:goSave();">댓글달기 </a>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp"%>
	</div>
</body>
</html>