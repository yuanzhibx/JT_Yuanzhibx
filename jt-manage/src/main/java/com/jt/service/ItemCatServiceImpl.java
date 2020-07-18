package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
     * spring 容器初始化时, 该注解不是必须注入, 如果要调用必须有值
     */
    @Autowired(required = false)
    private Jedis jedis;

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
     * 根据一级 id 查询子级信息, 并将结果由 ItemCat 转化为 EasyUITree 对象
     * 数据库中数据类型:    ItemCat 对象 (POJO)
     * 需要数据类型:       EasyUITree 对象 (VO)
     * @param parentId 父级 id
     * @return List<EasyUITree> 子级对象集合
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

    /**
     * Redis
     * 根据商品 id 实现缓存查询商品分类信息
     * @param parentId 商品分类 id
     * @return 商品分类信息
     */
    @Override
    public List<EasyUITree> findItemCatByCache(Long parentId) {
        // 定义 key
        String key = "ITEM_CAT_LIST::" + parentId;
        Long startTime = System.currentTimeMillis();
        List<EasyUITree> treeList = new ArrayList<>();

        // 判断 redis 中是否有值
        if (jedis.exists(key)) {
            //不是第一次查询, 则获取缓存数据直接返回
            String json = jedis.get(key);
            Long endTime = System.currentTimeMillis();
            treeList = ObjectMapperUtil.toObject(json, treeList.getClass());
            System.out.println("redis 查询缓存的时间 [ " + (endTime - startTime) + " ] ms");
        } else {
            //没有key, 表示第一次查询
            treeList = findItemCatByParentId(parentId);
            Long endTime = System.currentTimeMillis();
            String json = ObjectMapperUtil.toJson(treeList);
            jedis.set(key, json);
            System.out.println("redis 第一次查询缓存的时间 [ " + (endTime - startTime) + " ] ms");
        }
        return treeList;
    }

}
