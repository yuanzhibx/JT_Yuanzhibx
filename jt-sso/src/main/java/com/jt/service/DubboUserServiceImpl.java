package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
@Service
public class DubboUserServiceImpl implements DubboUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
    @Override
    @Transactional
    public void saveUser(User user) {
        // 将数据进行加密处理
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
        userMapper.insert(user);
    }

}
