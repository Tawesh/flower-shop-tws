package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.user.entity.OrderEntity;
import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.OrderService;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  20:49
 */
@RestController
@RequestMapping("/web/tws/fs/api/admin/data")
public class OrderManageApi {

    @Resource
    private OrderService orderService;

    @GetMapping("/orderInfo")
    public Object findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<OrderEntity> findAll = orderService.adminFindAll();
        hashMap.put("data", 0);
        hashMap.put("response", findAll);
        return hashMap;
    }

    @Resource
    private TokenService tokenService;

    @PostMapping("/order/search")
    public Object selectById(@RequestParam("tk") String tk, @RequestParam("orderId") String orderId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                List<OrderEntity> orderEntity = orderService.adminSelByOrdId(orderId);
                hashMap.put("code", 0);
                hashMap.put("response", orderEntity);
            } else {
                hashMap.put("code", 0);
                hashMap.put("response", "登录已失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
        }
        return hashMap;
    }

    @PostMapping("/order/edit")
    public Object edit(@RequestBody Map<String, String> map, @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();

        try {

            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                String phone = map.get("phone");
                String address = map.get("address");
                String orderId = map.get("orderId");
                boolean b = orderService.adminEditOrder(orderId, phone, address);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "修改成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "修改失败");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录失效");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
        }
        return hashMap;
    }


    /**
     * 删除
     *
     * @param
     * @return
     */

    @PostMapping("/order/delete")
    public Object delete(@RequestParam("orderId") String orderId, @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {

            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                boolean b = orderService.adminDeleteOrder(orderId);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "删除成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "删除失败");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }

        return hashMap;
    }

}
