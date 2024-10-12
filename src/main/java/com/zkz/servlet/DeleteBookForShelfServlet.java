package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.domain.Bookshelf;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteBookForShelfServlet", value = "/DeleteBookForShelfServlet")
public class DeleteBookForShelfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(request.getParameter("id"));
        Bookshelf bookshelf = (Bookshelf) request.getSession().getAttribute("bookshelf");
//        从bookshelf中移除当前书籍
        bookshelf.delete(id);
        request.getSession().setAttribute("bookshelf", bookshelf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
