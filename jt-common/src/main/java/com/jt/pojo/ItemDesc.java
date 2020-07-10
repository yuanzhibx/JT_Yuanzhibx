package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
@TableName("tb_item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo {

    /**
     * 商品 id 号
     * itemId 是 item 表主键, 也是 item_desc 主键
     */
    @TableId
    private Long itemId;

    /**
     * 商品详情信息
     */
    private String ItemDesc;

}
