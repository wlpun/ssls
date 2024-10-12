package com.zkz.dao;

import com.zkz.domain.Fine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FineDaoTest {
    private FineDao fineDao = null;

    @BeforeEach
    public void init() {
        fineDao = new FineDao();
    }

    @Test
    void addFineRecord() {
        System.out.println(fineDao.addFineRecord(7, 2, 2,"2023-05-24 09:00:05"));
    }

    @Test
    void payFine() {
        System.out.println(fineDao.payFine(5,2));
    }

    @Test
    void  isPayFine() {
        System.out.println(fineDao.isPayFine(5));
    }

    @Test
    void fineStatistics() {
        System.out.println(fineDao.fineStatistics());
    }
}