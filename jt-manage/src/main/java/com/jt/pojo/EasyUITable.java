package com.jt.pojo;

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

    private Integer total;
    private List<Item> rows;

}
