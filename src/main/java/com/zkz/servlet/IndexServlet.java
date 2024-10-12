package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.CategoryDao;
import com.zkz.domain.Book;
import com.zkz.domain.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        当用户点击首页分类标签时，获取首页分类id
        String category_id = request.getParameter("category_id");
//        初始时，category_id为null，设置category_id为0，Dao处理中如果是0则展示所有书籍
        if (category_id == null){
            category_id = "0";
        }
        BookDao bookDao = new BookDao();
        CategoryDao categoryDao = new CategoryDao();
//        根据分类获取书籍
        List<Book> bookList = bookDao.getBookByCategoryId(Integer.valueOf(category_id));
//        获取所有分类
        List<Category> categoryList =  categoryDao.getAllCategory();
        HttpSession session = request.getSession();
        session.setAttribute("bookList", bookList);
        session.setAttribute("categoryList", categoryList);
//        为实现active类，传category_id
        session.setAttribute("category_id", Integer.valueOf(category_id));
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
