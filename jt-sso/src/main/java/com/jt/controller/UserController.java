package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param param 需要校验的数据
     * @param type  校验的类型
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback) {
        boolean flag = userService.checkUser(param, type);
        SysResult sysResult = SysResult.success(flag);
        return new JSONPObject(callback, sysResult);
    }

}
