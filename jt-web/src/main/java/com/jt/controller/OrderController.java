package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-27
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference(check = false)
    private DubboCartService cartService;

    @Reference(check = false)
    private DubboOrderService orderService;

    /**
     * 根据 userId 查询购物车信息, 并在页面展现
     * url: http://www.jt.com/order/create.html
     * ${carts}
     *
     * @return 购物车页面
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        User user = (User) request.getAttribute("JT_USER");
        Long id = user.getId();
        List<Cart> list = cartService.findCartListByUserId(id);
        model.addAttribute("carts", list);
        return "order-cart";
    }

    /**
     * 提交订单
     * url: http://www.jt.com/order/submit
     *
     * @param order 表单提交
     * @return SysResult 对象(包含 orderId 数据)
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order, HttpServletRequest request) {
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
        order.setUserId(userId);
        String orderId = orderService.saveOrder(order);
        if (StringUtils.isEmpty(orderId)) {
            return SysResult.fail();
        }
        return SysResult.success(orderId);
    }

}
