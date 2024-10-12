package com.zkz.dao;

import com.zkz.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {
    private BookDao bookDao;
    @BeforeEach
    public void init() {
        bookDao = new BookDao();
    }
//    @Test
//    void getBook() {
//        List<Book> bookList = bookDao.getBook();
//        System.out.println(bookList.size());
//        System.out.println(bookList);
//    }

    @Test
    void getBookByCategoryId() {
        List<Book> bookList1 = bookDao.getBookByCategoryId(0);
        List<Book> bookList = bookDao.getBookByCategoryId(3);
        System.out.println("ppp" + bookList.size() + '\n' + bookList1.size());
        System.out.println(bookList1.get(0));
    }

    @Test
    void getBookByKey() {
        List<Book> bookList = bookDao.getBookByKey("鸟");
        System.out.println(bookList.size());
    }
    @Test
    void getBookById() {
        Book book = bookDao.getBookById(2);
        System.out.println(book);
    }

    @Test
    void checkStatus() {
        String status = bookDao.getStatus(1);
        System.out.println(status);
    }

    @Test
    void updateStatus() {
        bookDao.updateStatus(1, "在库");
        bookDao.updateStatus(2, "在库");
        bookDao.updateStatus(3, "在库");
        bookDao.updateStatus(4, "在库");
        bookDao.updateStatus(5, "在库");
        bookDao.updateStatus(6, "在库");
        bookDao.updateStatus(7, "在库");
        bookDao.updateStatus(13, "在库");
    }

    @Test
    void editBook() {
        System.out.println(bookDao.editBook("《深入理解计算机系统 》", "（美）布赖恩特（Bryant,R.E.）等", "机械工业出版社",
                "2016-11-14 00:00:00", "不在库", 1));

    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setName("name");
        book.setAuthors("authors");
        book.setPress("press");
        book.setPublishDate("2021-12-01 00:00:00");
        book.setPrice("20");
        book.setDescription("description");
        book.setCategoryId("2");
        book.setImageUrl("newFileName");
        System.out.println(bookDao.addBook(book));
    }

    @Test
    void bookStatistics() {
        System.out.println(bookDao.bookStatistics());
    }
}