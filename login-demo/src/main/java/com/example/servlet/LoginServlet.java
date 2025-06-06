package com.example.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.utils.MybatisUtils;

@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 处理登录逻辑
        String userno = request.getParameter("userno");
        String password = request.getParameter("password");
        logger.debug(password, userno);

        // 验证用户名和密码是否正确
        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
            User user = userMapper.login(userno, password);
            logger.debug(user);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (user != null) {
                // 登录成功，跳转到主页或其他页面
                out.write("登录成功！");
                out.println("<script>alert('"+user.getUserName()+"登录成功！');</script>");
            } else {
                // 登录失败，跳转到登录页面并显示错误信息
                out.write("登录失败！");
                out.println("<script>alert('登录失败！');</script>");
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("服务器内部错误");
        } finally {
            sqlSession.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 复用 doGet 方法的逻辑
        doGet(request, response);
    }
}