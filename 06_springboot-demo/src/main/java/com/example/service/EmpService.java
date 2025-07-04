package com.example.service;

import java.util.List;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;

public interface EmpService {

    //手动分页查询
    // PageResult<Emp> page(Integer page, Integer pageSize , String name, String
    // gender, LocalDate begin, LocalDate end);

    //分页查询员工
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工
    void save(Emp emp) throws Exception;

    //删除多个员工
    void deleteByIds(List<Integer> ids) throws Exception;

    //根据id查询员工
    Emp selectById(Integer id);

    //修改员工信息
    void updateById(Emp emp);
}
