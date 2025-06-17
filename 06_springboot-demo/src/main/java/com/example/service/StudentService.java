package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.pojo.PageResult;
import com.example.pojo.Student;

public interface StudentService {

    // 分页查询
    PageResult<Student> page(Integer page, Integer pageSize, Integer degree, Integer clazzId, String name);

    // 根据id查询
    Student selectById(Integer id);

    // 新增
    void add(Student student);

    // 批量删除
    void deleteByIds(List<Integer> ids);

    // 修改
    void update(Student student);

    //修改学生违规次数
    void updateViolationScore(Integer id, Integer score);

}
