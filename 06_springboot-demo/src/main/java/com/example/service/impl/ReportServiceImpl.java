package com.example.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.EmpMapper;
import com.example.pojo.JobData;
import com.example.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    private final EmpMapper empMapper;

    @Autowired
    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
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

}
