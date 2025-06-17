package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.pojo.ClazzData;
import com.example.pojo.JobData;

public interface ReportService {
    JobData empJobData();

    List<Map<String,Object>> empGenderData();

    ClazzData studentCountData();

    List<Map<String,Object>> studentDegreeData();

}
