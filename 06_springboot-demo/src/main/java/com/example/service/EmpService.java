package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;

public interface EmpService {

    //手动分页查询
    // PageResult<Emp> page(Integer page, Integer pageSize , String name, String
    // gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);
}
