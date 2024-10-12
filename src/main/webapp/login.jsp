<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-11
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="${ctx}/css/login.css">
    <script>
        const contextPath = '<%= request.getContextPath() %>';
    </script>
    <script src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
    <form>
        <h2>用户登录</h2>
        <label for="email">邮箱：</label>
        <input type="email" id="email" required>
        <label for="password">密码：</label>
        <input type="password" id="password" required>
        <button type="submit" id="login-btn">登录</button>
        <p>没有账号？<a href="register.jsp">点击注册</a></p>
    </form>
<div id="alert" class="alert" style="display:none;">
    <p id="alert-text"></p>
    <button id="alert-close">关闭</button>
</div>
<script src="${ctx}/js/login.js"></script>
</body>
</html>

