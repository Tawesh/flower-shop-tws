package com.tws.flowershop.user.service.impl;

import com.tws.flowershop.user.entity.CartEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  11:46
 */
@Service
@Data
public class CartImpl {
    private String message;
    private Map<String, List<CartEntity>> data;
}
