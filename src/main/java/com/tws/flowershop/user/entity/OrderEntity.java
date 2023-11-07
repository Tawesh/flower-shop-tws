package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/11  15:44
 */
@TableName("fs_order")
@Data
public class OrderEntity {
    @TableId
    private String orderId;
    private String itemTitle;
    private String userId;
    private float  price;
    private int    number;
    private String itemId;
    private int    total;
    private int    payStatus;
    private int    orderStatus;
    private String address;
    private String phone;
    private String changeDate;
}
