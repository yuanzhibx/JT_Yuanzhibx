package com.jt.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现 JSONP 跨域访问
 *
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@RestController
public class JSONPController {

    /**
     * URL: http://manage.jt.com/web/testJSONP?callback=hello&_=1595323949195
     * @return 格式: callback(JSON)
     */
    @RequestMapping("/web/testJSONP")
    public JSONPObject jsonp(String callback) {
        // 准备返回的数据
        User user = new User();
        user.setId(10010L).setPassword("YuanzhibxPassword");
        return new JSONPObject(callback, user);
    }

}
