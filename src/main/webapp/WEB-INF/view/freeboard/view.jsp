<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/project/css/reset.css"/><!-- reset을 제일 먼저 하는게 좋다. -->
<link rel="stylesheet" href="/project/css/common.css"/>
<link rel="stylesheet" href="/project/css/contents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/project/js/common.js"></script><!-- 다른 페이지를 만들 때는 메인화면에서 쓰는 swiper가 없으므로, 분리해서 필요한 것만 사용한다. -->
<script>
	function del() {
		if (confirm("삭제하시겠습니까?")) {
			//location.href="delete.do?boardno=${data.boardno}";// ajax 아닌 경우
			
			// 비동기 ajax 처리
			$.ajax({
				url : 'deleteAjax.do',
				data : {boardno : ${data.boardno}},
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
				url : '/project/comment/insert.do',
				type : 'post',
				data : $("#frm").serialize(),
				success : function(res) {
					if (res.trim() == '1') {
						alert('댓글이 등록되었습니다.');// 여기까지만 하면, 댓글 목록이 자동적으로 업데이트되지 않는다.
						commentList('board', ${data.boardno});
						$("#content").val("");
					} else {// 1이 아닌 경우
						alert('댓글 등록 오류');
					}
				}
			});
		}
	}
	
	function commentList(tablename, boardno) {// 여러 곳에서 쓰일 때는 매개변수로 받아서 처리
		$.ajax({
			url : '/project/comment/list.do',
			data : {tablename : tablename, boardno : boardno},// 매개변수를 써줌 - 속성명 : 속성값
			success : function(res) {
				$("#commentArea").html(res);
			}
		});
	}
	$(function() {// 클릭한 view page에 해당하는 boardno를 넣어줘야 하므로
		commentList('board', ${data.boardno});
	});
	
	function goDel(c_no) {
		if (confirm('댓글을 삭제하시겠습니까?')) {// 확인을 누르면 아래와 같이 실행됨
			$.ajax({
				url : "/project/comment/delete.do",
				data : {c_no : c_no},// 앞에는 이름, 뒤에는 매개변수
				success : function(res) {
					if (res.trim() == '1') {
						alert('정상적으로 삭제되었습니다.');
						commentList('board', ${data.boardno});
					} else {
						alert('삭제오류');
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
                <div class="bbs">
                    <div class="view">
                        <div class="title">
                            <dl>
                                <dt>${data.title }</dt>
                                <dd class="date">작성일 : ${data.regdate } </dd>
                            </dl>
                        </div>
                        <div class="cont"><p>${data.content }</p> </div>
                        <dl class="file">
                            <dt>첨부파일 </dt>
                            <dd>
                            <a href="/project/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}" 
                            target="_blank">${data.filename_org }</a></dd>
                        </dl>
                                    
                        <div class="btnSet clear">
                            <div class="fl_l">
                            	<a href="index.do" class="btn">목록으로</a>
	                            <a href="edit.do?boardno=${data.boardno }" class="btn">수정</a>
	                            <a href="javascript:del();" class="btn">삭제</a>
                            </div>
                            <%-- a태그는 인라인 태그 --%>
                        </div>
                    </div>
                    <div>
                    <c:if test="${!empty userInfo }"><%-- 로그인하지 않은 상태에서는 댓글작성 불가 --%>
                    <form method="post" name="frm" id="frm" action="" enctype="multipart/form-data" >
                    	<input type="hidden" name="tablename" value="board">
                    	<input type="hidden" name="boardno" value="${data.boardno }">
                    	<input type="hidden" name="userno" value="${userInfo.userno }"><%-- 주의!!! ${data.userno}가 들어가면 해당 글을 쓴 사람의 PK가 들어감! 로그인한 사람의 userno가 들어가야 한다. --%>
                        <table class="board_write">
                            <colgroup>
                                <col width="*" />
                                <col width="100px" />
                            </colgroup>
                            <tbody>
                            <tr>
                                <td>
                                    <textarea name="content" id="content" style="height:50px;"></textarea>
                                </td>
                                <td>
                                    <div class="btnSet"  style="text-align:right;">
                                        <a class="btn" href="javascript:goSave();">저장 </a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
					</c:if>
                    <%-- 영역을 만들어서 ajax로 응답받은 결과를 HTML로 넣어준다. --%>
                    <div id="commentArea"></div>
                    
                    </div>
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>