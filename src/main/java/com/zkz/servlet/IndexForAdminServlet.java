package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.domain.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexForAdminServlet", value = "/IndexForAdminServlet")
public class IndexForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.getBookByCategoryId(0);
        HttpSession session = request.getSession();
        session.setAttribute("bookList", bookList);
        response.sendRedirect(request.getContextPath() + "/admin/bookEdit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
