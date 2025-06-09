package com.example.service;

import com.example.mapper.BrandMapper;
import com.example.pojo.Brand;
import com.example.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    public List<Brand> selectAllBrand() {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAllBrand();
        session.close();
        return brands;
    }

    /**
     * 通过id查询
     */
    public Brand selectById(int id) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(id);
        session.close();
        return brand;
    }
    /**
     * 添加
     */
    public void addBrand(Brand brand) {

        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);

        brandMapper.addBrand(brand);

        session.commit();
        session.close();
    }
    /**
     * 修改
     */
    public void updateBrand(Brand brand) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.updateBrand(brand);
        session.commit();
        session.close();

    }

    /**
     * 通过id删除
     */
    public void deleteBrand(int id) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.deleteBrand(id);
        session.commit();
        session.close();
    }
}
