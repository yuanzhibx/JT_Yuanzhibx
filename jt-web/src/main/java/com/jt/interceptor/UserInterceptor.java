package com.jt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 如果用户不登录, 则不允许访问权限相关业务
     *
     * @return true: 表示放行  false: 表示拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    // cookie 存在
                    String ticket = cookie.getValue();
                    // redis 中是否有该记录
                    if (jedisCluster.exists(ticket)) {
                        return true;
                    }
                }
            }
        }
        // 重定向到用户登录页面
        response.sendRedirect("/user/login.html");
        return false;
    }

}
