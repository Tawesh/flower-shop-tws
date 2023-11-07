package com.tws.flowershop.admin.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TaoWeiShu
 * @date: 2023/10/14  22:42
 */
@Controller
@RequestMapping("/admin")
public class IndexControl {

    @GetMapping("/login")
    public String toLogin(){
        return "admin/login";
    }

    @GetMapping("/home")
    public String index() {
        return "admin/admin-index";
    }

    @GetMapping("/userManage")
    public String userManage() {
        return "admin/UserManagement";
    }

    @GetMapping("/orderManage")
    public String orderManage() {
        return "admin/OrderManagement";
    }


    @GetMapping("/productManage")
    public String productManage() {
        return "admin/ProductManagement";
    }

    @GetMapping("/websiteInfo")
    public String websiteInfo() {
        return "admin/websiteInfo";
    }

}
