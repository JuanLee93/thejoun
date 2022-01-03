<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
					<table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <tbody>
						<c:if test="${empty cList }">
                            <tr>
                                <td class="first" colspan="4">등록된 댓글이 없습니다.</td>
                            </tr>
						</c:if>
                        <c:if test="${!empty cList }"> 
                        	<c:forEach var="vo" items="${cList }">
                            <tr>
                                <td>${vo.c_no }</td>
                                <td class="txt_l" style="text-align:left;">
                                    ${vo.content }
                                    <c:if test="${vo.userno == userInfo.userno }">
                                    <a href="javascript:goDel(${vo.c_no});">[삭제]</a>
                                    <%-- 자바에서 자바스크립트로 넘겨주는 것은 불가능, 서버에서 먼저 실행되고 나서 클라이언트단에서 실행되도록 --%>
                                    </c:if>
                                </td>
                                <td class="writer">
                                    ${vo.name }
                                </td>
                                <td class="date"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
                            </tr>
                        	</c:forEach>
                        </c:if>
                        </tbody>
                    </table>