package com.tws.flowershop.user.controler;

import com.tws.flowershop.user.service.FlowerService;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/11  9:31
 */
@Controller
public class BuyControl {

    @Resource
    private TokenService tokenService;

    @Resource
    private FlowerService flowerService;

    @GetMapping("/buy")
    public String toBuy(@RequestParam("itemId") String itemId) {
        try {
            //先查询有没有商品
            boolean b = flowerService.itemIsExists(itemId);
            if (b) {
                return "user/buy";
            } else {
                return "redirect:/error";
            }
        } catch (Exception e) {
            return "redirect:/user/login-register";
        }

    }
}
