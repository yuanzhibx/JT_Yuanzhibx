package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-08
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree {

    /**
     * id 值  ItemCat 中的 id
     */
    private Long id;

    /**
     * 文本信息 ItemCat 中的 name 属性
     */
    private String text;

    /**
     * 状态
     * 打开: open  关闭: closed
     */
    private String state;

}
