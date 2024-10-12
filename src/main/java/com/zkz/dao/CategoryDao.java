package com.zkz.dao;

import com.zkz.domain.Category;
import com.zkz.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    // get all category
    public List<Category> getAllCategory() {
        List<Category> categoryList = null;
        try {
            String sql = "select * from category";
            categoryList = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return categoryList;
        }
    }
}
