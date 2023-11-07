package com.tws.flowershop.user.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author TaoWeiShu
 * @date: 2023/10/8  11:08
 */
@Controller
@RequestMapping("/user")
public class LogRegControl {

    @GetMapping("/login-register")
    public String loginRegisterPage() {return "user/login-register";
    }

    @GetMapping("/forget-pwd")
    public String forgetPassword() {
        return "user/forget-password";
    }
}
