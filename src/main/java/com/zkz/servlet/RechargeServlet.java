package com.zkz.servlet;

import com.zkz.dao.UserDao;
import com.zkz.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RechargeServlet", value = "/RechargeServlet")
public class RechargeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        double money = Double.valueOf(request.getParameter("money"));
        UserDao userDao = new UserDao();
        User user = ((User)request.getSession().getAttribute("user"));
        userDao.recharge(user.getId(), money);
        user.setBalance(userDao.getBalance(user.getUser_name()));
        request.getSession().setAttribute("user", user);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(user.getBalance());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
