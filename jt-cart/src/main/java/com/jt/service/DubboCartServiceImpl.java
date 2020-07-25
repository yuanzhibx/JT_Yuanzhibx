package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.pojo.Cart;
import com.jt.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Service
public class DubboCartServiceImpl implements DubboCartService {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 根据用户 id 查询购物车信息
     *
     * @param userId 用户 id
     * @return 购物车信息
     */
    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return cartMapper.selectList(queryWrapper);
    }

    /**
     * 更新购物车数量
     *
     * @param cart 购物车数据
     */
    @Override
    public void updateCartNum(Cart cart) {
        Cart cartTemp = new Cart();
        cartTemp.setNum(cart.getNum()).setUpdated(new Date());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
        // 柑橘对象中不为 null 的数据,设置 set
        cartMapper.update(cartTemp, updateWrapper);
    }

}
