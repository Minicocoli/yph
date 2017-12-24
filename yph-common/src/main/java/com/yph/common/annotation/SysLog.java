package com.yph.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志 注解
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/22
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
