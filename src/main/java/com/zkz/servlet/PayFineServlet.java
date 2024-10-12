package com.zkz.servlet;

import com.zkz.dao.FineDao;
import com.zkz.dao.UserDao;
import com.zkz.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PayFineServlet", value = "/PayFineServlet")
public class PayFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(request.getParameter("bookId"));
        double fineAmount = Double.valueOf(request.getParameter("fineAmount"));
        UserDao userDao = new UserDao();
        FineDao fineDao = new FineDao();
        User user = (User)request.getSession().getAttribute("user");
        fineDao.payFine(user.getId(), id);
        userDao.payFine(user.getId(), fineAmount);
        user.setBalance(userDao.getBalance(user.getUser_name()));
        request.getSession().setAttribute("user", user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
