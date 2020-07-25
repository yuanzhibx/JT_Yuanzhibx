package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面跳转
 *
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference(check = false)
    private DubboUserService userService;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 注册页面跳转
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 登录页面跳转
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 用户注册
     * http://www.jt.com/user/doRegister
     *
     * @param user 用户信息
     * @return SysResult 对象
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) {
        userService.saveUser(user);
        return SysResult.success();
    }

    /**
     * 单点登录
     * http://www.jt.com/user/doLogin?r=0.3626584850149306
     * <p>
     * Cookie:  Cookie 默认条件下,只能在自己的网址下进行展现
     * Domain: 指定域名可以实现 Cookie 的共享
     * Path: 指定路径下, 才能获取 Cookie
     *
     * @param user 用户信息
     * @return SysResult 对象
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(User user, HttpServletResponse response) {
        String ticket = userService.doLogin(user);
        if (StringUtils.isEmpty(ticket)) {
            return SysResult.fail();
        }
        // 使用 Cookie 实现数据存储
        Cookie cookie = new Cookie("JT_TICKET", ticket);
        cookie.setDomain("jt.com");
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        //将 Cookie 添加到客户端
        response.addCookie(cookie);
        return SysResult.success();
    }

    /**
     * 用户退出登录
     * url: http://www.jt.com/user/logout.html
     *
     * 0. 获取 cookie 中的数据 NAME = JT_TICKET
     * 1. 删除 redis 中的数据
     * 2. 删除 cookie 记录
     *
     * @return String
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equalsIgnoreCase(cookie.getName())) {
                    String ticket = cookie.getValue();
                    // 删除 redis 数据
                    jedisCluster.del(ticket);
                    // 删除 cookie 数据
                    cookie.setDomain("jt.com");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/";
    }

}
