package com.tws.flowershop.user.service.impl;

import com.tws.flowershop.user.entity.FlowerEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/10  16:00
 */
@Service
@Data
public class FlowerImpl {
    private String message;
    private List<FlowerEntity> data;
}
