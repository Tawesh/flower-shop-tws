package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.admin.service.AdminService;
import com.tws.flowershop.admin.service.impl.AdminImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  18:41
 */
@RestController
@RequestMapping("/web/tws/fs/api/admin/v1")
public class AdminLoginApi {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public Object toLogin(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String adminAcc = map.get("adminAcc");
        String adminPwd = map.get("adminPwd");
        AdminImpl admin = adminService.findAdmin(adminAcc, adminPwd);


        if ("success".equals(admin.getMsg())) {
            hashMap.put("code", 0);
            hashMap.put("response", admin);
        } else {
            hashMap.put("code", -1);
            hashMap.put("response", null);
        }
        return hashMap;
    }
}
