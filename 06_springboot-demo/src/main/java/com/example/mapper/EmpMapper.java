package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {

    // 条件查询员工
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 统计员工数量
     */
    // @Select("select count(*) from emp e LEFT JOIN dept d on e.dept_id = d.id")
    // public long count();

    /*
     * 分页查询所有员工
     */
    // @Select("select e.* ,d.name as deptName from emp e LEFT JOIN dept d on
    // e.dept_id = d.id " +
    // "order by e.update_time desc" +
    // " limit #{start},#{pageSize}")
    // List<Emp> page(Integer start, Integer pageSize);

    // 添加员工
    @Options(useGeneratedKeys = true, keyProperty = "id") // 返回主键id赋值给emp对象，可直接get方法获取。
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "
            + "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    // 批量删除员工
    void deleteByIds(List<Integer> ids);

}
