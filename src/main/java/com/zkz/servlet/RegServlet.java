package com.zkz.servlet;

import com.zkz.dao.UserDao;
import com.zkz.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegServlet", value = "/RegServlet")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取用户提交的表单数据
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // 假设注册成功，返回一个 success 消息
        String message = "";
        UserDao userDao = new UserDao();
        boolean ok = userDao.addAccount(username, password, email, phone, address);
        if(ok) message = "注册成功，请前往登录页面登录";
        else message = "用户名或邮箱已存在，请重新注册";
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
