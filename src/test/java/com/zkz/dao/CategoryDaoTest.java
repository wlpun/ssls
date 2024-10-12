package com.zkz.dao;

import com.zkz.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoTest {
    private CategoryDao categoryDao;

    @BeforeEach
    public void init() {
        categoryDao = new CategoryDao();
    }

    @Test
    void getAllCategory() {
        List<Category> categoryList = categoryDao.getAllCategory();
        System.out.println(categoryList.size());
    }
}