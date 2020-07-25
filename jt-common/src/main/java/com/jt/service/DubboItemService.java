package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboItemService {

    Item findItemById(Long itemId);

    ItemDesc findItemDescById(Long itemId);

}
