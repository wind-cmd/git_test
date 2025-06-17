package com.example.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.example.mapper.ClazzMapper;
import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public PageResult<Clazz> page(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end) {

        PageHelper.startPage(page, pageSize);
        // 班级列表
        List<Clazz> rows = clazzMapper.page(name, begin, end);
        rows.forEach(clazz -> {
            LocalDate today = LocalDate.now();
            if (today.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (today.isAfter(clazz.getBeginDate()) || clazz.getEndDate().isAfter(today)) {
                clazz.setStatus("在读");
            } else if(today.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            }else{
                clazz.setStatus("其他");
            }
        });
        Page<Clazz> p = (Page<Clazz>) rows;
        // 数据条数
        Long total = p.getTotal();
        PageResult<Clazz> pageResult = new PageResult<>(total, rows);
        return pageResult;
    }

    //删除班级
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    //添加班级
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    //通过id查询班级
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    //修改班级
    @Override
    public void updateById(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.list();
    }
}
