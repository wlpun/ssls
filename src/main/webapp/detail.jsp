<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-14
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@include file="header.jsp"%>
<script>const contextPath = '<%= request.getContextPath() %>';</script>
<script>
    const id = ${book.id};
    const status = "${book.status}";
</script>
<main class="details">
    <div class="details-left">
        <img src="${ctx}/images/${book.imageUrl}" alt="Book Cover" />
    </div>
    <div class="details-right">
        <h1 class="details-title">${book.name}</h1>
        <p class="details-author"><b>作者：</b>${book.authors}</p>
        <p class="details-publisher"><b>出版社：</b>${book.press}</p>
        <p class="details-description"><b>简介：</b>${book.description}</p>
        <p class="details-published-date"><b>出版时间：</b>${book.publishDate}</p>
        <p class="details-price"><b>定价: </b>${book.price}</p>
        <p class="details-status"><b class="bStatus">状态: </b><span class="thing">${book.status}</span></p>
        <div class="details-add-to-car_toCenter">
        <button class="details-add-to-cart">加入借阅架</button>
        </div>
        <div class="details-message"></div>
    </div>
</main>
<script src="${ctx}/js/detail.js"></script>
<%@include file="footer.jsp"%>
