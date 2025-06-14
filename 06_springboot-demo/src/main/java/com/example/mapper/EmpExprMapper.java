package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    //添加工作经历
    void add(List<EmpExpr> exprList);

}
