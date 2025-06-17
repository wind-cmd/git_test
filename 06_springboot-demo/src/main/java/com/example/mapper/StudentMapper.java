package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.pojo.Student;

@Mapper
public interface StudentMapper {


    // 分页查询
    List<Student> page(Integer degree, Integer clazzId, String name);

    // 根据id查询
    @Select("select s.*,c.name as clazzName from student s left join clazz c on s.clazz_id = c.id where s.id = #{id}")
    Student selectById(Integer id);

    // 新增
    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time) values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void add(Student student);

    // 批量删除
    void deleteByIds(List<Integer> ids);

    // 修改
    @Update("update student set name = #{name},no = #{no},gender = #{gender},phone = #{phone},id_card = #{idCard},is_college = #{isCollege},address = #{address},degree = #{degree},graduation_date = #{graduationDate},clazz_id = #{clazzId},update_time = #{updateTime},violation_count = #{violationCount}, violation_score = #{violationScore} where id = #{id}")
    void update(Student student);

    //修改学生违规次数
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score} where id = #{id}")
    void updateViolationScore(Integer id, Integer score);

    // 统计不同学历人数
    List<Map<String, Object>> studentDegreeData();

}