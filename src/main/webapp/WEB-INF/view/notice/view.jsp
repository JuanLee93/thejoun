<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="/thejoun/css/reset.css"/>
<link rel="stylesheet" href="/thejoun/css/common.css"/>
<link rel="stylesheet" href="/thejoun/css/contents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="/thejoun/js/common.js"></script>
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/view/include/header.jsp" %>
		<div class="sub">
            <div class="size">
                <h3 class="sub_title">공지사항</h3>
                <div class="bbs">
                    <div class="view">
                        <div class="title">
                            <dl>
                                <dt class ="tit" style="text-align:left;">${data.title }</dt><dt class="title_name" style="text-align:right;">작성자 : ${data.name }</dt>
                                <dd class="date" style="text-align:right;">등록일 : ${data.regdate } </dd>
                            </dl>
                        </div>
                        <div class="cont"><p>${data.contents }</p><br> </div>

                        <dl class="file">
                            <dt>첨부파일 </dt>
                            <dd>
                            <a href="/thejoun/common/download.jsp?path=/upload/&org=${data.filename_org }&real=${data.filename_real}" 
                            target="_blank">${data.filename_org }</a></dd>
                        </dl>
						<div style="height: 30px">
                        <div class="btnSet clear" style="text-align:center;">
                            <div class="fl_l" >
                            	<a href="index.do" class="btn1" style="margin-top: 40px;">목록으로</a>
                            </div>
                        </div>
                    	</div>
                    	<table>
                        	<td style="margin-letf: 20px; text-align: left; font-weight: bold;">
                        		<c:if test="${empty prev.notice_no}">< 이전 글이 없습니다.</c:if>
                        		<c:if test="${!empty prev.notice_no}">
                        		<a href="view.do?notice_no=${prev.notice_no }">< 이전글 - ${prev.title }</a>
                        		</c:if></td>
                        	<td style="margin-right: 20px; text-align: right; font-weight: bold;">
                        		<c:if test="${!empty next.notice_no}">
                        		<a href="view.do?notice_no=${next.notice_no }">${next.title } - 다음글 ></a>
                        		</c:if>       
                        		<c:if test="${empty next.notice_no}">다음 글이 없습니다. ></c:if></td>        	
                        </table>
                	</div>
                	<div style="margin-top: 40px; height: 30px">
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>