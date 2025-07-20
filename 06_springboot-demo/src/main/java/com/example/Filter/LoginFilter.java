package com.example.Filter;

import java.io.IOException;

import com.example.utils.JWTUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 转换请求响应对象类型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 1.获取请求路径
        String path = req.getServletPath();
        log.info("请求路径：{}", path);
        // 2.判断是否为登录请求路径，如果是，放行
        if (path.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        // 3.判断是否有token
        String token = req.getHeader("token");
        // 4.判断token是否为空
        if (token == null || token.isEmpty()) {
            log.info("token为空，未登录");
            res.setStatus(401);
            return;
        }
        // 5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JWTUtils.parseToken(token);
        } catch (Exception e) {
            log.info("token解析失败，未登录");
            res.setStatus(401);
            return;
        }
        // 6.放行
        log.info("token合法，放行");
        chain.doFilter(request, response);
    }

}
