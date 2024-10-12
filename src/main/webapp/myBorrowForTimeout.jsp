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
<script>
    var balance = ${user.balance};
</script>
<div class="myBorrow">
    <div class="menu">
        <a href="${ctx}/myBorrowForBorrowing.jsp"><div class="menu-item">借阅中</div></a>
        <a href="${ctx}/myBorrowForBorrowed.jsp"><div class="menu-item">已归还</div></a>
        <a href="${ctx}/myBorrowForTimeout.jsp"><div class="menu-item active">超时罚单</div></a>
    </div>
    <hr>
    <table>
        <thead>
        <tr>
            <th>书名</th>
            <th>借阅时间</th>
            <th>截止时间</th>
            <th>归还时间</th>
            <th>超期天数</th>
            <th>罚金</th>
            <th>是否缴纳</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="timeout" items="${myBorrowForTimeoutArrayList}">
            <tr>
                <td>${timeout.name}</td>
                <td>${timeout.borrow_time}</td>
                <td>${timeout.deadline}</td>

                <c:if test="${timeout.returnBook}">
                    <td>${timeout.return_time}</td>
                </c:if>
                <c:if test="${!timeout.returnBook}">
                    <td class="red">未归还</td>
                </c:if>

                <td>${timeout.outDays}</td>
                <td>${timeout.fineMoney}</td>

                <c:if test="${timeout.pay}">
                    <td class="green">已缴纳</td>
                </c:if>
                <c:if test="${!timeout.pay}">
                    <td class="red">未缴纳</td>
                </c:if>

                <td>
                    <c:if test="${!timeout.pay}">
                        <button class="renew-button" id="pay-fine-button" data-fine="${timeout.fineMoney}" data-id="${timeout.book_id}">
                            缴纳罚款
                        </button>
                    </c:if>
                    <c:if test="${!timeout.returnBook}">
                        <form action="${ctx}/ReturnBookServlet" method="post">
                            <button class="return-button" name="bookId" value="${timeout.book_id}">归还</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${ctx}/js/myBorrow.js"></script>
<%@ include file="footer.jsp"%>
