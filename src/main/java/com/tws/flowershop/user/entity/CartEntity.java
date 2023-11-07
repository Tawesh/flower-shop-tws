package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  11:39
 */
@TableName("fs_cart")
@Data
public class CartEntity {
    @TableId(value = "cartId",type = IdType.INPUT)
    private String itemId;
    private String itemTitle;
    private double itemPrice;
    private String describe;
    private long type;
    private String userId;
    private String cartId;
    private String createTime;
    private long buyN;
    private String selected;
}
