package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态获取当前访问服务器的端口号信息
 *
 * @author Yuanzhibx
 * @Date 2020-07-13
 */
@RestController
public class MsgController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/getPort")
    public String getMsg() {
        return "您当前访问的服务器端口号为: [ " + port + " ]";
    }

}
