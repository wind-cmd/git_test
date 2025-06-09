package com.example.web;

import com.example.pojo.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String userno = req.getParameter("userno");
        String password = req.getParameter("password");
        //获取输入的验证码
        String checkCode = req.getParameter("checkCode").toLowerCase();

        //获取session
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCode");
        //判断验证码是否正确
        if(!checkCode.equals(checkCodeSession.toLowerCase())){
            //输入不一致
            req.setAttribute("register_msg", "验证码错误！");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        //封装对象
        User user = new User();
        user.setPassWord(password);
        user.setUserNo(userno);

        //调用service
        boolean flag =  userService.register(user);
        //判断
        if(flag){
            //注册成功，跳转登录页面
            req.setAttribute("register_msg", "注册成功，请登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else{
            req.setAttribute("register_msg", "学号已存在！");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

    }
}
