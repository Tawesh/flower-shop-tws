package com.tws.flowershop.user.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author TaoWeiShu
 * @date: 2023/10/7  16:52
 */
@Controller
public class IndexPageControl {
//    @GetMapping("/")
//    public String indexPage(){
//        return "/index";
//    }

    @GetMapping("/agreement")
    public String agreement(){
        return "/agreement";
    }

}
