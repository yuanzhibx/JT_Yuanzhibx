package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 展现商品列表数据, 以 EasyUI 表格数据呈现
     * url: /item/query?page=1&rows=20
     *
     * @param page 当前页数
     * @param rows 当前页数据总数
     * @return 商品列表数据
     */
    @RequestMapping("/query")
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        return itemService.findItemByPage(page, rows);
    }

    /**
     * 商品新增操作
     *
     * @param item form 表单数据
     * @return SysResult 对象
     */
    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc) {
        //两张表同时入库
        itemService.saveItem(item, itemDesc);
        return SysResult.success();
    }

    /**
     * 商品修改操作
     *
     * @param item form 表单数据
     * @return SysResult 对象
     */
    @RequestMapping("/update")
    public SysResult updateItem(Item item) {
        itemService.updateItem(item);
        return SysResult.success();
    }

    /**
     * 商品删除操作
     *
     * @param ids 要删除的 id (数组类型)
     * @return SysResult 对象
     */
    @RequestMapping("/delete")
    public SysResult deleteItems(Long[] ids) {
        itemService.deleteItems(ids);
        return SysResult.success();
    }

    /**
     * 更新 status 状态
     *
     * @param ids 要更改的 id
     * @param status 状态
     * @return SysResult 对象
     */
    @RequestMapping("/updateStatus/{status}")
    public SysResult updateItemStatus(Long[] ids, @PathVariable Integer status) {
        //实现商品状态修改
        itemService.updateItemStatus(ids, status);
        return SysResult.success();
    }

}
