package com.tws.flowershop.user.service.impl;

import com.tws.flowershop.user.entity.OrderEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/12  20:24
 */
@Service
@Data
public class OrderImpl {
    private String message;
    private List<OrderEntity> data;
}