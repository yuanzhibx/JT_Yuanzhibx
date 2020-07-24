package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
@Service
public class DubboUserServiceImpl implements DubboUserService {

    @Autowired
    private UserMapper userMapper;

}
