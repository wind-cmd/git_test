package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;

@RestController
@RequestMapping("/emps")
public class EmpController {

    private final EmpService empService;
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    /*
     * 分页查询员工
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        logger.info("员工查询参数{}" + empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    /*
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        logger.info("新增员工{}" + emp);
        empService.save(emp);
        return Result.success();
    }
}
