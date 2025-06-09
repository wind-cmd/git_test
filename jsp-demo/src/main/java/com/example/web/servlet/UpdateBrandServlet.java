package com.example.web.servlet;

import com.example.pojo.Brand;
import com.example.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/UpdateBrand")
public class UpdateBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id =   Integer.parseInt(req.getParameter("id"));
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        String description = req.getParameter("description");
        int status =  Integer.parseInt(req.getParameter("status"));
        int ordered = Integer.parseInt(req.getParameter("ordered"));
        //封装对象
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setDescription(description);
        brand.setStatus(status);
        brand.setOrdered(ordered);
        //更新数据库
        BrandService service = new BrandService();
        service.updateBrand(brand);
        //查询数据库最新数据
        List<Brand>brands =  service.selectAllBrand();
        //结果集，存储到request对象
        req.setAttribute("brands", brands);
        //请求转发
        req.getRequestDispatcher("/SelectAll").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
