package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.user.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  22:04
 */
@RestController
@RequestMapping("/web/tws/fs/api/admin/data")
public class WebsiteApi {

    @Resource
    private OrderService orderService;

    @GetMapping("/websiteInfo")
    public Object getData(){
        HashMap<String, Object> hashMap = new HashMap<>();
        long count = orderService.adminOrderCount();
        hashMap.put("code",0);
        hashMap.put("orderCount",count);
        return hashMap;
    }


}
