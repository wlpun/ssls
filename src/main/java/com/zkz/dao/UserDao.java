package com.zkz.dao;

import com.zkz.domain.User;
import com.zkz.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //register account;
    public boolean addAccount(String username, String pwd, String email, String phone, String address) {
        int affectRows = 0;
        try {
            String judge = "select * from reader where user_name = ? or email = ?";
            List<User> userList = template.query(judge, new BeanPropertyRowMapper<>(User.class), username, email);
            if (userList.isEmpty()) {
                String sql = "insert into reader(user_name, password, phone, email, address, balance) values(?, ?, ?, ?, ?, ?)";
                String pwdMD5 = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
                affectRows = template.update(sql, username, pwdMD5, phone, email, address, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return affectRows > 0;
        }
    }

//    get user by email and pwd
    public User getUser(String email, String password) {
        User user = null;
        try {
            String sql = "select * from reader where email = ? and password = ?";
//            使用md5加密
            String pwdMD5 = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email, pwdMD5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

//    change password
    public boolean changePassword(int user_id, String pwd) {
        int flag = 0;
        try {
            String pwdMD5 = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
            String sql = "update reader set password = ? where id = ?";
            flag = template.update(sql, pwdMD5, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag > 0;
        }
    }

//    recharge
    public boolean recharge(int user_id, double money) {
        int flag = 0;
        try {
            String sql = "update reader set balance = balance + ? where id = ?";
            flag = template.update(sql, money, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag > 0;
        }
    }

//    payFine
    public boolean payFine(int user_id, double money) {
        int flag = 0;
        try {
            String sql = "update reader set balance = balance - ? where id = ?";
            flag = template.update(sql, money, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag > 0;
        }
    }

//    get balance
    public double getBalance(String user_name) {
        double money = 0;
        try {
            String sql = "select balance from reader where user_name = ?";
            money = template.queryForObject(sql, Double.class, user_name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return money;
        }
    }
}
