<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-28
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="header.jsp"%>
<div class="user">
    <div class="menu">
        <ul>
            <li>个人信息</li>
            <a href="${ctx}/bookshelf.jsp"><li>借阅架</li></a>
            <a href="${ctx}/MyBorrowServlet"><li>我的借阅</li></a>
        </ul>
    </div>
    <div class="content">
        <div class="row">
            <div class="label">用户名：</div>
            <div class="value">${user.user_name}</div>
        </div>
        <div class="row">
            <div class="label">邮箱：</div>
            <div class="value">${user.email}</div>
        </div>
        <div class="row">
            <div class="label">电话号码：</div>
            <div class="value">${user.phone}</div>
        </div>
        <div class="row">
            <div class="label">地址：</div>
            <div class="value">${user.address}</div>
        </div>
        <div class="row">
            <div class="label">密码：</div>
            <div class="value">********</div>
            <button class="change-password-button">修改密码</button>
        </div>
        <div class="row">
            <div class="label">账号余额</div>
            <div class="value userBalance">${user.balance}</div>
            <button class="recharge">充值</button>
        </div>
    </div>
    <!-- 修改密码框 -->
    <div class="password-modal">
        <div class="modal-content">
            <h3>修改密码</h3>
            <form id="changePasswordForm">
                <input type="password" name="newPassword" placeholder="请输入新密码">
                <button class="close1" type="button">关闭</button>
                <button type="submit">确认修改</button>
            </form>
        </div>
    </div>
    <!--  账户充值-->
    <div class="balance">
        <div class="invest-money">
            <h3>账户充值</h3>
            <form id="recharge">
                <input type="text" name="recharge" placeholder="请输入充值金额">
                <button class="close2" type="button">关闭</button>
                <button type="submit">确认充值</button>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/userCenter.js"></script>
<%@ include file="footer.jsp"%>
