package com.zkz.dao;

import com.alibaba.druid.support.json.JSONUtils;
import com.zkz.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao userDao;

    @BeforeEach
    void  init() {
        userDao = new UserDao();
    }
    @Test
    void addAccount() {
        boolean affectRows = userDao.addAccount("12", "123", "123@12", "123", "123");
//        assertEquals(true, affectRows);
        System.out.println(affectRows);
    }

    @Test
    void getUser() {
        User user = userDao.getUser("123@123", "123");
        System.out.println(user);
    }

    @Test
    void changePassword() {
        System.out.println(userDao.changePassword(5, "123"));
    }
    @Test
    void recharge() {
        System.out.println(userDao.recharge(5, 1));
    }

    @Test
    void getBalance() {
        System.out.println(userDao.getBalance("123"));
    }

    @Test
    void payFine() {
        System.out.println(userDao.payFine(5, 36));
    }
}