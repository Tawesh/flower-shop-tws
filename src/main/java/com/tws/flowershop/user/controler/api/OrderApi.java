package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.entity.OrderEntity;
import com.tws.flowershop.user.service.OrderService;
import com.tws.flowershop.user.service.TokenService;
import com.tws.flowershop.user.service.impl.OrderImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/11  15:42
 */
@RestController
@RequestMapping("/web/tws/fs/api/data")
public class OrderApi {

    @Resource
    private OrderService orderService;

    @Resource
    private TokenService tokenService;

    @PostMapping("/order-info")
    public Object getData(@NotNull @RequestParam("uid") String id,
                          @NotNull @RequestParam("tk") String token) {

        HashMap<String, Object> hashMap = new HashMap<>();
        String headString = token.substring(9, token.length() - 9);
        try {
            Map<String, String> tokenMap = tokenService.findToken(headString);
            if (tokenService.analysisToken(tokenMap.get("key"), headString)) {
                OrderImpl order = orderService.selById(id);
                hashMap.put("code", 0);
                hashMap.put("response", order);
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已过期");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务被拒绝");
        }
        return hashMap;
    }

    /**
     * 添加订单
     *
     * @param
     * @return
     */
    @PostMapping("/order-info/add")
    public Object addOrderInfo(@NotNull @RequestBody OrderEntity order, @NotNull @RequestParam String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (tk == null) {
            hashMap.put("code", -2);
            hashMap.put("response", "please restart login");
            return hashMap;
        }
        String headString = tk.substring(9, tk.length() - 9);
        try {
            Map<String, String> token = tokenService.findToken(headString);
            if (tokenService.analysisToken(token.get("key"), headString)) {
                boolean b = orderService.addInfo(order);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "订单已被添加");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "订单添加失败");
                }
            } else {
                hashMap.put("code", -2);
                hashMap.put("response", "登录已过期");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务被拒绝");
        }
        return hashMap;
    }

    @PostMapping("/repay")
    public Object rePay(@RequestBody Map<String,String> map){
        HashMap<String, Object> hashMap = new HashMap<>();
        try{
            boolean b = orderService.rePay(Integer.parseInt(map.get("payNum")), map.get("orderId"));
            if (b){
                hashMap.put("code",0);
                hashMap.put("response","支付成功");
            }else {
                hashMap.put("code",-1);
                hashMap.put("response","支付失败");
            }
        }catch (Exception e){
            hashMap.put("code",-1);
            hashMap.put("response","系统异常，请稍后再试");
        }
        return hashMap;

    }

}
