package com.tws.flowershop.user.service.impl;

import com.tws.flowershop.user.entity.ClientUserEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author TaoWeiShu
 * @date: 2023/10/7  19:45
 */
@Service
@Data
public class ClientUserImpl {
    /**
     * msg 消息
    */
    private String msg;
    /**
     * clientUserEntity 用户信息
    */
    private String username;
    private String userId;
    private String token;
    private String timestamp;
    private String account;
}
