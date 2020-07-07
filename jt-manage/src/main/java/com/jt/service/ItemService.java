package com.jt.service;

import com.jt.vo.EasyUITable;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
public interface ItemService {

    /**
     * 展现商品列表数据, 以 EasyUI 表格数据呈现
     * @param page 当前页数
     * @param rows 当前页数据总数
     * @return EasyUITable VO 对象
     */
    EasyUITable findItemByPage(Integer page, Integer rows);

}
