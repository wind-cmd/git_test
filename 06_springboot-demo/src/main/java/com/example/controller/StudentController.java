package com.example.controller;

import java.util.List;

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

import com.example.anno.LogAnnotation;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.service.StudentService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 分页查询
    @GetMapping
    public Result page(@RequestParam() Integer page,
            @RequestParam() Integer pageSize,
            @RequestParam(required = false) Integer degree,
            @RequestParam(required = false) Integer clazzId,
            @RequestParam(required = false) String name) {
        log.info("分页查询，参数：{}, {}, {}, {}, {}", page, pageSize, degree, clazzId, name);
        PageResult<Student> pageResult = studentService.page(page, pageSize, degree, clazzId, name);
        return Result.success(pageResult);
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Result selectById(@PathVariable() Integer id) {
        Student student = studentService.selectById(id);
        return Result.success(student);
    }

    // 添加
    @PostMapping
    @LogAnnotation
    public Result add(@RequestBody Student student) {
        log.info("新增学生：{}", student);
        studentService.add(student);
        return Result.success();
    }

    // 删除多个
    @DeleteMapping("/{ids}")
    @LogAnnotation
    public Result deleteByIds(@PathVariable List<Integer> ids) throws Exception {
        log.info("删除员工：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    // 修改
    @PutMapping
    @LogAnnotation
    public Result update(@RequestBody Student student) {
        log.info("修改学生：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    @LogAnnotation
    public Result updateViolationScore(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("修改学生违规分数：{}", score);
        studentService.updateViolationScore(id, score);
        return Result.success();
    }

}
