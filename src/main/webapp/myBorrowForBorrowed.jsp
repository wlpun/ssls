<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-20
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="header.jsp"%>
<div class="myBorrow">
    <div class="menu">
        <a href="${ctx}/myBorrowForBorrowing.jsp"><div class="menu-item">借阅中</div></a>
        <a href="${ctx}/myBorrowForBorrowed.jsp"><div class="menu-item active">已归还</div></a>
        <a href="${ctx}/myBorrowForTimeout.jsp"><div class="menu-item">超时罚单</div></a>
    </div>
    <hr>
    <table>
        <thead>
        <tr>
            <th>书名</th>
            <th>借阅时间</th>
            <th>截止时间</th>
            <th>归还时间</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="Borrowed" items="${myBorrowForBorrowedArrayList}">
            <tr>
                <td>${Borrowed.name}</td>
                <td>${Borrowed.borrow_time}</td>
                <td>${Borrowed.deadline}</td>
                <td>${Borrowed.return_time}</td>
                <c:if test="${!Borrowed.timeout}">
                <td class="green">已归还</td>
                </c:if>
                <c:if test="${Borrowed.timeout}">
                    <td class="red">超时归还</td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${ctx}/js/myBorrow.js"></script>
<%@ include file="footer.jsp"%>
