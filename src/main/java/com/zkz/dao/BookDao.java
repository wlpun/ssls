package com.zkz.dao;

import com.zkz.domain.Book;
import com.zkz.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
//    public List<Book> getBook() {
//        List<Book> bookList = null;
//        try {
//            String sql = "select * from book order by code desc limit 12";
//            bookList = template.query(sql, new BeanPropertyRowMapper<>(Book.class));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return bookList;
//        }
//    }

//    根据分类id获取书籍
    public List<Book> getBookByCategoryId(int categoryId) {
        List<Book> bookList = null;
        try {
            String sql = null;
//            分类id为0获取所有书籍
            if(categoryId == 0) sql = "select * from book";
            else {
                sql = "select * from book where categoryId = %d";
                //为避免重复操作，这里使用format先将categoryId写入sql语句
                sql = String.format(sql, categoryId);
            }
            bookList = template.query(sql, new BeanPropertyRowMapper<>(Book.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return bookList;
        }
    }

    //根据关键字获取书籍，即搜索模块
    public List<Book> getBookByKey(String key) {
        List<Book> bookList = null;
        try {
            String sql = "select * from book where name like concat('%', ?, '%')";
            bookList = template.query(sql, new BeanPropertyRowMapper<>(Book.class), key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return bookList;
        }
    }

    // get book by id
    public Book getBookById(int id) {
        Book book = null;
        try {
            String sql = "select * from book where id = ?";
            book = template.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return book;
        }
    }

    //获取当前书籍状态
    public String getStatus(int id) {
        String status = null;
        try {
            String sql = "select status from book where id = ?";
            Book book = template.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
            status = book.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return status;
        }
    }

    //to update book status
    public void updateStatus(int id, String status) {
        try {
            String sql = "update book set status = ? where id = ?";
            template.update(sql, status, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    edit book
    public boolean editBook(String name, String authors, String press, String publishDate,  String status, int id) {
        int affectRows = 0;
        try {
            String sql = "update book set name = ?, authors = ?, press = ?, publishDate = ?, status = ? where id = ?";
            affectRows = template.update(sql, name, authors, press, publishDate, status, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return affectRows > 0;
        }
    }

//    add book
    public boolean addBook(Book book) {
        int affectRows = 0;
        try {
            // Generate the code for the new book
            String newCode = generateBookCode();

            // Prepare the SQL statement for inserting the book
            String sql = "INSERT INTO book (Code, Name, Authors, Press, ImageUrl, Description, PublishDate, Price, Status, CategoryId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Execute the SQL statement and retrieve the number of affected rows
            affectRows = template.update(sql, newCode, book.getName(), book.getAuthors(), book.getPress(), book.getImageUrl(),
                    book.getDescription(), book.getPublishDate(), book.getPrice(), "在库", book.getCategoryId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return affectRows > 0;
        }
    }

    // Helper method to generate the book code
    private String generateBookCode() {
        String code = "";
        try {
            // Get the last inserted code from the database
            String sql = "SELECT MAX(Code) FROM book";
            code = template.queryForObject(sql, String.class);

            // Extract the numeric part of the code
            String numericPart = code.substring(2);
            int sequence = Integer.parseInt(numericPart) + 1;

            // Generate the new code by combining "b_" and the incremented sequence
            code = "b_" + String.format("%03d", sequence);
        } catch (Exception e) {
            // If there are no existing codes, start with b_001
            code = "b_001";
        }
        return code;
    }

    public List<Integer> bookStatistics(){
        List<Integer> list = null;
        try{
            String sql = "SELECT count(*) from book group by categoryId;";
            list = template.queryForList(sql, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
}