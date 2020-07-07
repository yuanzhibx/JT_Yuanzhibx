package com.jt.controller;

import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * url: http://localhost:8091/item/query?page=1&rows=20
	 * @param page 当前页数
	 * @param rows 当前页数据总数
	 * @return 商品列表数据
	 */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		return itemService.findItemByPage(page, rows);
	}

}
