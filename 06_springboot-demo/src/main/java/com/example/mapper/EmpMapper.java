package com.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {

    //条件查询员工
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 统计员工数量
     * 
     * @return
     */
    // @Select("select count(*) from emp e LEFT JOIN  dept d on e.dept_id = d.id")
    // public long count();

    /*
     * 分页查询所有员工
     */
    // @Select("select e.* ,d.name as deptName from emp e LEFT JOIN  dept d on e.dept_id = d.id " +
    //         "order by e.update_time desc" +
    //         " limit #{start},#{pageSize}")
    // List<Emp> page(Integer start, Integer pageSize);

    
}
