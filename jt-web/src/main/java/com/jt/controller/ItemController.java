package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    @Reference(check = false)
    private DubboItemService itemService;
    /**
     * 根据商品 id 执行查询的商品
     * ${item.title}            商品信息
     * ${itemDesc.ItemDesc}     商品详情信息
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }

}
