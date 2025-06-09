package com.example.mapper;

import com.example.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAllBrand();
    /**
     * 通过id查询
     */
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    /**
     * 添加
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void addBrand(Brand brand);

    /**
     * 修改
     */
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void updateBrand(Brand brand);

    /**
     * 通过id删除
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteBrand(int id);
}
