package com.question.aop;

import java.lang.annotation.*;

/**
 * ClassName Crmlog
 * AOP日志记录 自定义注解类
 * @author xxx
 * description 揭露用户操作
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemCrmlog {

    /**
     * 进行了什么操作
     */
    String description()  default "";

}
