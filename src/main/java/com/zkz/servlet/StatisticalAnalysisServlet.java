package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;
import com.zkz.dao.FineDao;
import com.zkz.domain.Book;
import com.zkz.domain.Borrow;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StatisticalAnalysisServlet", value = "/StatisticalAnalysisServlet")
public class StatisticalAnalysisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookDao bookDao = new BookDao();
        BorrowDao borrowDao = new BorrowDao();
        FineDao fineDao = new FineDao();
        List<Integer> bookStatistics = bookDao.bookStatistics();
        List<Integer> borrowStatistics = borrowDao.borrowStatistics();
        List<Integer> fineStatistics = fineDao.fineStatistics();
        HttpSession session = request.getSession();
        session.setAttribute("bookStatistics", bookStatistics);
        session.setAttribute("borrowStatistics",borrowStatistics);
        session.setAttribute("fineStatistics", fineStatistics);
        response.sendRedirect(request.getContextPath() + "/admin/statisticalAnalysis.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
