package com.jt.controller;

import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getMsg")
    public String getMsg() {
        return "jt-sso 单点登录系统搭建 DEMO --Yuanzhibx";
    }

}
