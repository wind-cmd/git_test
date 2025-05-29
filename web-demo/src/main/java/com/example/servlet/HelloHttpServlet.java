package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/helloHttp")
public class HelloHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET...");
        // 请求方法
        System.out.println(request.getMethod());
        // 获取虚拟目录
        System.out.println(request.getContextPath());
        // 统一资源定位符
        System.out.println(request.getRequestURI());
        // 统一资源标识符
        System.out.println(request.getRequestURI());
        // 获取请求参数
        System.out.println(request.getQueryString());

        // 获取请求头
        System.out.println(request.getHeader("user-agent"));

        // 获取请求参数
        // request.getParameterMap();
        // request.getParameterValues("");
        // request.getParameter("");
        // request.getParameterNames();

        // 重定向
//        request.setAttribute("test", "testAttribute");
//        request.getRequestDispatcher("/helloHttp2").forward(request, resp);

        // 响应重定向
        // resp.setStatus(302);
        // resp.setHeader("Location", "/helloHttp2");
         resp.sendRedirect("/web-demo/helloHttp2");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST...");
        // 获取请求体
        // 字符流
        // BufferedReader br = req.getReader();
        // 字节流
        // ServletInputStream sis = req.getInputStream();
    }

}