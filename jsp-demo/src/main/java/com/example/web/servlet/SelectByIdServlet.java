package com.example.web.servlet;

import com.example.pojo.Brand;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/SelectById")
public class SelectByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //获取service对象
        BrandService brandService = new BrandService();
        //查询数据
        Brand brand =  brandService.selectById(id);
        //存储到request对象
        req.setAttribute("brand", brand);
        //请求转发
        req.getRequestDispatcher("/updateBrand.jsp").forward(req, resp);
    }
}
