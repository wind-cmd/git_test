package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.pojo.Dept;

@Mapper
public interface DeptMapper {

    @Select("select * from dept order by update_time desc")
    public List<Dept> selectAll();

    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    public void addDept(Dept dept);

    @Select("select * from dept where id = #{id} order by update_time desc")
    public Dept selectById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    public void updateById(Dept dept);
}
