package com.example.servlet;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受用户参数
        String userno = req.getParameter("userno");
        String password = req.getParameter("password");

        // 验证用户名和密码是否正确
        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUserNo(userno);
        if (user == null) {
            userMapper.addUserNo(userno, password);
            sqlSession.commit();
            sqlSession.close();
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("用户名已存在");
        }
    }
}
