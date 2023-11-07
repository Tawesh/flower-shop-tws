package com.tws.flowershop.user.controler.api;

import com.tws.flowershop.user.entity.ClientUserEntity;
import com.tws.flowershop.user.entity.ClientUserInfoEntity;
import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.impl.ClientUserImpl;
import com.tws.flowershop.comment.utils.DateTimeUtil;
import com.tws.flowershop.comment.utils.Md5Utils;
import com.tws.flowershop.comment.utils.RandomNumberUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/9  14:56
 */
@RestController
@RequestMapping("/web/tws/fs/api/v3")
public class LogRegApi {

    @Resource
    private ClientUserService clientUserService;
    @Resource
    private DateTimeUtil dateTimeUtil;

    @Resource
    private Md5Utils md5Utils;

    /**
     * 登录接口
     */
    @PostMapping("/user/login")
    public Object toLogin(@RequestBody Map<String, String> map, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //get account and pwd
        String account = map.get("account");
        String pwd = map.get("password");
        System.out.println(account + pwd);
        String password = md5Utils.encrypt(pwd);
        System.out.println(password);
        //verify is pass?
        HttpSession session = request.getSession();
        try {
            //从session中获取
            boolean isLog = (Boolean) session.getAttribute("isLog");
            System.out.println(isLog);
            if (!isLog) {
                hashMap.put("code", -1);
                hashMap.put("response", null);
            } else {
                //查询数据与返回
                ClientUserImpl clientUser = clientUserService.searchUser(account, password);
                hashMap.put("code", 0);
                hashMap.put("response", clientUser);
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", null);
        }
        System.out.println("登录返回的数据是" + hashMap);
        return hashMap;
    }

    @Resource
    private RandomNumberUtil randomNumberUtil;
    @Resource
    private ClientUserEntity clientUserEntity;
    @Resource
    private ClientUserInfoEntity clientUserInfoEntity;

    //注册接口

    @PostMapping("/user/register")
    public Object toRegister(@RequestBody Map<String, String> map, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        HttpSession session = request.getSession();
        try {
            boolean isReg = (Boolean) session.getAttribute("isReg");
            if (isReg) {
                //get account and pwd
                String account = map.get("account");
                String password = map.get("password");
                String username = map.get("username");
                String phone = map.get("phone");
                String address = map.get("address");
                //账号不存在才注册
                boolean exists = clientUserService.userIsExists(account);
                System.out.println("用户存在吗" + exists);
                if (!exists) {
                    String id = "fs" + randomNumberUtil.generateRandomCode(15);

                    clientUserEntity.setAccount(account);
                    clientUserEntity.setPassword(password);
                    clientUserEntity.setId(id);
                    clientUserEntity.setUserName(username);
                    clientUserEntity.setPhone(phone);
                    clientUserEntity.setAddress(address);
                    clientUserEntity.setRegisterTime(dateTimeUtil.getDateTime());
                    boolean b = clientUserService.toRegister(clientUserEntity);
                    if (b) {
                        hashMap.put("code", 0);
                        hashMap.put("message", "注册成功");
                    } else {
                        hashMap.put("code", -1);
                        hashMap.put("message", "注册失败");
                    }
                } else {
                    hashMap.put("code", -2);
                    hashMap.put("message", "注册失败，用户已存在");
                }

            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "服务器检测到恶意请求，已终止访问");
            }
        } catch (NullPointerException nullPointerException) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务器检测到恶意请求，已终止访问");
        }

        return hashMap;
    }

    @RequestMapping("/forget-password")
    public Object forgetPwd(@NotNull @RequestBody Map<String,String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            // 用户是否存在
            boolean account = clientUserService.userIsExists(map.get("account"));
            if (account){
                boolean b = clientUserService.forgetPassword(map.get("newPwd"), map.get("account"));
                if (b){
                    hashMap.put("code",0);
                    hashMap.put("response","修改成功");
                }else {
                    hashMap.put("code",-1);
                    hashMap.put("response","修改失败");
                }
            }else {
                hashMap.put("code",-1);
                hashMap.put("response","账号不存在");
            }


        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }
        return hashMap;
    }
    @PostMapping("userIsExists")
    public Object userIsExists(@NotNull @RequestParam("acc") String acc){
        HashMap<String, Object> hashMap = new HashMap<>();
        try{
            boolean b = clientUserService.userIsExists(acc);
            if (b){
                hashMap.put("code",0);
                hashMap.put("response","exists");
            }else {
                hashMap.put("code",-1);
                hashMap.put("response","not exists");
            }
        }catch (Exception e){
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }
        return hashMap;
    }
}
