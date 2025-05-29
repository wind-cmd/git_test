package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * HelloServlet
 */
@WebServlet(value = "/hello")
public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化方法，默认情况下，Servlet第一次被访问时调用。
        // 调用次数：一次
        // loadOnStartup 启动时加载，默认为-1，不加载，大于0时，加载，值越小，优先级越高。
        System.out.println("HelloServlet init...");


    }

    /**
     * 调用时机：每一次Servlet被访问时，调用
     * 调用次数：多次
     */
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Hello Servlet");
        HttpServletRequest request = (HttpServletRequest) req;

    }

    /**
     * 销毁方法
     * 调用时机：Servlet被销毁时，调用
     * 调用次数：一次
     */
    @Override
    public void destroy() {
        System.out.println("HelloServlet destroy...");
    }

    /**
     * 获取Servlet配置信息
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 获取Servlet信息
     */
    @Override
    public String getServletInfo() {
        return null;
    }

}
