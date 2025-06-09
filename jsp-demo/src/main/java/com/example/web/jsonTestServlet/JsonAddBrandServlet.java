package com.example.web.jsonTestServlet;

import com.alibaba.fastjson.JSON;
import com.example.pojo.Brand;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/json/addBrand")
public class JsonAddBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收数据
        BufferedReader reader = req.getReader();
        String json = reader.readLine();


        Brand brand = JSON.parseObject(json, Brand.class);

        BrandService brandService = new BrandService();
        brandService.addBrand(brand);


        //响应成功
        resp.getWriter().print("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
