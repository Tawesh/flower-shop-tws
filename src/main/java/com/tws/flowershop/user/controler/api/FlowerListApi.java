package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.entity.FlowerEntity;
import com.tws.flowershop.user.service.CartService;
import com.tws.flowershop.user.service.FlowerService;
import com.tws.flowershop.user.service.TokenService;
import com.tws.flowershop.user.service.impl.CartImpl;
import com.tws.flowershop.user.service.impl.FlowerImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/10  15:55
 */
@RestController
@RequestMapping("/web/tws/fs/api/data")
public class FlowerListApi {

    @Resource
    private FlowerService flowerService;

    @Resource
    private TokenService tokenService;

    @GetMapping("flower-list")
    public Object getFlowerList(@NotNull @RequestParam("start") int start,
                                @NotNull @RequestParam("end") int end,
                                @NotNull @RequestHeader("User-Agent") String userAgent
    ) {
        HashMap<String, Object> hashMap = new HashMap<>();

        if (userAgent != null && !userAgent.contains("Mozilla")) {
            hashMap.put("code", 500);
            hashMap.put("response", "not access your request！because maybe it is creeper program");
            return hashMap;
        }

        try {
            FlowerImpl data = flowerService.getData(start, end);
            hashMap.put("code", 0);
            hashMap.put("response", data);
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", null);
        }
        return hashMap;
    }

    @Resource
    private CartService cartService;

    @GetMapping("/flower")
    public Object getData(@RequestParam("itemId") String id, @RequestParam("userId") String userid) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            FlowerImpl info = flowerService.getFlowerInfoById(id);
            int userCart = cartService.countCartNumber(userid);
            map.put("code", 0);
            map.put("response", info);
            map.put("cartNum", userCart);
        } catch (NullPointerException nullPointerException) {
            map.put("code", -1);
            map.put("cartNum", 0);
            map.put("response", "请登录后访问");
        }
        return map;
    }

    @PostMapping("/flower/search")
    public Object search(@RequestParam("key") String key) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            List<FlowerEntity> flowerEntities = flowerService.userSearch(key);
            hashMap.put("code", 0);
            hashMap.put("response", flowerEntities);
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }
        return hashMap;
    }

}
