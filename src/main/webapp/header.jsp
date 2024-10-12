<%--header.jsp--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <link rel="stylesheet" href="${ctx}/css/index.css">
    <link rel="stylesheet" href="${ctx}/css/detail.css">
    <link rel="stylesheet" href="${ctx}/css/bookshelf.css">
    <link rel="stylesheet" href="${ctx}/css/borrowSuccess.css">
    <link rel="stylesheet" href="${ctx}/css/myBorrow.css">
    <link rel="stylesheet" href="${ctx}/css/userCenter.css">
    <script src="js/jquery-2.1.4.min.js"></script>
    <script>const contextPath = "<%= request.getContextPath() %>" ;</script>
</head>
<body>
<header class="header">
    <div class="search-box">
<%--        通过form将搜索内容提交--%>
        <form action="${ctx}/SearchServlet" method="post">
            <input type="submit" class="search-btn" value="搜索" >
            <input type="text" placeholder="请输入关键字" name="key">
        </form>
    </div>
    <div class="menu">
        <a href="${ctx}/IndexServlet" class="menu-item">首页</a>
<%--        判断用户是否登录--%>
        <c:if test="${empty user}">
            <a href="${ctx}/login.jsp" class="menu-item">登录</a>
        </c:if>
        <c:if test="${!empty user}">
            <a href="${ctx}/userCenter.jsp" class="menu-item">用户：${user.user_name}</a>
            <a href="${ctx}/bookshelf.jsp" class="menu-item">借书架</a>
            <a href="${ctx}/MyBorrowServlet" class="menu-item">我的借阅</a>
            <a href="${ctx}/LogoutServlet" class="menu-item">登出</a>
<%--            <a href="#" class="menu-item">我的余额</a>--%>
        </c:if>
    </div>
</header>

