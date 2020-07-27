package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String show(Model model, HttpServletRequest request) {
        // 动态获取 userId
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
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
    public void updateCart(Cart cart, HttpServletRequest request) {
        // 动态获取 userId
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
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
    public String saveCart(Cart cart, HttpServletRequest request) {
        // 动态获取 userId
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html";
    }

    /**
     * 删除购物车数据
     * url地址: http://www.jt.com/cart/delete/1474391978.html
     * ${cart.itemId} 商品 id
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId, HttpServletRequest request) {
        // 动态获取 userId
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cartService.deleteCart(cart);
        return "redirect:/cart/show.html";
    }

}
