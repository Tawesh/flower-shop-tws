package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author TaoWeiShu
 * @date: 2023/10/9  15:28
 */
@Data
@TableName("fs_client_user_info")
@Component
public class ClientUserInfoEntity {
    @TableId("userId")
    private String userId;
    private String userName;
    private String phone = null;
    private String address = null;
    private String registerTime;
}
