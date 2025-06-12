package com.example.springboot_demo;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//标识为请求处理类
@RestController
public class HelloController {

    //标识请求路径
    @Test
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("Hello," + name);
        return "Hello," + name;
    }

}
