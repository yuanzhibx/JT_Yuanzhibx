package com.jt.pojo;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单
 *
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@TableName("tb_order")
@Data
@Accessors(chain = true)
public class Order extends BasePojo {

    /**
     * 订单物流信息
     * 入库操作忽略该字段
     */
    @TableField(exist = false)
    private OrderShipping orderShipping;

    /**
     * 订单商品信息  一对多
     * 入库操作忽略该字段
     */
    @TableField(exist = false)
    private List<OrderItem> orderItems;

    /**
     * 订单 id
     */
    @TableId
    private String orderId;

    /**
     * 实付金额
     */
    private String payment;

    /**
     * 支付类型
     */
    private Integer paymentType;

    /**
     * 邮费
     */
    private String postFee;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家昵称
     */
    private String buyerNick;

    /**
     * 买家是否已评价
     */
    private Integer buyerRate;

}