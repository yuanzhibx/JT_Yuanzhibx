package com.jt.aop;

import com.jt.anno.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * 拦截被 @CacheFind 标识的方法, 之后利用 aop 进行缓存的控制
 *
 * @author Yuanzhibx
 * @Date 2020-07-18
 */
@Component
@Aspect
public class CacheAOP {

    @Autowired(required = false)
    private Jedis jedis;

    /**
     * 拦截被 @CacheFind 标识的方法, 之后利用 aop 进行缓存的控制
     * 通知方法: 环绕通知
     * 实现步骤:
     *          1. 准备查询 redis 的 key     ITEM_CAT_LIST::第一个参数
     *          2. 动态获取注解语法           @annotation(cacheFind)
     *              拦截指定注解类型的注解并且将注解对象当做参数传递
     */
    @SuppressWarnings("unchecked")
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
        // 获取注解中的 key
        String key = cacheFind.key();
        // 动态获取第一个参数当做 key
        Object firstArg = joinPoint.getArgs()[0].toString();
        key += "::" + firstArg;
        Object result = null;
        // 根据 key 查询 redis
        if (jedis.exists(key)) {
            String json = jedis.get(key);
            // 获取返回值类型
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            result = ObjectMapperUtil.toObject(json, methodSignature.getReturnType());
        } else {
            // key 不存在, 第一次查询数据库
            try {
                result = joinPoint.proceed();
                // 将数据保存到 redis 中
                String json = ObjectMapperUtil.toJson(result);
                int secondes = cacheFind.secondes();
                if (secondes > 0) {
                    jedis.setex(key, secondes, json);
                } else {
                    jedis.set(key, json);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new RuntimeException(throwable);
            }
        }
        return result;
    }

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
//    /**
//     * 业务需求: 要求拦截 ItemCatServiceImpl 类中的业务
//     * @Pointcut("bean(itemServiceImpl)")  按类匹配, 控制粒度较粗
//     * @Pointcut("within(com.jt.service..*)")
//     */
////    @Pointcut("bean(itemServiceImpl)")
////    @Pointcut("within(com.jt.service..*)")
//    @Pointcut("execution(* com.jt.service..*.*(..))")
//    public void pointCut() {
//
//    }
//
//    @Before("pointCut()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("前置通知~~~");
//        String typeName = joinPoint.getSignature().getDeclaringTypeName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] objs = joinPoint.getArgs();
//        Object target = joinPoint.getTarget();
//        System.out.println("方法全路径为" + typeName + methodName);
//        System.out.println("方法参数" + objs);
//        System.out.println("目标对象" + target);
//    }
//
//    /**
//     * 环绕通知可以控制目标方法执行
//     * @param joinPoint
//     * @return
//     */
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        System.out.println("环绕通知开始~~~");
//        try {
//            Object result = joinPoint.proceed();
//            System.out.println("环绕通知结束~~~");
//            return result;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            throw new RuntimeException();
//        }
//    }

}
