package com.zkz.servlet;

import com.zkz.dao.BorrowDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ContinueBorrowServlet", value = "/ContinueBorrowServlet")
public class ContinueBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.valueOf(request.getParameter("bookId"));
        BorrowDao borrowDao = new BorrowDao();
        borrowDao.continueBorrow(bookId);
        response.sendRedirect(request.getContextPath() + "/MyBorrowServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
