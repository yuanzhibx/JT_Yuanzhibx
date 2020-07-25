package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboCartService {

    List<Cart> findCartListByUserId(Long userId);

    void updateCartNum(Cart cart);

}
