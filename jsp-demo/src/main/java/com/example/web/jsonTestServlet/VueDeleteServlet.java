package com.example.web.jsonTestServlet;

import com.alibaba.fastjson.JSON;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/json/deleteBrand")
public class VueDeleteServlet extends HttpServlet {
    BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        //解析json
        List<Integer> ids= JSON.parseArray(json, Integer.class);

        brandService.deleteBrands(ids);

        //响应成功
        resp.getWriter().print("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
