<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-11
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%--index.jsp--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="header.jsp"%>
<div class="container">
    <div class="sidebar">
        <h2>书籍分类</h2>
        <ul>
            <a href="${ctx}/IndexServlet?category_id=0"> <li>所有书籍</li></a>
            <hr>
<%--            获取分类--%>
            <c:forEach var="category" items="${categoryList}">
                <%--判断是否为当前点击的分类，如果是，则增加active类--%>
                <c:if test="${category_id != category.id}">
               <a href="${ctx}/IndexServlet?category_id=${category.id}"> <li>${category.name}</li></a>
                </c:if>
                <c:if test="${category_id == category.id}">
                   <a href="${ctx}/IndexServlet?category_id=${category.id}"> <li class="active">${category.name}</li></a>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <div class="index-content">
<%--        获取所有书籍--%>
        <c:forEach var="book" items="${bookList}">
            <div class="book">
                <img src="${ctx}/images/${book.imageUrl}" alt="book" width="300px">
                <div class="book-info">
                    <h3>${book.name}</h3>
                    <p>${book.authors}</p>
                    <p>${book.press}</p>
                    <a href="${ctx}/DetailServlet?id=${book.id}"><button>借阅详情</button></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="footer.jsp"%>