package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;
import com.zkz.dao.FineDao;
import com.zkz.dao.UserDao;
import com.zkz.domain.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AddToBookshelfServlet", value = "/AddToBookshelfServlet")
public class AddToBookshelfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String message = "";
        UserDao userDao = new UserDao();
        Bookshelf bookshelfFlag = null;
//        Bookshelf是借阅架类，包含已在借阅架的书和相关函数
        Bookshelf bookshelf = null;
//        首先获取session中的user域， 用于判断用户是否登录或者存在罚款未缴纳
        User user = (User)request.getSession().getAttribute("user");
//        用户已登录
        if(user != null) {
//            点击加入借阅架时判断用户书本超时情况
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = localDateTime.format(formatter);
            FineDao fineDao = new FineDao();
            List<Borrow> borrowRecordList = null;
            BorrowDao borrowDao = new BorrowDao();
            BookDao bookDao = new BookDao();
            int userId = ((User)request.getSession().getAttribute("user")).getId();
            borrowRecordList = borrowDao.getBorrowingList(userId);
            for(Borrow borrowRecord: borrowRecordList) {
                //获取当前书籍
                Book book = bookDao.getBookById(borrowRecord.getBook_id());
//            如果当前书籍存在超时情况，则更新
                if (borrowRecord.getDeadline().compareTo(time) < 0 && !borrowRecord.isReturn()) {
                    LocalDateTime dateTime1 = LocalDateTime.parse(borrowRecord.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                当前时间转化为LocalDatetime
                    LocalDateTime dateTime2 = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                dateTime2 - dateTime1
                    Duration duration = Duration.between(dateTime1, dateTime2);
                    int days = (int)duration.toDays();
                    fineDao.addFineRecord(userId, book.getId(), days, borrowRecord.getBorrow_time());
                }
            }
//            判断是否存在未缴纳罚单
            boolean isPay = fineDao.isPayFine(user.getId());
//        用户不存在未缴纳罚单
            if(isPay) {
                int id = Integer.valueOf(request.getParameter("id"));
//                判断当前是否存在书架
                bookshelfFlag = (Bookshelf) request.getSession().getAttribute("bookshelf");
                boolean isShelf = false;
//                判断书本是否在借阅架
                if (bookshelfFlag != null) {
                    for (Book book : bookshelfFlag.getBookList()) {
                        if (book.getId() == id) isShelf = true;
                    }
                }
                String status = request.getParameter("status");
//                判断当前书籍是否在库，设置相应msg
                if (status.equals("在库")) {
                    if (request.getSession().getAttribute("bookshelf") == null) {
                        bookshelf = new Bookshelf();
                    } else {
                        bookshelf = (Bookshelf) request.getSession().getAttribute("bookshelf");
                    }
                    if (!isShelf) bookshelf.add(id);
                    Book book = bookDao.getBookById(id);
                    request.getSession().setAttribute("book", book);
                    message = "加入借阅架成功";
                } else {
                    message = "书本不在库，无法加入借阅架";
                }
//                如果书本已经在借阅架，重新设置msg
                if (isShelf) {
                    message = "书本已在借阅架，请勿重复加入";
                }
            }
//        用户存在未缴纳罚单
            else message = "请先缴纳罚款";
        }
        else message = "您还没有登录";
        request.getSession().setAttribute("bookshelf", bookshelf);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write(message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
