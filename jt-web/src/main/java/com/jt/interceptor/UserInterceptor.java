package com.jt.interceptor;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
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
     * 用户已登录, 则放行, 反之则拦截不允许访问权限相关业务
     *
     * @return true: 表示放行  false: 表示拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 获取 Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    String ticket = cookie.getValue();
                    //2. redis 中是否有该记录, 有则放行
                    if (jedisCluster.exists(ticket)) {
                        //3. 动态获取用户 json 信息
                        String userJson = jedisCluster.get(ticket);
                        User user = ObjectMapperUtil.toObject(userJson, User.class);
                        // 利用 request 对象传递用户信息
                        request.setAttribute("JT_USER", user);
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
