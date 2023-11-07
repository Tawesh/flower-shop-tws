package com.tws.flowershop.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  18:29
 */
@Data
@TableName("fs_admin")
public class AdminEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String adminAcc;
    private String adminPwd;
}
