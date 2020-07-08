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
     * url: http://localhost:8091/item/cat/queryItemName?itemCatId=163
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
     * 业务: 查询商品分类信息, 返回到 VO 对象
     * URL: http://localhost:8091/item/cat/list
     * 参数: 无
     * 返回值结果: EasyUITree 对象
     * JSON 格式: [{"id": "2", "text": "英雄联盟", "iconCls": "icon-save"}{"id": "3", "text": "吃鸡游戏", "iconCls": "icon-save"}]
     */

    /**
     * 查询商品分类信息, 返回到 VO 对象
     * url: http://localhost:8091/item/cat/list
     *
     * @return EasyUITree 对象
     */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        //1. 查询一级商品分类信息
//        Long parentId = id == null ? 0L : id;
        return itemCatService.findItemCatByParentId(parentId);
    }

}
