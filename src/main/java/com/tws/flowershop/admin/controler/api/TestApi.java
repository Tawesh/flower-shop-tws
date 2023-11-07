package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.admin.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TaoWeiShu
 * @date: 2023/10/17  14:18
 */
@RestController
@RequestMapping("/test")
public class TestApi {
    @Resource
    private AdminMapper adminMapper;
    @PostMapping("/api")
    public Object getData(){
        return adminMapper.selectManyTable();
    }
}
