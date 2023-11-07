package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.service.VerifyCodeService;
import com.tws.flowershop.user.service.impl.VerifyCodeImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;


/**
 * @author TaoWeiShu
 * @date: 2023/10/8  15:00
 */
@RestController
@RequestMapping("/web/tws/fs/api/v1")
public class VerifyCodeApi {

    @Resource
    private VerifyCodeService verifyCodeService;
    @PostMapping("/email")
    public Object sendCode(
            @NotEmpty @RequestParam("q") String email,
            HttpServletRequest request) {

        HashMap<String, Object> hashMap = new HashMap<>();
        HttpSession session = request.getSession();
        boolean isCode = (boolean) session.getAttribute("isCode");
        Map<String, Object> map = verifyCodeService.allowSend(isCode);
        System.out.println("这个是" + map);
        if ((Boolean) (map.get("success"))) {
            //发送验证码
            VerifyCodeImpl verifyCode = verifyCodeService.sendCode(email,request);
            hashMap.put("code", 0);
            hashMap.put("response", verifyCode);
        } else {
            //不发生验证码
            hashMap.put("code", -1);
            hashMap.put("response", null);

        }
        return hashMap;
    }
}
