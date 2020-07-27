package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单物流
 *
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@TableName("tb_order_shipping")
@Data
@Accessors(chain = true)
public class OrderShipping extends BasePojo {

    @TableId
    private String orderId;

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

}