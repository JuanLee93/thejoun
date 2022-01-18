<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.CommonUtil" %>
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
	$(function() {

		$(".board_tr").click(function() {
			
			var writer = $(this).data("user_no");
			var secret = $(this).data("secret");
		
			if (${!empty userInfo }) {
				if (secret === "Y") {
					if (writer === ${userInfo.userno}) {
						location.href='view.do?qna_no='+$(this).data("qna_no");
					} else {
						alert("글쓴이만 열람할 수 있습니다.");
					}
				} else {
					location.href='view.do?qna_no='+$(this).data("qna_no");
				}
				
			} else {
				alert("로그인 후 이용해주세요.");
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
                <h3 class="sub_title">문의하기</h3>
		       
	        	<div class="hg" style="height: 25px"></div>
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총 ${totCount }개</strong>  |  ${questionVo.page }/${totPage }페이지</span></p>
                        <caption>문의하기 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                           
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>처리상태</th>
                                <th>글쓴이</th>
                                <th>등록일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:if test="${empty list }">
                            <tr>
                                <td class="first" colspan="6">등록된 글이 없습니다.</td>
                            </tr>
						</c:if>
                        <c:if test="${!empty list }">
                        <c:forEach var="vo" items="${list }" varStatus="status">
                            <tr class="board_tr" data-qna_no="${vo.qna_no }" data-secret="${vo.secret }" data-user_no="${vo.userno }" style="cursor: pointer;">
                                <td>${(totCount-status.index) - ((questionVo.page-1)*10) }</td>
                                <td class="txt_l" style="text-align:left;">
                                    <c:if test="${vo.secret == 'Y'}">
			                            <img src="/thejoun/images/lock.png" style="display: inline-block; vertical-align: middle; width:13px; height: 15px;">
									</c:if>
                                    ${vo.title } 
                                </td>
                                <td class="state">
                                	<c:if test="${vo.state == 'N'}">
                                	접수
                                	</c:if>
                                	<c:if test="${vo.state == 'Y'}">
                                	답변완료
                                	</c:if>
                                </td>
                                <td class="writer">
                                    ${vo.nickname }
                                </td>
                                <td class="date">${vo.regdate }</td>
                                <td>${vo.readcount }</td>
                            </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <div class="btn1Set"  style="text-align:right;">
					<c:if test="${!empty userInfo }">
                        <a class="btn1" href="write.do">글작성 </a>
  					</c:if>
  					<c:if test="${empty userInfo }">
                        <a class="btn1" href="javascript:alert('로그인 후 사용가능합니다.'); location.href='/thejoun/user/login.do';">글작성 </a>
                    </c:if>
                    </div>
                    ${pageArea }

                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="stype" name="searchType" class="dSelect" title="검색분류 선택">
                                    <option value="">전체</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="nickname">닉네임</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="searchWord" value="" title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색">
                            </span>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</body>
</html>