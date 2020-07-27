package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 根据 userId 查询购物车信息, 并在页面展现
     * url: http://www.jt.com/order/create.html
     * ${carts}
     *
     * @return
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        User user = (User) request.getAttribute("JT_USER");
        Long id = user.getId();
        List<Cart> list = cartService.findCartListByUserId(id);
        model.addAttribute("carts", list);
        return "order-cart";
    }

}
