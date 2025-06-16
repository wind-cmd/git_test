package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    private final EmpService empService;

    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    /*
     * 分页查询员工
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("员工查询参数{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        log.info("员工查询结果{}", pageResult);
        return Result.success(pageResult);
    }

    /*
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("新增员工{}" + emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     * @throws Exception 
     */

    @DeleteMapping
    public Result deleteByIds(@RequestParam List<Integer> ids) throws Exception {
        log.info("删除员工：{}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据id查询员工：{}", id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }

    // 修改员工信息
    @PutMapping
    public Result updateById(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.updateById(emp);
        return Result.success();
    }
}
