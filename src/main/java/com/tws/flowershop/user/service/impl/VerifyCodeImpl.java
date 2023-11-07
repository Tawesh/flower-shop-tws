package com.tws.flowershop.user.service.impl;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author TaoWeiShu
 * @date: 2023/10/8  14:41
 */
@Service
@Data
public class VerifyCodeImpl {
    /**
     * 验证码类型
    */
    private String type;
    /**
     * 验证码
    */
    private String data;
    /**
     * 消息
    */
    private String msg;
}
