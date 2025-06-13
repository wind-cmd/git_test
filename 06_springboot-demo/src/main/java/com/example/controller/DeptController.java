package com.example.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;

@RestController
@RequestMapping("/depts")
public class DeptController {
    private final DeptService deptService;
    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping
    public Result selectAll() {
        return Result.success(deptService.selectAll());
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        return Result.success(deptService.selectById(id));
    }

    @DeleteMapping
    public Result deleteById(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result updateById(@RequestBody Dept dept) {
        logger.info("Received update request for dept: {}", dept);
        deptService.updateById(dept);
        return Result.success();
    }
}
