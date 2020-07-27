package com.jt.pojo;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@TableName("tb_order")
@Data
@Accessors(chain = true)
public class Order extends BasePojo {

    /**
     * 入库操作忽略该字段
     */
    @TableField(exist = false)
    private OrderShipping orderShipping;

    /**
     * 封装订单商品信息  一对多
     * 入库操作忽略该字段
     */
    @TableField(exist = false)
    private List<OrderItem> orderItems;

    @TableId
    private String orderId;
    private String payment;
    private Integer paymentType;
    private String postFee;
    private Integer status;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;
    private String shippingName;
    private String shippingCode;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;
    private Integer buyerRate;

}