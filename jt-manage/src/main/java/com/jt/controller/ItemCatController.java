package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-07
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 根据商品分类 id  查询  商品分类信息, 并返回商品分类名称
     * url: /item/cat/queryItemName?itemCatId=163
     *
     * @param itemCatId 商品分类 id
     * @return 商品分类名称
     */
    @RequestMapping("/queryItemName")
    public String findItemCatNameById(Long itemCatId) {
        ItemCat itemCat = itemCatService.findItemCatById(itemCatId);
        return itemCat.getName();
    }

    /**
     * Redis 缓存分类信息
     * 根据商品父级 id 查询商品分类信息, 返回 VO 对象
     * url: /item/cat/list
     *
     * @param parentId 一级分类 id
     * @return EasyUITree 对象
     */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
//        Long parentId = id == null ? 0L : id;
//        return itemCatService.findItemCatByParentId(parentId);
        return itemCatService.findItemCatByCache(parentId);
    }

}
