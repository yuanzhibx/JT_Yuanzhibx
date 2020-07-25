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
     * 根据商品 id 查询商品/商品详情信息, 并显示在页面
     * url: http://www.jt.com/items/1474391978.html  跳转到商品页面
     * ${item.title}            商品信息
     * ${itemDesc.ItemDesc}     商品详情信息
     *
     * @param itemId 商品 id
     * @return 商品页面
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
