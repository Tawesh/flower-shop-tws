package com.tws.flowershop.user.service;

import com.tws.flowershop.user.entity.CartEntity;
import com.tws.flowershop.user.mapper.CartMapper;
import com.tws.flowershop.user.service.impl.CartImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  11:48
 */
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private CartImpl cart;

    public int countCartNumber(String userId){
        try{
            return cartMapper.findUserCart(userId);
        }catch (Exception e){
            return 0;
        }
    }

    public CartImpl findUserCart(String userId) {
        HashMap<String, List<CartEntity>> hashMap = new HashMap<>();
        try {
            List<CartEntity> flowerCart = cartMapper.findUserCartType(userId, 0);
            List<CartEntity> giftCart = cartMapper.findUserCartType(userId, 1);
            hashMap.put("0", flowerCart);
            hashMap.put("1", giftCart);
            cart.setMessage("success");
            cart.setData(hashMap);
        } catch (Exception e) {
            cart.setMessage("error");
            cart.setData(null);
        }
        return cart;
    }

    //添加

    public boolean insertCart(CartEntity cart) {
        try {
            cartMapper.insertCartByOne(cart);
            return true;
        } catch (Exception e) {
            System.out.println(cart);
            System.out.println("加入购物车时出现异常"+e);
            return false;
        }
    }

    //删除

    public boolean deleteCart(String cartId) {
        try {
            cartMapper.deleteCartByOne(cartId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clearCartToBuy(List<String> cartIdList){
        try{
            for (String s : cartIdList) {
                cartMapper.deleteCartByOne(s);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
