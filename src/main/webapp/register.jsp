<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-11
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/login.css">
    <script>
        const contextPath = '<%= request.getContextPath() %>';
    </script>
    <script src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
    <form>
        <h2>用户注册</h2>
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username" required>
        <label for="email">邮箱：</label>
        <input type="email" id="email" name="email" required>
        <label for="password">密码：</label>
        <input type="password" id="password" name="password" required>
        <label for="confirm-password">确认密码：</label>
        <input type="password" id="confirm-password" required>
        <label for="phone">手机号码：</label>
        <input type="text" id="phone" name="phone" required>
        <label for="address">地址：</label>
        <input type="text" id="address" name="address" required>
        <button type="submit" id="register-btn">注册</button>
        <p>已有账号？<a href="${ctx}/login.jsp">点击登录</a></p>
    </form>
<div id="alertReg" class="alert" style="display:none;">
    <p id="alert-textReg"></p>
    <button id="alert-closeReg">关闭</button>
</div>
<script src="${ctx}/js/reg.js"></script>
</body>
</html>