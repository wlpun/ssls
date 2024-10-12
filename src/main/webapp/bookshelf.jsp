<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-05-15
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@include file="header.jsp"%>
<script>contextPath = "<%= request.getContextPath() %>";</script>
<div class="bookshelfCenter">
    <form action="${ctx}/BorrowSuccessServlet" method="post">
        <div class="bookshelf">
                <table>
                    <thead>
                    <tr>
                        <th><button type="button" class="select-all-button" id="selectAllButton">全选</button></th>
                        <th>图片</th>
                        <th>书名</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="bookForShelf" items="${bookshelf.bookList}">
                        <tr>
                            <td><input type="checkbox" name="selected" value="${bookForShelf.id}"></td>
                            <td><img src="${ctx}/images/${bookForShelf.imageUrl}"></td>
                            <td>${bookForShelf.name}</td>
                            <td>${bookForShelf.authors}</td>
                            <td>${bookForShelf.press}</td>
                            <%--using data-* custom properties, and in js, can event.target.dataset.id get id--%>
                            <td><button class="delete-button" data-id="${bookForShelf.id}">x</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
        <button type="submit" class="confirm-button">确认借阅</button>
    </form>
</div>
<script src="${ctx}/js/bookshelf.js"></script>
<%@include file="footer.jsp"%>
