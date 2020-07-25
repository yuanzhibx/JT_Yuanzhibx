package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
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
     *
     * Cookie:  Cookie 默认条件下,只能在自己的网址下进行展现
     *      Domain: 指定域名可以实现 Cookie 的共享
     *      Path: 指定路径下, 才能获取 Cookie
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
        cookie.setMaxAge(7*24*60*60);
        //将 Cookie 添加到客户端
        response.addCookie(cookie);
        return SysResult.success();
    }

}
