package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.vo.EasyUITable;
import com.jt.pojo.Item;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 利用 MP 方式呈现商品列表数据
     *
     * @param page 当前页数
     * @param rows 当前页数据总数
     * @return
     */
    @Override
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        //1. 定义分页对象
        Page<Item> mpPage = new Page<>(page, rows);
        //2. 定义条件构造器
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        //3. 按照更新时间排序
        queryWrapper.orderByDesc("updated");
        //4. 执行分页操作, 封装 Page 对象
        mpPage = itemMapper.selectPage(mpPage, queryWrapper);
        int total = (int) mpPage.getTotal();
        List<Item> records = mpPage.getRecords();
        return new EasyUITable(total, records);
    }

    /**
     * 商品新增操作
     *
     * @param item
     */
    @Transactional
    @Override
    public void saveItem(Item item) {
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        itemMapper.insert(item);
    }

    /**
     * 商品更新操作
     *
     * @param item
     */
    @Override
    public void updateItem(Item item) {
        item.setUpdated(new Date());
        itemMapper.updateById(item);
    }

    /**
     * 商品删除操作
     *
     * @param ids 要删除的 id (数组类型)
     */
    @Override
    public void deleteItems(Long[] ids) {
        // 将数组转换为集合
        List<Long> list = Arrays.asList(ids);
        itemMapper.deleteBatchIds(list);
    }

    /**
     * 更新 status 状态
     * SQL :    UPDATE tb_item
     *          SET status = #{status}, updated = #{updated}
     *          WHERE id IN (id1, id2, id3......)
     * @param ids 要更改的 id
     * @param status 状态
     */
    @Override
    public void updateItemStatus(Long[] ids, Integer status) {
        //1. 定义修改数据
        Item item = new Item();
        item.setStatus(status).setUpdated(new Date());
        //2. 定义修改条件
        UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
        List<Long> idList = Arrays.asList(ids);
        updateWrapper.in("id", idList);
        itemMapper.update(item, updateWrapper);
    }

    /**
     * 展现商品列表数据, 以 EasyUI 表格数据呈现
     * 分页 SQL : SELECT  FROM tb_item LIMIT 0, 20   (0-19, 共 20 条记录)
     * @param page 当前页数
     * @param rows 当前页数据总数
     * @return EasyUITable VO 对象
     */
//	@Override
//	public EasyUITable findItemByPage(Integer page, Integer rows) {
//		//1. 获取总记录数
//		int total = itemMapper.selectCount(null);
//		//2.1 查询起始位置
//		int start = (page - 1) * rows;
//		//2.2 查询分页后的结果
//		List<Item> itemList = itemMapper.selectItemByPage(start, rows);
//		return new EasyUITable(total, itemList);
//	}

}
