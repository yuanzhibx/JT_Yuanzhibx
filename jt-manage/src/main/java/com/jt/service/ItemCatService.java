package com.jt.service;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

import java.util.List;

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

    /**
     * 根据一级 id 查询子级信息, 并将结果由 ItemCat 转化为 EasyUITree 对象
     * 数据库中数据类型:    ItemCat 对象 (POJO)
     * 需要数据类型:       EasyUITree 对象 (VO)
     * @param parentId 父级 id
     * @return List<EasyUITree> 子级对象集合
     */
    List<EasyUITree> findItemCatByParentId(Long parentId);

}
