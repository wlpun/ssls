package com.zkz.servlet;

import com.zkz.dao.BookDao;
import com.zkz.domain.Book;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.servlet.http.Part;


@WebServlet(name = "AddBookServlet", value = "/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    private static final String IMAGE_DIRECTORY = "/images";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


        // 获取表单字段的值
        String name = request.getParameter("name");
        String authors = request.getParameter("authors");
        String press = request.getParameter("press");
        String publishDate = request.getParameter("publishDate");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String bookCategory = request.getParameter("bookCategory");


        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);
        book.setPress(press);
        book.setPublishDate(publishDate);
        book.setPrice(price);
        book.setDescription(description);
        book.setCategoryId(bookCategory);
        book.setImageUrl(request.getParameter("bookImage"));
        BookDao bookDao = new BookDao();
        bookDao.addBook(book);

        // 返回响应
        response.setContentType("text/plain");
        response.getWriter().write("Book added successfully.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
