package com.yph.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/22
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.yph.common.annotation.SysLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        log.info("【 系统日志 】 ------------> 保存日志记录 。");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();


        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();


        // 保存 日志


    }

}