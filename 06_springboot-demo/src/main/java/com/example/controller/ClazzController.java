package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ClazzService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    private final ClazzService clazzService;

    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    // 分页查询
    @GetMapping
    public Result page(@RequestParam() Integer page,
            @RequestParam() Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end) {

        log.info("分页查询，参数：page={},pageSize={},name={},begin={},end={}", page, pageSize, name, begin, end);
        PageResult<Clazz> pageResult = clazzService.page(page, pageSize, name, begin, end);

        return Result.success(pageResult);
    }
    //查询所有班级
    @GetMapping("/list")
    public Result list(){
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }

    // 删除班级
    @DeleteMapping("/{id}")
    @LogAnnotation
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    // 添加班级
    @PostMapping
    @LogAnnotation
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    // 通过ID查询
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    // 修改班级信息
    @PutMapping
    @LogAnnotation
    public Result updateById(@RequestBody Clazz clazz) {
        log.info("修改班级信息：{}", clazz);
        clazzService.updateById(clazz);
        return Result.success();
    }

}
