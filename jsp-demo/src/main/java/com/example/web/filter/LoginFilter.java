package com.example.web.filter;

import com.example.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //配置访问过滤白名单，主要包含登录注册资源
        String[] urls = {"/login.jsp","/register.jsp","/Login","/Register","/CheckCode","/AjaxServlet","register.html"
        ,"/json","json_addBrand.html","json_brand.html"};
        //获取访问路径
        String url = request.getRequestURI().toString();

        //判断访问资源有没有白名单
        for (String url1 : urls) {
            if (url.contains(url1)) {
                //白名单放行
                filterChain.doFilter(servletRequest, servletResponse);
                return ;
            }
        }

        //获取session
        HttpSession session = request.getSession();
        Object user = (User) session.getAttribute("user");

        //判断用户是否为空
        if (user == null) {
            //没有登录
            request.setAttribute("msg", "还没登录呢！");
            request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        } else {
            //已登录
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
