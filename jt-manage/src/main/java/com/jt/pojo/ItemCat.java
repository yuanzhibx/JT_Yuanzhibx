package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jt.vo.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类信息
 * POJO 实体对象使用包装类型
 * @Accessors(chain = true)  链式加载
 *
 * @author Yuanzhibx
 * @Date 2020-07-07
 */
@Data
@Accessors(chain = true)
@TableName("tb_item_cat")
public class ItemCat extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级分类id
     */
    private Long parentId;
    /**
     * 商品分类名称
     */
    private String name;
    /**
     * 商品分类状态
     */
    private Integer status;
    /**
     * 商品分类排序号
     */
    private Integer sortOrder;
    /**
     * 是否为父级
     * 0 false   |   1 true
     */
    private Boolean isParent;

}
