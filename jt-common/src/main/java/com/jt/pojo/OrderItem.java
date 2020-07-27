package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单商品
 *
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@TableName("tb_order_item")
@Data
@Accessors(chain = true)
public class OrderItem extends BasePojo {

    @TableId
    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;

}