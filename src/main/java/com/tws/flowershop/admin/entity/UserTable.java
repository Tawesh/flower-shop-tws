package com.tws.flowershop.admin.entity;

import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/17  14:21
 */
@Data
public class UserTable {
    private String id;
    private String account;
    private String password;
    private int accStatus=0;
    private String userId;
    private String userName;
    private String phone = null;
    private String address = null;
    private String registerTime;
}
