package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private static Map<Integer, String> paramMap;

    static {
        Map<Integer, String> map = new HashMap<>();
        // 将 type 类型转化为具体的字段信息
        map.put(1, "username");
        map.put(2, "phone");
        map.put(3, "email");
        paramMap = map;
    }

    /**
     * 检测数据库中是否有数据
     * 有 true  没有 false
     *
     * @param param 需要校验的数据
     * @param type  校验的类型 1 username、2 phone、3 email
     * @return
     */
    @Override
    public boolean checkUser(String param, Integer type) {
        String column = paramMap.get(type);
        // 通过获取数据中的记录总数, 判断是否存在数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(column, param);
        int count = userMapper.selectCount(queryWrapper);
        return count > 0 ? true : false;
    }
}
