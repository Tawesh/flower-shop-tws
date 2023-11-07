package com.tws.flowershop.user.controler;

import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  11:25
 */
@Controller
@RequestMapping("/user")
public class CartControl {

    @Resource
    private ClientUserService clientUserService;

    @Resource
    private TokenService tokenService;

    @GetMapping("/cart")
    public String index(@NotNull @RequestParam("u") String userId, @NotNull @RequestParam("tk") String tk) {

        boolean b = clientUserService.idIsExists(userId);
        if (b) {
            String s = tk.substring(9, tk.length() - 9);
            Map<String, String> token = tokenService.findToken(s);
            if (tokenService.analysisToken(token.get("key"), s)) {
                return "user/cart/index";
            } else {
                return "redirect:/user/login-register?s=buy&timestamp=" + System.currentTimeMillis();
            }
        } else {
            return "redirect:/user/login-register?s=buy&timestamp=" + System.currentTimeMillis();
        }
    }
}
