package com.zkz.servlet;

import com.zkz.dao.BookDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditBookServlet", value = "/EditBookServlet")
public class EditBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String authors = request.getParameter("authors");
        String press = request.getParameter("press");
        String publishDate = request.getParameter("publishDate");
        String status = request.getParameter("status");
        int bookId = Integer.valueOf(request.getParameter("bookId"));
        BookDao bookDao = new BookDao();
        bookDao.editBook(name, authors, press, publishDate, status, bookId);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
