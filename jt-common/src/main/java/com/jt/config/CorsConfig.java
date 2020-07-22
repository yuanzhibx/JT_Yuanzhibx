package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类似于 web 项目中的 web.xml 文件
 *
 * @author Yuanzhibx
 * @Date 2020-07-22
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 配置后端服务器可以跨域的清单
     * addMapping(): /* 匹配一级目录   /** 匹配多级目录
     * allowedOrigins(): 配置源  * 统配
     * allowedMethods(): 允许的请求类型
     * allowCredentials(): 是否允许携带 Cookie
     * maxAge(): 请求时间
     *
     * @param registry 什么请求可以跨域  [ /web/** /web/* ]
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true)
                .maxAge(1000);
    }

}
