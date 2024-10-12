package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;
import com.zkz.dao.UserDao;
import com.zkz.domain.Borrow;
import com.zkz.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.getUser(email, password);
        String message = "";
        if(user != null) {
//            如果登录成功设置传给ajax的msg为success
            message = "success";
            if(user.getUser_name().equals("admin")) {
                message = "admin";
            }
//            同时设置session属性
            request.getSession().setAttribute("user", user);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
