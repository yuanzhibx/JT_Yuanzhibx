package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
public interface ItemService {

    /**
     * 展现商品列表数据, 以 EasyUI 表格数据呈现
     *
     * @param page 当前页数
     * @param rows 当前页数据总数
     * @return EasyUITable VO 对象
     */
    EasyUITable findItemByPage(Integer page, Integer rows);

    /**
     * 商品新增操作
     * 实现关联新增操作
     *
     * @param item
     * @param itemDesc
     */
    void saveItem(Item item, ItemDesc itemDesc);

    /**
     * 商品更新操作
     *
     * @param item
     * @param itemDesc
     */
    void updateItem(Item item, ItemDesc itemDesc);

    /**
     * 商品删除操作
     *
     * @param ids 要删除的 id (数组类型)
     */
    void deleteItems(Long[] ids);

    /**
     * 更新 status 状态
     * SQL :    UPDATE tb_item
     *          SET status = #{status}, updated = #{updated}
     *          WHERE id IN (id1, id2, id3......)
     * @param ids 要更改的 id
     * @param status 状态
     */
    void updateItemStatus(Long[] ids, Integer status);

    /**
     * 根据 itemId 查询商品详情信息并回显
     *
     * @param itemId
     * @return
     */
    ItemDesc findItemDescById(Long itemId);

}
