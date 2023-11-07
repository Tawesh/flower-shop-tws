package com.tws.flowershop.user.controler;

import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/11  15:20
 */
@Controller
public class PersonalInfoControl {

    @Resource
    private TokenService tokenService;

    @Resource
    private ClientUserService clientUserService;

    @GetMapping("/user/user-info")
    public String toPersonInfo(@RequestParam("tk") String tk, @RequestParam("uid") String uid) {
        System.out.println(tk + "\n" + uid);
        if ("".equals(tk) || "".equals(uid)) {
            return "redirect:/login-register";
        } else {
            boolean b = clientUserService.idIsExists(uid);
            if (b) {
                if (tk.length() >= 18) {
                    String headString = tk.substring(9, tk.length() - 9);
                    Map<String, String> token = tokenService.findToken(headString);
                    if (tokenService.analysisToken(token.get("key"), headString)) {
                        return "user/personal-center";
                    }
                }
            }

            return "user/login-register";
        }
    }

}
