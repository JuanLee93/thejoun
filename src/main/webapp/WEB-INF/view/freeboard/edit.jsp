<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="https://malsup.github.io/jquery.form.js"></script>
<script>
	function goSave() {
		if ($("#title").val=='') {
			alert('제목을 입력하세요');
			$("#title").focus();
			return;
		
		}
		// serialize : 직렬화 -> form을 직렬화시키면 그 안의 값들을 한 줄로 가져옴 -> console.log로 출력시키면, (예) boardno=2&title=파일첨부?&content=잘 되나?
		var data = $("#frm").serialize();
		/*
		$.ajax({
			url : 'update.do',
			data : data,
			type : 'post',
			success : function(res) {
				alert('정상적으로 수정되었습니다.');
				location.href='view.do?boardno=${data.boardno}';
			}
		});
		*/
		$("#frm").submit();// 아래 form태그의 id가 frm이므로! ajax 아닐 때 사용
	}
		$(function() {
			$("#frm").ajaxForm({
				//url : 'update.do', // form 태그에 action경로 설정되어 있으므로 제외해도 됨
				success : function(res) {
					alert('정상적으로 수정되었습니다.');
					location.href='view.do?boardno=${data.boardno}';
				},
				error : function(error) {
					console.log(error);
				}
			});
		});
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
    
                <div class="bbs">
                <form method="post" name="frm" id="frm" action="update.do" enctype="multipart/form-data" >
                <input type="hidden" name="boardno" value="${data.boardno }">
                    <table class="board_write">
                        <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" id="title" class="wid100" value="${data.title }"/>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content" id="content">${data.content }</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td>
                            	<input type="checkbox" name="delCheck" value="1">기존파일삭제(${data.filename_org })<br>
                                <input type="file" name="file"><%-- controller에 insert 매개변수 이름이 같아야 한다. --%>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                        <a class="btn" href="javascript:goSave();">저장 </a>
                    </div>
                    </form>
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>