package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/11/2  15:11
 */

@RestController
@RequestMapping("/api/user")
public class LogOutApi {

    @Resource
    private TokenService tokenService;

    @PostMapping("/logout")
    public Object logout(@NotNull @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String headString = tk.substring(9, tk.length() - 9);
            Map<String, String> token = tokenService.findToken(headString);
            if (tokenService.analysisToken(token.get("key"), headString)) {
                System.out.println("登出时的token是" + headString);
                boolean b = tokenService.deleteToken(headString);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "success");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "error");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }
        return hashMap;
    }
}
