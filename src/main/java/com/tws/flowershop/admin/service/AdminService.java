package com.tws.flowershop.admin.service;

import com.tws.flowershop.admin.mapper.AdminMapper;
import com.tws.flowershop.admin.service.impl.AdminImpl;
import com.tws.flowershop.comment.utils.TokenUtils;
import com.tws.flowershop.user.entity.TokenEntity;
import com.tws.flowershop.user.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  18:38
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminImpl adminImpl;
    @Resource
    private TokenUtils tokenUtils;
    /**
     * 搜索用户
     *
     * @param account  账号
     * @param password 密码
     * @return
     */

    @Resource
    private TokenService tokenService;

    /**
     * 查找管理员账户
     *
     * @param account 管理员账号
     * @param pwd     密码
     * @return true/false
     */
    public AdminImpl findAdmin(String account, String pwd) {
        try {
            long admin = adminMapper.findAdmin(account, pwd);
            if (admin > 0) {
                //生成token
                Map<String, String> token = tokenUtils.setToken("admin", "admin", "admin");
                //将token存到数据库
                TokenEntity tokenEntity = new TokenEntity();
                tokenEntity.setToken(token.get("token"));
                tokenEntity.setKey(token.get("key"));
                tokenService.addToken(tokenEntity);
                adminImpl.setMsg("success");
                HashMap<String, Object> hashMap = new HashMap<>();
                String fsToken = "FsAdmin" + token.get("token") + "fSaDMIN";
                hashMap.put("token", fsToken);
                hashMap.put("user", "admin");
                adminImpl.setData(hashMap);
            } else {
                adminImpl.setMsg("fail");
                adminImpl.setData(null);
            }
            return adminImpl;
        } catch (Exception e) {
            adminImpl.setMsg("fail");
            adminImpl.setData(null);
            return adminImpl;
        }
    }
}
