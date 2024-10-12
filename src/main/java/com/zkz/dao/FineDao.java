package com.zkz.dao;

import com.zkz.domain.Book;
import com.zkz.domain.Fine;
import com.zkz.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FineDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    public boolean addFineRecord(int user_id, int book_id, int days, String borrow_time) {
        int affectRows = 0;
        try {
            String sql = "select * from fine where book_id = ? and user_id = ? and isPay = 0";
            List<Fine> fineList = template.query(sql, new BeanPropertyRowMapper<>(Fine.class), book_id, user_id);
            if(fineList.isEmpty()) {
                sql = "insert into fine(book_id, user_id, amount_of_penalty, isPay, borrow_time) values (?, ?, ?, 0, ?)";
                affectRows = template.update(sql,book_id, user_id, days, borrow_time);
            }
            else {
                sql = "update fine set amount_of_penalty = ? where book_id = ? and user_id = ?";
                affectRows = template.update(sql, days, book_id, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return affectRows > 0;
        }
    }

        public boolean payFine(int user_id, int book_id) {
            int affectRows = 0;
            try {
                String sql = "update fine set isPay = 1 where book_id = ? and user_id = ?";
                affectRows = template.update(sql, book_id, user_id);
            } catch (Exception e) {
              e.printStackTrace();
            } finally {
                return affectRows > 0;
            }
        }

//    判断用户是否存在未缴纳罚单
    public boolean isPayFine(int user_id) {
        boolean flag = true;
        try {
            String sql = "select * from fine where user_id = ? and isPay = 0";
            List<Fine> fineList = template.query(sql, new BeanPropertyRowMapper<>(Fine.class), user_id);
            if(!fineList.isEmpty()) flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    //根据时间，用户id, 书id,查询是否缴纳罚款,首先需要查询是否存在记录，此dao为myBorrow服务
    public boolean isPayForTime(int user_id, int book_id, String borrow_time) {
        boolean flag = true;
        try {
            String sql = "select * from fine where user_id = ? and book_id = ? and borrow_time = ?";
            List<Fine> fineList1 = template.query(sql, new BeanPropertyRowMapper<>(Fine.class), user_id, book_id, borrow_time);
            if(fineList1.isEmpty()) flag = false;
            else{
                sql = "select * from fine where user_id = ? and book_id = ? and borrow_time = ? and isPay = 0";
                List<Fine> fineList = template.query(sql, new BeanPropertyRowMapper<>(Fine.class), user_id, book_id, borrow_time);
                if (!fineList.isEmpty()) flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    //获取罚款金额
    public double getFineMoney(int user_id, int book_id, String borrow_time) {
        double money = 0;
        try {
            String sql = "select * from fine where user_id = ? and book_id = ? and borrow_time = ?";
            Fine fine = template.queryForObject(sql, new BeanPropertyRowMapper<>(Fine.class), user_id, book_id, borrow_time);
            money = fine.getAmount_of_penalty();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return money;
        }
    }

    public List<Integer> fineStatistics(){
        List<Integer> list = null;
        try{
            String sql = "SELECT count(*) from fine group by isPay ORDER BY isPay";
            list = template.queryForList(sql, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
}
