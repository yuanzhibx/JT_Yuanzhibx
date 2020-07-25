package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

/**
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 检验用户信息是否存在
     *
     * @param param 需要校验的数据
     * @param type  校验的类型
     * @return JSONP 对象
     */
    @RequestMapping("/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback) {
        boolean flag = userService.checkUser(param, type);
        SysResult sysResult = SysResult.success(flag);
        return new JSONPObject(callback, sysResult);
    }

    /**
     * 利用 JSONP 获取用户信息
     *
     * @param callback
     * @param ticket
     * @return
     */
    @RequestMapping("/query/{ticket}")
    public JSONPObject findUserByTicket(String callback, @PathVariable String ticket) {
        if (jedisCluster.exists(ticket)) {
            String data = jedisCluster.get(ticket);
            System.out.println(data);
            SysResult sysResult = SysResult.success(data);
            return new JSONPObject(callback, sysResult);
        } else {
            SysResult sysResult = SysResult.fail();
            return new JSONPObject(callback, sysResult);
        }
    }

}
