package com.jt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;

/**
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@Service
public class OrderServiceImpl implements DubboOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 保存订单信息
     *
     * @param order form 表单数据
     * @return orderId
     */
    @Transactional
    @Override
    public String saveOrder(Order order) {
        String orderId = "" + order.getUserId() + System.currentTimeMillis();
		Date date = new Date();

		// 实现订单入库
		order.setOrderId(orderId).setStatus(1).setCreated(date).setUpdated(date);
		orderMapper.insert(order);

		// 订单物流入库
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId).setCreated(date).setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        // 订单商品入库
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(orderId).setCreated(date).setUpdated(date);
            orderItemMapper.insert(orderItem);
        }

        return orderId;
    }

}
