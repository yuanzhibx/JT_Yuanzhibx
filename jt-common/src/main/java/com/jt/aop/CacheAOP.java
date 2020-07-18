package com.jt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP Test
 * 1. 将对象交给 Spring 容器管理 @Component
 * 2. 定义 AOP 切面 @Aspect
 *
 * 切面 = 切入点表达式 + 通知方法
 *      切入点表达式: @Pointcut 切入点表达式可以理解为一个 if 判断, 只有满足条件, 才会执行
 *
 * JoinPoint joinPoint 方法执行时被切入点表达式匹配, 该方法执行称为连接点
 *
 * @author Yuanzhibx
 * @Date 2020-07-18
 */
@Component
@Aspect
public class CacheAOP {

    /**
     * 业务需求: 要求拦截 ItemCatServiceImpl 类中的业务
     * @Pointcut("bean(itemServiceImpl)")  按类匹配, 控制粒度较粗
     * @Pointcut("within(com.jt.service..*)")
     */
//    @Pointcut("bean(itemServiceImpl)")
    @Pointcut("within(com.jt.service..*)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知~~~");
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] objs = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        System.out.println("方法全路径为" + typeName + methodName);
        System.out.println("方法参数" + objs);
        System.out.println("目标对象" + target);
    }

}
