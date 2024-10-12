package com.zkz.dao;

import com.zkz.domain.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class BorrowDaoTest {
    private BorrowDao borrowDao = null;

    @BeforeEach
    public void init() {
        borrowDao = new BorrowDao();
    }

    @Test
    void getRecordList() {
        List<Borrow> borrowRecordList = borrowDao.getRecordList(5);
        System.out.println(borrowRecordList.size());
        for (Borrow borrowRecord: borrowRecordList) {
            System.out.println(borrowRecord);
        }
    }

    @Test
    void continueBorrow() {
        System.out.println(borrowDao.continueBorrow(1));

    }

    @Test
    public void addBorrowRecord() {
        borrowDao.addBorrowRecord(5, 2);
    }

    @Test
    void returnBook() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = localDateTime.format(formatter);
        borrowDao.returnBook(2, time);
    }

    @Test
    void borrowStatistics() {
        System.out.println(borrowDao.borrowStatistics());
    }
}