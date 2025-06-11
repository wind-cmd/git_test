package com.example.web.jsonTestServlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Brand;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/json/selectAll")
public class JsonSelectAllServlet extends HttpServlet {

    BrandService brandService = new BrandService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //接收数据
        BufferedReader reader = req.getReader();
        String json = reader.readLine();

        Brand brand =  JSON.parseObject(json,Brand.class);
        List<Brand> brands = brandService.selectByForm(brand);
        String jsonString = JSONObject.toJSONString(brands);



        //与jsonTest配合，直接查询所有数据，没有筛选条件
//        List<Brand> brandss= brandService.selectAllBrand();

        System.out.println(jsonString);
        // 响应输出
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}