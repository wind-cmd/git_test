package com.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

    @Autowired
    public EmpServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    // public PageResult<Emp> page(Integer page, Integer pageSize, String name,
    // String gender, LocalDate begin,
    // LocalDate end) {
    // Long total = empMapper.count();
    // Integer start = (page - 1) * pageSize;
    // List<Emp> rows = empMapper.page(start, pageSize, name, gender, begin, end);
    // }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) rows;
        Long total = p.getTotal();

        logger.info("员工查询结果: 总记录数 = {}, 当前页数据 = {}", total, rows);

        PageResult<Emp> pageResult = new PageResult<>(total, rows);
        return pageResult;
    }
}
