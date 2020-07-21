package com.jt.controller;

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
public class IndexController {

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
