package com.tws.flowershop.admin.service.impl;

import lombok.Data;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author TaoWeiShu
 * @date: 2023/10/16  14:30
 */
@Service
@Data
public class AdminImpl {
    private String msg;
    private Object data;
}
