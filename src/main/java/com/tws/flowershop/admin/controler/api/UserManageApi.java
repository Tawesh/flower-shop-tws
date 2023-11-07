package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.admin.entity.UserTable;
import com.tws.flowershop.admin.mapper.AdminMapper;
import com.tws.flowershop.comment.utils.DateTimeUtil;
import com.tws.flowershop.comment.utils.RandomNumberUtil;
import com.tws.flowershop.comment.utils.TokenUtils;
import com.tws.flowershop.user.entity.ClientUserEntity;
import com.tws.flowershop.user.service.ClientUserService;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  20:45
 */
@RestController
@RequestMapping("/web/tws/fs/api/admin/data")
public class UserManageApi {

    @Resource
    private ClientUserService clientUserService;

    @Resource
    private AdminMapper adminMapper;

    @GetMapping("/userInfo")
    public Object getAll() {
        List<UserTable> userTables = adminMapper.selectManyTable();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", 0);
        hashMap.put("response", userTables);
        return hashMap;
    }

    @Resource
    private RandomNumberUtil randomNumberUtil;

    @Resource
    private TokenUtils tokenUtils;
    @Resource
    private TokenService tokenService;
    @Resource
    private DateTimeUtil dateTimeUtil;

    @PostMapping("/user/add")
    public Object addUser(@RequestParam("tk") String tk, @RequestBody Map<String, String> map) {
        System.out.println("111-" + tk);
        HashMap<String, Object> hashMap = new HashMap<>();
        String s = tk.substring(7, tk.length() - 7);
        Map<String, String> token = tokenService.findToken(s);
        boolean key = tokenService.analysisToken(token.get("key"), s);
        try {
            if (key) {
                //get account and pwd
                String account = map.get("account");
                String password = map.get("password");
                String username = map.get("userName");
                String phone = map.get("phone");
                String address = map.get("address");
                //账号不存在才注册
                boolean exists = clientUserService.userIsExists(account);
                System.out.println("用户存在吗" + exists);
                if (!exists) {
                    String id = "fs" + randomNumberUtil.generateRandomCode(15);
                    ClientUserEntity clientUserEntity = new ClientUserEntity();
                    clientUserEntity.setAccount(account);
                    clientUserEntity.setPassword(password);
                    clientUserEntity.setId(id);
                    clientUserEntity.setAddress(address);
                    clientUserEntity.setUserName(username);
                    clientUserEntity.setPhone(phone);
                    clientUserEntity.setRegisterTime(dateTimeUtil.getDateTime());
                    boolean b = clientUserService.toRegister(clientUserEntity);
                    if (b) {
                        hashMap.put("code", 0);
                        hashMap.put("message", "添加成功");
                    } else {
                        hashMap.put("code", -1);
                        hashMap.put("message", "添加失败");
                    }
                } else {
                    hashMap.put("code", -2);
                    hashMap.put("message", "添加失败，用户已存在");
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

    //查询单个用户

    @PostMapping("/user/select")
    public Object selectUserById(@RequestParam("tk") String tk, @RequestParam("id") String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            //解析tk
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                ClientUserEntity clientUserEntity = clientUserService.adminSelById(id);
                hashMap.put("code", 0);
                hashMap.put("response", clientUserEntity);
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已失效");
                hashMap.put("username", null);

            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
            hashMap.put("username", null);
        }

        return hashMap;

    }

    @PostMapping("/user/edit")
    public Object editUser(@RequestBody ClientUserEntity clientUser) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            boolean b = clientUserService.adminEditUser(clientUser);
            if (b) {
                hashMap.put("code", 0);
                hashMap.put("response", "修改成功");
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "修改失败");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务出现异常");
        }
        return hashMap;
    }

    @PostMapping("/user/delete")
    public Object delete(@RequestParam("id") String id, @RequestParam("tk") String tk) {

        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                boolean b = clientUserService.adminDeleteUser(id);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "删除成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "删除失败");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务出现异常");
        }
        return hashMap;
    }

    @PostMapping("/user/search")
    public Object searchFun(@RequestParam("acc") String acc, @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                List<ClientUserEntity> clientUserEntity = clientUserService.adminSelByAcc(acc);
                hashMap.put("code", 0);
                hashMap.put("response", clientUserEntity);
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务出现异常");
        }
        return hashMap;
    }

}
