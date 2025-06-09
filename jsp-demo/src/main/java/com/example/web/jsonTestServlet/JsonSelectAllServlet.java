package com.example.web.jsonTestServlet;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Brand;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/json/selectAll")
public class JsonSelectAllServlet extends HttpServlet {

    BrandService brandService = new BrandService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<Brand> brands = brandService.selectAllBrand();
        String jsonString = JSONObject.toJSONString(brands);

        // 响应输出
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}