package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.pojo.Clazz;
import com.example.pojo.PageResult;

public interface ClazzService {

    // 分页查询班级信息
    PageResult<Clazz> page(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end);

    // 查询所有班级
    List<Clazz> list();

    // 删除班级
    void deleteById(Integer id);

    // 新增班级
    void add(Clazz clazz);

    // 通过id查询班级
    Clazz selectById(Integer id);

    // 修改班级
    void updateById(Clazz clazz);

}
