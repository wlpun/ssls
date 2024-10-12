package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;
import com.zkz.domain.Book;
import com.zkz.domain.Bookshelf;
import com.zkz.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BorrowSuccessServlet", value = "/BorrowSuccessServlet")
public class BorrowSuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            String []s  = request.getParameterValues("selected");
            if(s == null) response.sendRedirect(request.getContextPath() + "/bookshelf.jsp");
            else {
                Bookshelf bookshelf = (Bookshelf) request.getSession().getAttribute("bookshelf");
                BorrowDao borrowDao = new BorrowDao();
                BookDao bookDao = new BookDao();
                List<Book> bookListForBorrowSuccess = new ArrayList<Book>();
                for (String x : s) {
                    borrowDao.addBorrowRecord(((User) request.getSession().getAttribute("user")).getId(), Integer.valueOf(x));
                    bookListForBorrowSuccess.add(bookDao.getBookById(Integer.valueOf(x)));
//                    确认借阅后设置态为不在库
                    bookDao.updateStatus(Integer.valueOf(x), "不在库");
                    bookshelf.delete(Integer.valueOf(x));
                }
                request.setAttribute("bookListForBorrowSuccess", bookListForBorrowSuccess);
                request.getRequestDispatcher("/borrowSuccess.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
