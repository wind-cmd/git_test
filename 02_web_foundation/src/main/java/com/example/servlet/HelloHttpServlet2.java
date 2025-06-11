package com.example.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/helloHttp2")
public class HelloHttpServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取request
        System.out.println(req.getAttribute("test"));

        // 设置响应头，主要是格式和编码
        resp.setContentType("text/html;charset=utf-8");
        // resp.setHeader("Content-Type", "text/html;charset=utf-8");
        // response获取字符输出流
        // PrintWriter writer = resp.getWriter();
        // writer.write("Hello HttpServlet2");
        // writer.write("<h1>这是一段内容</h1>");

        // 获取字节输入流
        FileInputStream fis = new FileInputStream("E:\\Workspaces\\VSCode\\java_test\\git_test\\web-demo\\test.text");
        // 方法1.获取字节输出流，输出到页面
        // ServletOutputStream out = resp.getOutputStream();
        // byte[] buff = new byte[1024];
        // int len = 0;
        // while ((len = fis.read(buff)) != -1) {
        // out.write(buff, 0, len);
        // }
        // fis.close();

        // 方法2.使用缓冲流，提高效率
        // 字节输入缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        // 字节输出流
        ServletOutputStream out = resp.getOutputStream();
        // 字节输出缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        byte[] buff = new byte[1024];
        int len;
        while ((len = bis.read(buff)) != -1) {
            System.out.println(buff);
            bos.write(buff, 0, len);
        }
        bos.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
