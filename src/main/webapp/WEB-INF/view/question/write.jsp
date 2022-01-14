<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항</title>
<link rel="stylesheet" href="/thejoun/css/reset.css"/>
<link rel="stylesheet" href="/thejoun/css/common.css"/>
<link rel="stylesheet" href="/thejoun/css/contents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
<script type="text/javascript" src="/thejoun/smarteditor/js/HuskyEZCreator.js"></script>
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
		oEditors.getById['content'].exec("UPDATE_CONTENTS_FIELD", []);
		var is_checked;
		if ($('input:checkbox[name="checked"]').is(":checked")) {
			is_checked = 'Y';
		} else {
			is_checked = 'N';
		}
		
		$("#secret").val(is_checked);
		$("#form").submit();
	}
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">문의하기</h3>
    
                <div class="bbs">
                <form method="post" name="form" id="form" action="insert.do" enctype="multipart/form-data" >
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
                                <input type="file" name="file">
                            </td>
                        </tr>
                        <tr>
                        	<th>비밀글</th>
                        	<input type="hidden" name="secret" id="secret" />
                        	<td>게시글을 비밀글로 지정하시겠습니까?&nbsp;<input type="checkbox" name="checked" /> </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="btn1Set"  style="text-align:right;">
                        <a class="btn1" href="javascript:goSave();">저장 </a>
                    </div>
                    </form>
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>