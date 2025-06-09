package com.example.web;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/CheckCode")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取输出流
        ServletOutputStream outputStream =  resp.getOutputStream();
//        FileOutputStream outputStream = new FileOutputStream(new File("D:/captcha.png"));
        //验证码对象
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        //设置验证码样式
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        //生成验证码
        specCaptcha.out(outputStream);
        //存入session
        HttpSession session = req.getSession();
        session.setAttribute("checkCode", specCaptcha.text().toLowerCase());

        System.out.println(specCaptcha.text().toLowerCase());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
