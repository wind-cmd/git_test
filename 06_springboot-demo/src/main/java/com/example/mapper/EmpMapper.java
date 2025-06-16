package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

        // 根据id查询员工
        @Select("select id, username, name, phone, gender, image, job, salary, entry_date, dept_id, create_time, update_time"
                        + " from emp where id = #{id}")
        Emp selectById(Integer id);

        // 根据id数组查询员工图片路径
        List<Emp> selectByIds(List<Integer> ids);

        // 根据id修改员工信息
        void updateById(Emp emp);

        //统计各个职位的员工人数
        List<Map<String, Object>> empJobData();

        //统计男女员工人数
        List<Map<String, Object>> empGenderData();

}
