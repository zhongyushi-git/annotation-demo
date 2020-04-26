package com.zys.springboot.annotationdemo.config;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String title() default "";//模块名称
    String describe() default "";//描述
}