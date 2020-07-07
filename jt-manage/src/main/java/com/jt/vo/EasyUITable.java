package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 封装 EasyUI 表格数据的 VO 对象
 *
 * @author Yuanzhibx
 * @Date 2020-07-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EasyUITable {

    /**
     * 数据总数
     */
    private Integer total;

    /**
     * 每页数据总数
     */
    private List<Item> rows;

}
