package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Service
public class DubboItemServiceImpl implements DubboItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * 根据商品 id 查询商品信息
     *
     * @param itemId 商品 id
     * @return 商品信息
     */
    @Override
    public Item findItemById(Long itemId) {
        return itemMapper.selectById(itemId);
    }

    /**
     * 根据商品 id 查询商品详情信息
     *
     * @param itemId 商品 id
     * @return 商品详情信息
     */
    @Override
    public ItemDesc findItemDescById(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }

}
