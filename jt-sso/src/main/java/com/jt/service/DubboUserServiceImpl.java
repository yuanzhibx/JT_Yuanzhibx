package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.UUID;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
@Service
public class DubboUserServiceImpl implements DubboUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveUser(User user) {
        // 将数据进行加密处理
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
        userMapper.insert(user);
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return ticket 用户密钥
     */
    @Override
    public String doLogin(User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        // 校验数据库密码是否正确
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        User userDB = userMapper.selectOne(queryWrapper);
        if (userDB == null) {
            return null;
        }
        // 用户名密码正确, 开始单点登录
        String ticket = UUID.randomUUID().toString().replace("-", "");
        // 防止涉密信息泄露
        userDB.setPassword("NullPointer");
        String value = ObjectMapperUtil.toJson(userDB);
        jedisCluster.setex(ticket, 7*24*3600, value);
        return ticket;
    }

}
