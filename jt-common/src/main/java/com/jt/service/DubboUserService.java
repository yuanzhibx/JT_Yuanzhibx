package com.jt.service;

import com.jt.pojo.User;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboUserService {

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return ticket 用户密钥
     */
    String doLogin(User user);
}
