package com.example.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.ClazzMapper;
import com.example.mapper.EmpMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.ClazzData;
import com.example.pojo.JobData;
import com.example.service.ReportService;
import com.example.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    private final EmpMapper empMapper;
    private final StudentMapper studentMapper;
    private final ClazzMapper clazzMapper;

    @Autowired
    public ReportServiceImpl(EmpMapper empMapper, StudentMapper studentMapper, ClazzMapper clazzMapper) {
        this.empMapper = empMapper;
        this.studentMapper = studentMapper;
        this.clazzMapper = clazzMapper;
    }

    @Override
    public JobData empJobData() {
        List<Map<String, Object>> list = empMapper.empJobData();
        List<Object> joblist = list.stream().map(dataMap -> dataMap.get("job")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("data")).toList();

        return new JobData(joblist, datalist);
    }

    @Override
    public List<Map<String, Object>> empGenderData() {
        return empMapper.empGenderData();
    }

    // 班级人数
    @Override
    public ClazzData studentCountData() {
        List<Map<String, Object>> list = clazzMapper.studentCountData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("studentData")).toList();
        return new ClazzData(clazzList, datalist);
    }

    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return studentMapper.studentDegreeData();
    }

}
