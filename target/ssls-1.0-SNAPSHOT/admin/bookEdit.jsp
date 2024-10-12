<%--
  Created by IntelliJ IDEA.
  User: 16628
  Date: 2023-06-01
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>

<div class="editBook">
    <h2>图书编辑</h2>
    <hr>
    <button class="addButton">新增图书</button>
    <hr>
    <table>
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>出版社</th>
            <th>出版时间</th>
            <th>状态</th>
            <th>选择</th>
        </tr>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td><input type="text" value="${book.name}" name="name"></td>
                <td><input type="text" value="${book.authors}" name="authors"></td>
                <td><input type="text" value="${book.press}" name="press"></td>
                <td><input type="text" value="${book.publishDate}" name="publishDate"></td>
                <td><input type="text" value="${book.status}" name="status"></td>
                <td>
                    <button class="saveButton" data-id="${book.id}">保存</button>
                    <button class="deleteButton">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="addBookModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>新增图书</h2>
        <form id="addBookForm">
            <div class="form-group">
                <label for="bookImage">图片选择:</label>
                <input type="file" id="bookImage" name="bookImage">
            </div>
            <div class="form-group">
                <label for="name">书名:</label>
                <input type="text" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="authors">作者:</label>
                <input type="text" id="authors" name="authors">
            </div>
            <div class="form-group">
                <label for="press">出版社:</label>
                <input type="text" id="press" name="press">
            </div>
            <div class="form-group">
                <label for="publishDate">出版日期:</label>
                <input type="text" id="publishDate" name="publishDate">
            </div>
            <div class="form-group">
                <label for="price">定价:</label>
                <input type="text" id="price" name="price">
            </div>
            <div class="form-group">
                <label for="description">简介:</label>
                <input type="text" id="description" name="description">
            </div>
            <div class="form-group">
                <label for="bookCategory">分类:</label>
                <select id="bookCategory" name="bookCategory">
                    <option value="1">计算机系统概述</option>
                    <option value="2">Python路线</option>
                    <option value="3">计算机网络</option>
                    <option value="4">Java</option>
                    <option value="5">C++</option>
                    <option value="6">Web和APP开发</option>
                    <option value="7">信息安全</option>
                    <option value="8">Linux入门</option>
                    <option value="9">数据库</option>
                </select>
            </div>
            <div class="form-group">
                <input type="button" value="增加" id="toAddBookButton">
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.saveButton').click(function() {
            var row = $(this).closest('tr');
            var bookData = {
                name: row.find('input[name="name"]').val(),
                authors: row.find('input[name="authors"]').val(),
                press: row.find('input[name="press"]').val(),
                publishDate: row.find('input[name="publishDate"]').val(),
                status: row.find('input[name="status"]').val()
            };
            var bookId = $(this).data('id');
            var requestData = Object.assign({}, bookData, { bookId: bookId });
            $.ajax({
                type: 'POST',
                url: contextPath + '/EditBookServlet',
                data: requestData,
                success: function(response) {
                    window.location.href = contextPath + "/IndexForAdminServlet";
                }
            });
        });

        // 打开新增图书框
        $('.addButton').click(function() {
            $('#addBookModal').show();
        });

        // 关闭新增图书框
        $('.close').click(function() {
            $('#addBookModal').hide();
        })

        // 提交新增图书表单
        $('#toAddBookButton').click(function () {

            // 文件上传请求
            var fileData = new FormData();
            fileData.append('bookImage', $('#bookImage')[0].files[0]);
            console.log(fileData.get('bookImage'));
            $.ajax({
                type: 'POST',
                url: contextPath + '/FileUploadServlet',
                data: fileData,
                processData: false,
                contentType: false,
                success: function(response) {
                    // 处理文件上传成功的逻辑
                    console.log("success");
                    // 文本字段提交请求
                    var formData = {
                        name: $('#name').val(),
                        authors: $('#authors').val(),
                        press: $('#press').val(),
                        publishDate: $('#publishDate').val(),
                        price: $('#price').val(),
                        description: $('#description').val(),
                        bookCategory: $('#bookCategory').val()
                    };
                    // 在这里将返回的文件名添加到formData中
                    formData.bookImage = response;
                    console.log(response);
                    // 然后发送文本字段提交请求
                    submitFormData(formData);
                    $('#addBookModal').fadeOut();
                }
            });

            // 文本字段提交请求
            function submitFormData(formData) {
                $.ajax({
                    type: 'POST',
                    url: contextPath + '/AddBookServlet',
                    data: formData,
                    success: function(response) {
                        // 处理文本字段提交成功的逻辑
                        console.log("Form data submit success");
                    }
                });
            }
        });
    });
</script>
<%@ include file="footer.jsp"%>
