<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-19
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@include file="header.jsp"%>
<div class="borrowSuccess">
    <div class="border-box">
        <h2 class="success-title">借阅成功</h2>
        <hr>
        <table class="success-table">
            <tbody>
            <c:forEach var="bookForBorrowSuccess" items="${bookListForBorrowSuccess}">
                <tr>
                    <td><img src="${ctx}/images/${bookForBorrowSuccess.imageUrl}" alt="Book Image"></td>
                    <td>${bookForBorrowSuccess.name}</td>
                    <td>${bookForBorrowSuccess.authors}</td>
                    <td>${bookForBorrowSuccess.press}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file="footer.jsp"%>