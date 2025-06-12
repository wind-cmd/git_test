package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pojo.Dept;

@Service
public interface DeptService {

    List<Dept> selectAll();

    void deleteById(Integer id);

    void addDept(Dept dept);

    Dept selectById(Integer id);

    void updateById(Dept dept);

}
