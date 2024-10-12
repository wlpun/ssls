package com.zkz.dao;

import com.zkz.domain.Borrow;
import com.zkz.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BorrowDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //get record List by user id
    public List<Borrow> getRecordList(int userId) {
        List<Borrow> borrowRecordList = null;
        try {
            String sql = "select * from borrow where user_id = ?";
            borrowRecordList = template.query(sql, new BeanPropertyRowMapper<>(Borrow.class), userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return borrowRecordList;
        }
    }

    //add borrow record
    public void addBorrowRecord(int userId, int bookId) {
        try {
            String sql = "Insert into borrow(book_id, user_id, borrow_time, deadline, ISReturn, isContinue) values" +
                    "(?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 31 DAY), " +
                    "'%Y-%m-%d %H:%i:%s'), 0, 0)";
            template.update(sql, bookId , userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return book, update borrow status
    public void returnBook(int bookId, String return_time) {
        try {
            String sql = "update borrow set IsReturn = 1,return_time = ? where book_Id = ?";
            template.update(sql, return_time,bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean continueBorrow(int book_id) {
        int affectRows = 0;
        try {
//            续借只能续借一次,并且续借一次只能加31天
            String sql = "update borrow set deadline = DATE_ADD(deadline, interval 31 DAY), isContinue = 1 " +
                    "where book_id = ? and isReturn = 0";
//            String sql = "update borrow set deadline = DATE_SUB(deadline, interval 31 DAY) where book_id = ? and isReturn = 0";
            affectRows = template.update(sql, book_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return affectRows > 0;
        }
    }

    public List<Borrow> getBorrowingList(int userId) {
        List<Borrow> borrowRecordList = null;
        try {
            String sql = "select * from borrow where user_id = ? and isReturn = 0";
            borrowRecordList = template.query(sql, new BeanPropertyRowMapper<>(Borrow.class), userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return borrowRecordList;
        }
    }

    public List<Integer> borrowStatistics(){
        List<Integer> list = null;
        try{
            String sql = "SELECT COUNT(br.book_id) AS count FROM book AS b LEFT JOIN borrow AS br ON b.id = br.book_id GROUP BY b.categoryId";
            list = template.queryForList(sql, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
}
