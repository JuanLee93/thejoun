<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script>
	function del() {
		if (confirm("삭제하시겠습니까?")) {
			$.ajax({
				url : 'deleteAjax.do',
				data : {qna_no : ${data.qna_no}},
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
	
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">문의하기</h3>
                <div class="bbs">
                    <div class="view">
                        <div class="title">
		                    <table class="board_write">
		                        <tbody>
		                        	<H2>작성자 정보 및 내용</H2>
			                        <tr>
			                            <th>제목</th>
			                            <td class ="tit">${data.title }</td>
			                        </tr>
			                        <tr>
			                            <th>작성자</th>
			                            <td class="writer">${data.nickname }</td>
			                        </tr>
			                        <tr>
			                            <th>내용</th>
			                            <td class="content">${data.content }</td>
			                        </tr>
			                        <tr>
			                            <th>등록일</th>
			                            <td class="regdate">${data.regdate }</td>
			                        </tr>
			                        <tr>
			                            <th>첨부파일</th>
			                            <td>
			                                <a href="/thejoun/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}" 
	                            			target="_blank">${data.filename_org }</a>
			                            </td>
			                        </tr>
		                        </tbody>
		                    </table>
		                    <div style="width:100%; margin:50px; 0 0 0"></div>		 
		                    <c:if test="${!empty data.reply }">
			                    <table class="board_write2">
			                        <tbody>
			                        <H2>답변 내용</H2>
			                        <tr>
				                         
				                            <td class="content">${data.reply}</td>
				                       </tr>
			                        </tbody>
			                    </table>
  							</c:if>                       
		                    
                       	</div>    
                    </div>
                    <div class="btnSet clear" style="text-align:center;">
                            <div class="fl_l" >
                            	<a href="index.do" class="btn1">목록으로</a>
                            	<c:if test="${data.userno == userInfo.userno }">
	                            <a href="edit.do?qna_no=${data.qna_no }" class="btn1">수정</a>
	                            <a href="javascript:del();" class="btn1">삭제</a>
	                            </c:if>
                            </div>
                    </div>
                </div>
            </div>
        </div>
	<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>