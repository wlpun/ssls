package com.zkz.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "FileUploadServlet", value = "/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();   //DiskFileItemFactory 是创建FileItem 对象的工厂
        ServletFileUpload upload = new ServletFileUpload(factory);  //ServletFileUpload 负责处理上传的文件数据
        upload.setHeaderEncoding("utf-8");
        try{
            List<FileItem> itemList = upload.parseRequest(request);
            for (FileItem item:itemList){
                if (item.getFieldName().equals("bookImage")){
                    //文件后缀名保持不变，文件名使用UUID通用唯一识别码
                    String extName = item.getName().substring(item.getName().lastIndexOf("."));
                    String fileName = UUID.randomUUID().toString();
                    String newFileName = fileName + extName;
                    //设定存储文件夹
                    String path = this.getServletContext().getRealPath("/images");
                    File file = new File(path,newFileName);
                    item.write(file);//保存文件
                    response.setContentType("text/html");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().print(newFileName);//响应客户端新文件名称
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
