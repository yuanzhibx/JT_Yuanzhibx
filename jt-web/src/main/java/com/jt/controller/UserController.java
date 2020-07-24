package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
