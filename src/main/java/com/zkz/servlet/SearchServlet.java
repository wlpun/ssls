package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.domain.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String key = request.getParameter("key");
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.getBookByKey(key);
        HttpSession session = request.getSession();
        session.setAttribute("bookList", bookList);
//        设置当前category_id为0，是为避免当用户点击分类后时搜索，出现不正常active
        session.setAttribute("category_id", 0);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
