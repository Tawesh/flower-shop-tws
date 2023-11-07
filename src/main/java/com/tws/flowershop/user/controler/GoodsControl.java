package com.tws.flowershop.user.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author TaoWeiShu
 * @date: 2023/10/10  16:29
 */
@Controller
public class GoodsControl {
    @GetMapping("/goods-list")
    public String toGoodsPage(){
        return "user/goods";
    }
}
