package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-07
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 根据商品分类 id  查询  商品分类信息
     * @param itemCatId 商品分类 id
     * @return 商品分类信息
     */
    @Override
    public ItemCat findItemCatById(Long itemCatId) {
        return itemCatMapper.selectById(itemCatId);
    }

    /**
     * 将 ItemCat 转化为 EasyUITree 对象.
     * 数据库中数据类型: ItemCat 对象 POJO
     * 需要: EasyUITree 对象 VO
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITree> findItemCatByParentId(Long parentId) {
        //1. 根据 parentId 查询数据库信息(根据父级查询子级)
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);

        //2. 将 itemCatList 转换为 VO 对象
        List<EasyUITree> treeList = new ArrayList<>();
        for (ItemCat itemCat : itemCatList) {
            Long id = itemCat.getId();
            String text = itemCat.getName();
            //如果是父级, 则关闭 closed  如不是父级,则打开 open
            String state = itemCat.getIsParent()?"closed":"open";
            EasyUITree easyUITree = new EasyUITree(id, text, state);
            treeList.add(easyUITree);
        }
        return treeList;
    }

}
