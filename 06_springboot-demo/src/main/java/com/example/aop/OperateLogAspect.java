package com.example.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.TreadLocalUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
// 日志切面类
public class OperateLogAspect {

    private final OperateLogMapper operateLogMapper;

    @Autowired
    public OperateLogAspect(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    // 环绕通知
    // 切入点
    @Around("@annotation(com.example.anno.LogAnnotation)")
    // 通知
    public Object logOperate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long start = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 结束时间
        long end = System.currentTimeMillis();
        // 执行时间
        long costTime = end - start;

        // 操作日志封装
        OperateLog operateLog = new OperateLog();
        // 操作人ID
        operateLog.setOperateEmpId(getOperatorId());
        // 操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        // 操作类
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        // 操作方法
        operateLog.setMethodName(joinPoint.getSignature().getName());
        // 操作参数
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        // 操作结果
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        // 操作耗时
        operateLog.setCostTime(costTime);

        // 操作日志存储
        log.info("操作日志：{}", operateLog);
        operateLogMapper.insertOperateLog(operateLog);
        return result;
    }

    // 获取操作人ID
    private Integer getOperatorId() {
        // TODO: 获取操作人ID的逻辑
        return TreadLocalUtils.getCurrentId();
    }

}
