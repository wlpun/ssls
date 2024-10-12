package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.dao.BorrowDao;
import com.zkz.dao.FineDao;
import com.zkz.domain.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyBorrowServlet", value = "/MyBorrowServlet")
public class MyBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        用于获取当前用户的借阅记录
        List<Borrow> borrowRecordList = null;
//        同时获取三个借阅情况,分别为借阅中,已归还,和已超时的记录
        ArrayList<MyBorrow> myBorrowForBorrowingArrayList = new ArrayList<MyBorrow>();
        ArrayList<MyBorrow> myBorrowForBorrowedArrayList = new ArrayList<MyBorrow>();
        ArrayList<MyBorrow> myBorrowForTimeoutArrayList = new ArrayList<MyBorrow>();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        获取当前时间，用于更新借阅情况
        String time = localDateTime.format(formatter);
        BorrowDao borrowDao = new BorrowDao();
        BookDao bookDao = new BookDao();
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        borrowRecordList = borrowDao.getRecordList(userId);
        for(Borrow borrowRecord: borrowRecordList) {
            //获取当前书籍
            Book book = bookDao.getBookById(borrowRecord.getBook_id());
            MyBorrow myBorrow = new MyBorrow();
            //设置myBorrow相关属性
            myBorrow.setBook_id(book.getId());
            myBorrow.setName(book.getName());
            myBorrow.setAuthors(book.getAuthors());
            myBorrow.setBorrow_time(borrowRecord.getBorrow_time());
            myBorrow.setDeadline(borrowRecord.getDeadline());
            myBorrow.setReturnBook(borrowRecord.isReturn());
            myBorrow.setToContinue(borrowRecord.isContinue());
//            如果当前订单已经超时
            if(borrowRecord.getDeadline().compareTo(time) < 0) {
                myBorrow.setTimeout(true);
                LocalDateTime dateTime1 = LocalDateTime.parse(borrowRecord.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime dateTime2 = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Duration duration = Duration.between(dateTime1, dateTime2);
                int days = (int)duration.toDays();
                FineDao fineDao = new FineDao();
                boolean flag = fineDao.isPayForTime(userId, book.getId() ,borrowRecord.getBorrow_time());
//                如果已经缴纳罚款或者已经归还书籍无需再更新罚金
                if(flag || myBorrow.isReturnBook()) {
                    if(flag)myBorrow.setPay(true);
                    double fineMoney = fineDao.getFineMoney(userId, book.getId(), borrowRecord.getBorrow_time());
                    myBorrow.setFineMoney(fineMoney);
                    myBorrow.setOutDays((int)fineMoney);
                }
                else  {
                    myBorrow.setPay(false);
                    fineDao.addFineRecord(userId, book.getId(), days, borrowRecord.getBorrow_time());
                    double m = fineDao.getFineMoney(userId, book.getId(), borrowRecord.getBorrow_time());
                    myBorrow.setFineMoney(m);
                    myBorrow.setOutDays((int)m);
                }
                myBorrowForTimeoutArrayList.add(myBorrow);
            }
            else myBorrow.setTimeout(false);
            if(!borrowRecord.isReturn()) myBorrowForBorrowingArrayList.add(myBorrow);
            else {
                myBorrow.setReturn_time(borrowRecord.getReturn_time());
                myBorrowForBorrowedArrayList.add(myBorrow);
            }
        }
        HttpSession session = request.getSession();
//        将三种记录分别写入session域
        session.setAttribute("myBorrowForBorrowingArrayList",myBorrowForBorrowingArrayList);
        session.setAttribute("myBorrowForBorrowedArrayList", myBorrowForBorrowedArrayList);
        session.setAttribute("myBorrowForTimeoutArrayList",myBorrowForTimeoutArrayList);
        response.sendRedirect(request.getContextPath() + "/myBorrowForBorrowing.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
