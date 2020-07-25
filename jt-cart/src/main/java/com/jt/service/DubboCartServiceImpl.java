package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.pojo.Cart;
import com.jt.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Service
public class DubboCartServiceImpl implements DubboCartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return cartMapper.selectList(queryWrapper);
    }

}
