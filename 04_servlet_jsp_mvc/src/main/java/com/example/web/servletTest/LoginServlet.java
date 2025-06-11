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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    /**
     * 登录页面,和login.html配套演示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接受学号，密码
        String userno = request.getParameter("userno");
        String password = request.getParameter("password");

        //获取sqlsession对象
        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //查询用户是否存在，返回user对象
            UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
            User user = userMapper.login(userno, password);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // 验证用户名和密码是否正确
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