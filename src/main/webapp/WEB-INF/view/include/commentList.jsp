<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
					<table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="250px" />
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
                                <td>${vo.comment_no }</td>
                                <td class="txt_l" style="text-align:left;">
                                    <c:if test="${vo.nested > 0 }">
                                    	<c:forEach begin="1" end="${vo.nested }">&nbsp;&nbsp;&nbsp;</c:forEach>
                                    	<img src="/thejoun/images/reply.gif">
                                    </c:if>
                                    ${vo.content }
                                    <button type="button" class="commentReplyBtn" onclick="replyForm(${vo.comment_no});">[답글]</button>
                                    <c:if test="${vo.userno == userInfo.userno }">
                                  	<button type="button" class="commentUpdateBtn" onclick="showForm(${vo.comment_no});">[수정]</button>
                                  	<button type="button" onclick="javascript:goDel(${vo.comment_no});">[삭제]</button>
                                    </c:if>
                                </td>
                                <td class="writer">
									<form id="friendsAddButton${vo.userno }" method="post">
	                                    <span style="float:left;">${vo.nickname }</span> <!-- 여기 다가해야함 -->
	                                    <c:if test="${userInfo.userno  != vo.userno }">
												<input type="hidden" name="to_userno" value="${vo.userno }">
												<button type="button" class="friendsButton" style="float:left;"onclick="addFriends(${vo.userno});" >친구추가</button>
										</c:if>
									</form>
                                </td>
                                
                                <td class="date"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr id="comment_${vo.comment_no}" style="display:none;">
                                <td colspan="3">
                                    <textarea name="content" id="content_${vo.comment_no}" style="height:50px;width:100%; resize:none;">${vo.content }</textarea>
                                </td>
                                <td>
                                    <div class="btn1Set"  style="text-align:right;">
                                        <a class="btn1" href="javascript:goEdit(${vo.comment_no});">댓글수정 </a>
                                    </div>
                                </td>
                            </tr>
                            <tr id="commentReply_${vo.comment_no}" style="display:none;">
                                <td colspan="3">
                                    <textarea name="content" id="contentReply_${vo.comment_no}" style="height:50px;width:100%; resize:none;"></textarea>
                                </td>
                                <td>
                                    <div class="btn1Set"  style="text-align:right;"><!-- ${data.userno} 이거 추가했음 -->
                                        <a class="btn1" href="javascript:goCommentReply(${vo.userno}, ${vo.comment_no}, ${vo.gno}, ${vo.ono }, ${vo.nested });">답글달기 </a>
                                    </div>
                                </td>
                            </tr>
                        	</c:forEach>
                        </c:if>
                        </tbody>
                    </table>