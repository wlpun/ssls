package com.zkz.domain;

import com.zkz.dao.BookDao;

import java.util.ArrayList;

public class Bookshelf {
    private ArrayList<Book> bookList;

    public Bookshelf(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public Bookshelf() {
        bookList = new ArrayList<Book>();
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public void add(int id) {
        BookDao bookDao = new BookDao();
        bookList.add(bookDao.getBookById(id));
    }

    public void delete(int id) {
        for (int i = 0; i < bookList.size(); i++) {
            int t = bookList.get(i).getId();
            if(id == t) {
                bookList.remove(i);
                break;
            }
        }
    }
}
