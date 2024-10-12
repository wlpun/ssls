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
        <a href="${ctx}/myBorrowForBorrowing.jsp"><div class="menu-item active">借阅中</div></a>
        <a href="${ctx}/myBorrowForBorrowed.jsp"><div class="menu-item">已归还</div></a>
        <a href="${ctx}/myBorrowForTimeout.jsp"><div class="menu-item">超时罚单</div></a>
    </div>
    <hr>
    <table>
        <thead>
        <tr>
            <th>书名</th>
            <th>借阅时间</th>
            <th>截止时间</th>
            <th>状态</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${myBorrowForBorrowingArrayList}" var="Borrowing">
        <tr>
            <td>${Borrowing.name}</td>
            <td>${Borrowing.borrow_time}</td>
            <td>${Borrowing.deadline}</td>
<%--            如果已超时还未还书会设置当前借阅中的状态为超时未还,颜色为红色--%>
            <c:if test="${Borrowing.timeout}">
                <td class="red">超时未还</td>
            </c:if>
<%--            如果未超时则显示正常--%>
            <c:if test="${Borrowing.timeout == false}">
                <td class="green">借阅中</td>
            </c:if>
            <td>
                <c:if test="${Borrowing.timeout == false}">
                    <c:if test="${!Borrowing.toContinue}">
                        <form action="${ctx}/ContinueBorrowServlet" method="post">
                            <button class="renew-button" name="bookId" value="${Borrowing.book_id}">续借</button>
                        </form>
                    </c:if>
                </c:if>
                <form action="${ctx}/ReturnBookServlet" method="post">
                    <button class="return-button" name="bookId" value="${Borrowing.book_id}">归还</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${ctx}/js/myBorrow.js"></script>
<%@ include file="footer.jsp"%>
