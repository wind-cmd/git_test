package com.example.web.servlet;

import com.example.pojo.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取学号密码
        String userno  = req.getParameter("userno");
        String password = req.getParameter("password");

        //获取记住我复选框
        String remember = req.getParameter("remember");

        //调用service
        User user = userService.login(userno, password);

        //判断
        if(user != null) {
            //登陆成功，重定向到SelectAll，查询所有brand

            //判断用户是否勾选记住我
            if("1".equals(remember)) {
                //创建Cookie
                Cookie c_userno = new Cookie("userno", userno);
                Cookie c_password = new Cookie("password", password);

                //设置生效时间
                c_userno.setMaxAge(3600*24);
                c_password.setMaxAge(3600*24);

                //发送cookie
                resp.addCookie(c_userno);
                resp.addCookie(c_password);
            }
            //登录成功后的user存储到session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+"/SelectAll");
        }else{
            //登录失败

            //失败提示信息存储到request
            req.setAttribute("msg", "学号或密码错误");
            //请求转发
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
