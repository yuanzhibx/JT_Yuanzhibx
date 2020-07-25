package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Data
@TableName("tb_cart")
@Accessors(chain = true)
public class Cart extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;

}
