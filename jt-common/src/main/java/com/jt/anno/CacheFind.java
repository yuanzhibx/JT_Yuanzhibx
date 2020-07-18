package com.jt.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yuanzhibx
 * @Date 2020-07-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheFind {

    /**
     * 识别存入 redis 中的 key 的前缀
     */
    public String key();

    /**
     * 保存时间
     */
    public int secondes() default 0;

}
