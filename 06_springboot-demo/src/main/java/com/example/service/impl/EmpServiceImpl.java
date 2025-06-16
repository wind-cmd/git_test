package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import com.example.pojo.EmpLog;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.AliyunOSSOperator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;
    private final EmpLogService empLogService;
    private final AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprMapper, EmpLogService empLogService,
            AliyunOSSOperator aliyunOSSOperator) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.empLogService = empLogService;
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    // public PageResult<Emp> page(Integer page, Integer pageSize, String name,
    // String gender, LocalDate begin,
    // LocalDate end) {
    // Long total = empMapper.count();
    // Integer start = (page - 1) * pageSize;
    // List<Emp> rows = empMapper.page(start, pageSize, name, gender, begin, end);
    // }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);

        // 查询结果变为Page对象，获取总记录数
        Page<Emp> p = (Page<Emp>) rows;
        Long total = p.getTotal();

        // 封装结果返回
        PageResult<Emp> pageResult = new PageResult<>(total, rows);
        return pageResult;
    }

    /**
     * 设置事务，只要出现错误就会滚，不管是不是运行时异常。
     * 事务注解写在方法上，方法内操作要么全部执行，要么回滚。
     */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void save(Emp emp) throws Exception {

        try { // 1.存员工基本信息
              // 设置时间员工创建时间，修改时间
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.add(emp);

            // 获取工作经历
            List<EmpExpr> empExprs = emp.getExprList();
            if (!empExprs.isEmpty()) {
                // 遍历工作经历，
                empExprs.forEach(empExpr -> {
                    // 赋值empId，知道是哪个员工的经历
                    empExpr.setEmpId(emp.getId());
                });
                // 2.保存员工经历
                empExprMapper.add(emp.getExprList());
            }
        } finally {
            // 3.记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 删除员工，删除员工工作经历
     * 
     * @throws Exception
     */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void deleteByIds(List<Integer> ids) throws Exception {
        // 获取每个员工的OSS文件路径
        List<String> paths = empMapper.selectByIds(ids)
                .stream().map(Emp::getImage)
                .filter(image -> image != null && !image.isEmpty())
                .toList();
        log.info("要删除的文件目录：{}", paths);

        // 删除员工信息
        empMapper.deleteByIds(ids);

        // 删除OSS文件
        aliyunOSSOperator.delete(paths);
        // 删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    // 根据id查询员工
    @Override
    public Emp selectById(Integer id) {
        Emp emp = empMapper.selectById(id);
        emp.setExprList(empExprMapper.selectByEmpId(id));
        return emp;
    }

    // 根据id修改员工信息
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void updateById(Emp emp) {
        // 1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 2.修改员工工作经历
        // 先删除原来的，
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
        // 给工作经历赋值员工id，因为新增的员工id为空。
        List<EmpExpr> exprList = emp.getExprList();
        if (!exprList.isEmpty()) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
        }
        empExprMapper.add(emp.getExprList());
    }
}
