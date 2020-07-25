package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference(check = false)
    private DubboCartService cartService;

    /**
     * 根据用户 id 查询购物车信息, 并显示在页面
     * url: http://www.jt.com/cart/show.html
     * ${cartList}  购物车数据
     *
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model) {
        // 获取 userId
        Long userId = 7L;
        // 根据 userId 查询购物车数据
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    /**
     * 更新购物车数量
     * url: http://www.jt.com/cart/update/num/562379/13
     *
     * @param cart
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    public void updateCart(Cart cart) {
        Long userId = 7L;
        cart.setUserId(userId);
        cartService.updateCartNum(cart);
    }

    /**
     * 新增购物车数据
     * url地址: http://www.jt.com/cart/add/562379.html
     *
     * @param cart 商品数据
     * @return 重定向到购物车页面
     */
    @RequestMapping("/add/{itemId}")
    public String saveCart(Cart cart) {
        Long userId = 7L;
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html";
    }

}
