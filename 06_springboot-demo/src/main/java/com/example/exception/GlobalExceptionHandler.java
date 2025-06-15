package com.example.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.pojo.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("Exception occurred: ", e);
        return Result.error("出错了请联系管理员~");
    }

    @ExceptionHandler
    public Result DuplicateEntryException(DuplicateKeyException e) {
        log.error("Exception occurred: ", e);
        String errorMsg = e.getMessage();
        int i = errorMsg.indexOf("Duplicate entry");
        String msg = errorMsg.substring(i).split(" ")[2];
        if (msg.contains("'")) {
            msg = msg.substring(1, msg.length() - 1);
        }
        return Result.error(msg + " 已存在！");
    }
}
