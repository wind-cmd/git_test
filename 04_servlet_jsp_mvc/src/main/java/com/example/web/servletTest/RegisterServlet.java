package com.example.web.servletTest;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.util.MybatisUtils;
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

    /**
     * 注册页面和register.html配合演示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受用户参数
        String userno = req.getParameter("userno");
        String password = req.getParameter("password");

        //获取sqlsession对象
        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //查询用户是否注册，返回user对象
        UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUserNo(userno);
        //未注册
        if (user == null) {
            //添加新用户
            userMapper.addUserNo(userno, password);
            sqlSession.commit();
            sqlSession.close();
        }else{//已注册
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("用户名已存在");
        }
    }
}
