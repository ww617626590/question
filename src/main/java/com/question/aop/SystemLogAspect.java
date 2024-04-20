package com.question.aop;

import com.question.domain.User;
import com.question.domain.UserActions;

import com.question.mapper.UserActionsMapper;
import com.question.service.UserActionsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: xxx
 * @date: 2024/4/12 20:01
 * @description:
 */
@Aspect
@Component
public class SystemLogAspect {
    @Autowired
    private UserActionsService userActionsService;


    /**
     * 注解Pointcut切入点
     * 定义出一个或一组方法，当执行这些方法时可产生通知
     * 指向你的切面类方法
     * 由于这里使用了自定义注解所以指向你的自定义注解
     */
    @Pointcut("@annotation(com.question.aop.SystemCrmlog)")
    public void crmAspect() {
    }


    /**
     * 返回后通知（@AfterReturning）：在某连接点（joinpoint）
     * 正常完成后执行的通知：例如，一个方法没有抛出任何异常，正常返回
     * 方法执行完毕之后
     * 注意在这里不能使用ProceedingJoinPoint
     * 不然会报错ProceedingJoinPoint is only supported for around advice
     * crmAspect()指向需要控制的方法
     *  returning  注解返回值
     * @param joinPoint
     * @param returnValue  返回值
     * @throws Exception
     */
    @AfterReturning(value = "crmAspect()", returning = "returnValue")
    public void doCrmLog(JoinPoint joinPoint, Object returnValue) throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new Exception("用户未登录");
        }
        String description = getServiceMethodDescription(joinPoint);
        UserActions userActions = new UserActions();
        userActions.setCreatedBy(user.getId());
        userActions.setCreatedTime(new Date());
        userActions.setAction(description);
        //将数据保存到数据库
        userActionsService.save(userActions);
    }


    /**
     *获取自定义注解里的日志描述
     * @param joinPoint
     * @return 返回注解里面的日志描述
     * @throws Exception
     */
    private String getServiceMethodDescription(JoinPoint joinPoint)
            throws Exception {
        //类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] arguments = joinPoint.getArgs();
        //通过反射获取示例对象
        Class targetClass = Class.forName(targetName);
        //通过实例对象方法数组
        Method[] methods = targetClass.getMethods();
        String description = "";
        for(Method method : methods) {
            //判断方法名是不是一样
            if(method.getName().equals(methodName)) {
                //对比参数数组的长度
                Class[] clazzs = method.getParameterTypes();
                if(clazzs.length == arguments.length) {
                    //获取注解里的日志信息
                    description = method.getAnnotation(SystemCrmlog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
