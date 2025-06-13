package com.example.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpQueryParam {
    private Integer page;//页码
    private Integer pageSize;//每页展示记录数
    private String name;//姓名
    private Integer gender;//性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//入职开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//入职结束时间

}
