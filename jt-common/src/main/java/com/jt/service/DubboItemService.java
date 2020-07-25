package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboItemService {

    /**
     * 根据商品 id 查询商品信息
     *
     * @param itemId 商品 id
     * @return 商品信息
     */
    Item findItemById(Long itemId);

    /**
     * 根据商品 id 查询商品详情信息
     *
     * @param itemId 商品 id
     * @return 商品详情信息
     */
    ItemDesc findItemDescById(Long itemId);

}
