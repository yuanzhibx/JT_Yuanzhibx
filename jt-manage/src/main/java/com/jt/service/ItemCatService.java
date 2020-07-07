package com.jt.service;

import com.jt.pojo.ItemCat;

/**
 * @author Yuanzhibx
 * @Date 2020-07-07
 */
public interface ItemCatService {

    /**
     * 根据商品分类 id  查询  商品分类信息
     * @param itemCatId 商品分类 id
     * @return 商品分类信息
     */
    ItemCat findItemCatById(Long itemCatId);

}
