<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-06-01
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <link rel="stylesheet" href="${ctx}/css/index.css">
    <link rel="stylesheet" href="${ctx}/css/bookEdit.css">
    <script src="${ctx}/js/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/js/echarts.min.js"></script>
    <script>const contextPath = "<%= request.getContextPath() %>" ;</script>
</head>
<body>
<header class="header">
    <div class="search-box">
        <h3>图书管理系统-图书管理</h3>
    </div>
    <div class="menu">
        <a href="${ctx}/IndexForAdminServlet" class="menu-item">图书编辑</a>
        <a href="${ctx}/StatisticalAnalysisServlet" class="menu-item">统计分析</a>
        <a href="${ctx}/LogoutServlet" class="menu-item">登出</a>
    </div>
</header>