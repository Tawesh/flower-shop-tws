package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.entity.CartEntity;
import com.tws.flowershop.user.service.CartService;
import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.impl.CartImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  14:12
 */
@RestController
@RequestMapping("/shop/cart")
public class CartApi {


    @Resource
    private CartService cartService;

    @Resource
    private ClientUserService clientUserService;

    @PostMapping("/info")
    public Object getCart(@NotNull @RequestParam("userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            boolean b = clientUserService.idIsExists(userId);
            if (b) {
                CartImpl cart = cartService.findUserCart(userId);
                hashMap.put("code", 0);
                hashMap.put("response", cart);
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "user not exists! pleases to login! thanks");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常，请求失败");
        }
        return hashMap;
    }

    @PostMapping("/insert")
    public Object addCart(@NotNull @RequestBody CartEntity cart) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            boolean b = cartService.insertCart(cart);
            if (b) {
                hashMap.put("code", 0);
                hashMap.put("response", "已添加到购物车");
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "添加购物车失败");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常，请求失败");
        }
        return hashMap;
    }

    @PostMapping("/delete")
    public Object deleteCart(@NotNull @RequestParam("cartId") String cartId) {
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            boolean b = cartService.deleteCart(cartId);
            if (b) {
                hashMap.put("code", 0);
                hashMap.put("response", "操作成功");
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "操作失败");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常，请求失败");
        }
        return hashMap;
    }

    @PostMapping("clear-buy")
    public Object clearAll(@NotNull @RequestBody List<String> cartIdList) {
        System.out.println("要购买的商品列表的id " + cartIdList);
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            boolean b = cartService.clearCartToBuy(cartIdList);
            if (b) {
                hashMap.put("code", 0);
                hashMap.put("response", "操作成功");
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "操作失败");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常，请求失败");
        }
        return hashMap;
    }

}
