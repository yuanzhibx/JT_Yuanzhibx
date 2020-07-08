package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.vo.EasyUITable;
import com.jt.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;

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
