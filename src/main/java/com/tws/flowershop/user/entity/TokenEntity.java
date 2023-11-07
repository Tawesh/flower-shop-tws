package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author:Tws
 * @Date:2023/8/17-16:57
 */
@Data
@TableName("fs_token")
public class TokenEntity {

    /**
    * Function Of Method:
     * token的模型类，记录随机生成的token 秘钥
    */
    private String id;
    private String key;
    private String token;
    private Boolean overdue = false;
}
