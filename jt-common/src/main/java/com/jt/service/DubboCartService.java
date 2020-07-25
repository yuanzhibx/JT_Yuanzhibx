package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboCartService {

    /**
     * 根据用户 id 查询购物车信息
     *
     * @param userId 用户 id
     * @return 购物车信息
     */
    List<Cart> findCartListByUserId(Long userId);

    /**
     * 更新购物车数量
     *
     * @param cart 购物车数据
     */
    void updateCartNum(Cart cart);

}
