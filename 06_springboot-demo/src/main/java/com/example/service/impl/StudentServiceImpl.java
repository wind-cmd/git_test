package com.example.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    // 分页查询
    @Override
    public PageResult<Student> page(Integer page, Integer pageSize, Integer degree, Integer clazzId, String name) {

        PageHelper.startPage(page, pageSize);
        List<Student> students = studentMapper.page(degree, clazzId, name);
        Page<Student> p = (Page<Student>) students;
        PageResult<Student> pageResult = new PageResult<>(p.getTotal(), students);
        return pageResult;
    }
    // 根据id查询

    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectById(id);
    }

    // 添加
    @Override
    public void add(Student student) {
        log.info("新增学生：{}", student);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    // 修改
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    // 修改学生违规次数
    @Override
    public void updateViolationScore(Integer id, Integer score) {
        studentMapper.updateViolationScore(id, score);
    }
}
