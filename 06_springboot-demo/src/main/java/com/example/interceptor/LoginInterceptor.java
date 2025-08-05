package com.example.interceptor;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.utils.JWTUtils;
import com.example.utils.TreadLocalUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 拦截方法调用之前调用
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {

        // 1.获取请求路径
        String path = req.getServletPath();
        log.info("请求方法：{}，请求路径：{}", req.getMethod(), path);
        // 2.判断是否为登录请求路径，如果是，放行
        // if (path.equals("/login")) {
        // return true;
        // }
        // 3.判断是否有token
        String token = req.getHeader("token");
        // 4.判断token是否为空
        if (token == null || token.isEmpty()) {
            log.info("token为空，未登录");
            res.setStatus(401);
            return false;
        }
        // 5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            Map<String, Object> claimsMap = JWTUtils.parseToken(token);
            // 获取token中的用户id
            Integer id = (Integer) claimsMap.get("id");
            TreadLocalUtils.setCurrentId(id);
            log.info("token合法，存入TreadLocal，用户id：{}", id);
        } catch (Exception e) {
            log.info("token解析失败，未登录");
            res.setStatus(401);
            return false;
        }
        // 6.放行
        log.info("token合法，放行");
        return true;
    }

    // 拦截方法调用之后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
    }

    // 在视图渲染之后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        TreadLocalUtils.removeCurrentId();
        log.info("TreadLocal中的用户id移除");
    }

}
