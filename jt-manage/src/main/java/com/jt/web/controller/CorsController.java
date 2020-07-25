package com.jt.web.controller;

import com.jt.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanzhibx
 * @Date 2020-07-22
 */
@RestController
public class CorsController {

    @RequestMapping("/web/testCors")
    public User testUser() {
        System.out.println("执行 testUser");
        return new User().setId(100L).setPassword("CORS PASSWORD");
    }
}
