package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Emp;
import com.example.pojo.LoginInfo;
import com.example.pojo.Result;
import com.example.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {

    private EmpService empService;

    @Autowired
    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    /* 登录接口 */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}", emp);
        LoginInfo e = empService.login(emp);
        if (e == null) {
            return Result.error("用户名或密码错误！");
        }
        return Result.success(e);
    }
}
