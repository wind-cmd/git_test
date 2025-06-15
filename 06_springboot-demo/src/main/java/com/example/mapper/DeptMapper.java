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

    /**
     * 查询全部部门数据
     * 
     * @return
     */
    @Select("select * from dept order by update_time desc")
    public List<Dept> selectAll();

    /**
     * 根据id删除部门数据
     * 
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    /**
     * 新增部门
     * 
     * @param dept
     */
    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    public void addDept(Dept dept);

    /**
     * 根据id查询部门
     * 
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id} order by update_time desc")
    public Dept selectById(Integer id);

    /**
     * 根据id修改部门
     * 
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    public void updateById(Dept dept);
}
