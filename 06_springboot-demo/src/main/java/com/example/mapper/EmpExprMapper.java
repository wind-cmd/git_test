package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    // 添加工作经历
    void add(List<EmpExpr> exprList);

    // 删除员工工作经历
    void deleteByEmpIds(List<Integer> ids);

    // 根据员工id查询工作经历
    @Select("select * from emp_expr where emp_id = #{id}")
    List<EmpExpr> selectByEmpId(Integer id);

}
