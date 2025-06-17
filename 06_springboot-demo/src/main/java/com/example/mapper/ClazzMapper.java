package com.example.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.pojo.Clazz;

@Mapper
public interface ClazzMapper {

    //条件查询所有班级
    List<Clazz> page(String name, LocalDate begin, LocalDate end);

    //删除班级
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    //新增班级
    @Insert("insert into clazz values(null,#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    //根据id查询班级
    @Select("select * from clazz where id=#{id}")
    Clazz selectById(Integer id);

    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateById(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> list();

    //统计各个班级的学生人数
    List<Map<String, Object>> studentCountData();

}
