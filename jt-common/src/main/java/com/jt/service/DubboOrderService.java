package com.jt.service;

import com.jt.pojo.Order;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
public interface DubboOrderService {

    /**
     * 保存订单信息
     *
     * @param order form 表单数据
     * @return orderId
     */
    String saveOrder(Order order);

}
