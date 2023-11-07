package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author TaoWeiShu
 * @date: 2023/10/7  19:33
 */
@Data
@TableName("fs_client_user")
@Component
public class ClientUserEntity {
    private String id;
    private String account;
    private String password;
    private int accStatus = 1;
    private String userId;
    private String userName;
    private String phone = null;
    private String address = null;
    private String registerTime;
}
