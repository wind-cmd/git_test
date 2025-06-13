package com.example.service;

import java.util.List;

import com.example.pojo.Dept;

public interface DeptService {

    List<Dept> selectAll();

    void deleteById(Integer id);

    void addDept(Dept dept);

    Dept selectById(Integer id);

    void updateById(Dept dept);

}
