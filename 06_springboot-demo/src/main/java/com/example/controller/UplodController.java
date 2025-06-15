package com.example.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UplodController {
    private final AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    public UplodController(AliyunOSSOperator aliyunOSSOperator) {
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}", file);
        if (!file.isEmpty()) {
            // 获取文件类型
            String lastName = file.getOriginalFilename().split("\\.")[1];
            // 过UUID生成新的文件名，"ac6792cc2d81436fa7dd2eaae869fe76.png"
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + "." + lastName;
            // 文件上传到OSS，并获取访问url
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            log.info("OSS访问地址：{}", url);
            return Result.success(url);
        }
        return Result.error("上传文件失败！");
    }

}
