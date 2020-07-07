package com.jt.service;

import com.jt.pojo.EasyUITable;
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
	 * 展现商品列表数据, 以 EasyUI 表格数据呈现
	 * 分页 SQL : SELECT  FROM tb_item LIMIT 0, 20   (0-19, 共 20 条记录)
	 * @param page 当前页数
	 * @param rows 当前页数据总数
	 * @return EasyUITable VO 对象
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1. 获取总记录数
		int total = itemMapper.selectCount(null);
		//2.1 查询起始位置
		int start = (page - 1) * rows;
		//2.2 查询分页后的结果
		List<Item> itemList = itemMapper.selectItemByPage(start, rows);
		return new EasyUITable(total, itemList);
	}
}
