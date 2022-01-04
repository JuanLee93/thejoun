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
<script type="text/javascript" src="/project/smarteditor/js/HuskyEZCreator.js"></script>
<script src="/project/js/common.js"></script><!-- 다른 페이지를 만들 때는 메인화면에서 쓰는 swiper가 없으므로, 분리해서 필요한 것만 사용한다. -->
<script>
	var oEditors;
	$(function(){
		oEditors = setEditor("content");
	});

	function goSave() {
		if ($("#title").val=='') {
			alert('제목을 입력하세요');
			$("#title").focus();
			return;
		}
		oEditors.getById['content'].exec("UPDATE_CONTENTS_FIELD", []);// 이 코드가 들어가야만 실제 글 작성 후 저장할 때, textarea에 해당 내용이 들어간다.
		$("#frm").submit();// 아래 form태그의 id가 frm이므로!
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
                <form method="post" name="frm" id="frm" action="insert.do" enctype="multipart/form-data" >
                    <table class="board_write">
                        <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" id="title" class="wid100" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content" id="content" style="width:100%;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td>
                                <input type="file" name="file"><!-- controller에 insert 매개변수 이름이 같아야 한다. -->
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