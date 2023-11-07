package com.tws.flowershop.user.service;

import com.tws.flowershop.comment.utils.TokenUtils;
import com.tws.flowershop.user.entity.TokenEntity;
import com.tws.flowershop.user.mapper.TokenMapper;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author:Tws
 * @Date:2023/8/17-17:07
 */
@Service
public class TokenService {

    @Resource
    private TokenMapper tokenMapper;

    @Resource
    private TokenUtils tokenUtils;

    /**
     * 查询token ====>>>键值形式返回
     *
     * @param token token令牌
     * @return token信息map对象 ，含key
     */
    public Map<String, String> findToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        try {
            TokenEntity tokenKey = tokenMapper.findTokenKey(token);
            map.put("key", tokenKey.getKey());
            map.put("token", tokenKey.getToken());
            return map;
        } catch (NullPointerException nullPointerException) {
            map.put("key", null);
            map.put("token", null);
            return map;
        }
    }

    /**
     * 添加token信息
     *
     * @param token token信息对象
     * @return true / false
     */

    public Boolean addToken(TokenEntity token) {
        //不成功即失败
        try {
            int insert = tokenMapper.insertToken(token.getKey(), token.getToken(), token.getOverdue().toString());
            return true;
        } catch (Exception e) {
            System.out.println("exception is" + e);
            return false;
        }

    }
    //解析token

    /**
     * @param key   秘钥
     * @param token 令牌
     * @return true/false
     */
    public boolean analysisToken(String key, String token) {

        return tokenUtils.validateJwt(key, token);

    }


    /**
     * 删除token
     *
     * @param
     * @return
     */

    public boolean deleteToken(String token) {
        try {
            tokenMapper.deleteToken(token);
            return true;
        } catch (Exception e) {
            System.out.println("删除token出现异常"+e);
            return false;
        }
    }
}
