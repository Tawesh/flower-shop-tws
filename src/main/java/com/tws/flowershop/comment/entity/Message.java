package com.tws.flowershop.comment.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author TaoWeiShu
 * @date: 2023/9/14  20:36
 */
@Data
@Component
public class Message {
    /**
     * 状态码
    */
    private int code;
    /**
     * 消息
     */
    private String msg;


}
