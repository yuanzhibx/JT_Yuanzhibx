package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.service.DubboUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
