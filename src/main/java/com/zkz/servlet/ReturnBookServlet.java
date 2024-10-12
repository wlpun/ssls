package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ReturnBookServlet", value = "/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int book_id = Integer.valueOf(request.getParameter("bookId"));
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        获取当前的归还时间
        String time = localDateTime.format(formatter);
        BorrowDao borrowDao = new BorrowDao();
        BookDao bookDao = new BookDao();
        borrowDao.returnBook(book_id, time);
//        设置当前书籍状态为在库
        bookDao.updateStatus( book_id, "在库");
        response.sendRedirect(request.getContextPath() + "/MyBorrowServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
