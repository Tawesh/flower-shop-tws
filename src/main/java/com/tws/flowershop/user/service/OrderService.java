package com.tws.flowershop.user.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.tws.flowershop.user.entity.OrderEntity;
import com.tws.flowershop.user.mapper.OrderMapper;
import com.tws.flowershop.user.service.impl.OrderImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/12  20:23
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderImpl order;

    public OrderImpl selById(String userId) {
        try {
            List<OrderEntity> orderEntities = orderMapper.selByUId(userId);
            order.setMessage("success");
            order.setData(orderEntities);
        } catch (NullPointerException e) {
            order.setMessage("fail");
            order.setData(null);
        }
        return order;
    }

    public boolean rePay(int payNum,String orderId){
        try{
            orderMapper.changePayStatus(payNum,orderId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 添加
     *
     * @param orderEntity 订单对象
     */
    public boolean addInfo(OrderEntity orderEntity) {
        try {
            int i = orderMapper.addOrderinfo(orderEntity);
            return true;
        } catch (Exception e) {
            System.out.println("添加信息时出现错误" + e);
            return false;
        }
    }

    public List<OrderEntity> adminFindAll() {
        try {
            return orderMapper.adminFindAll();
        } catch (Exception e) {
            System.out.println("管理员查询所有订单数据时出现异常" + e);
            return null;
        }
    }

    /**
     * 订单统计==>销量统计
     *
     * @param
     * @return
     */
    public long adminOrderCount() {
        try {
            return orderMapper.adminOrderCount();
        } catch (Exception e) {
            return 0;
        }
    }

    //管理员只能修改订单状态
    public boolean adminEditOrder(String orderId, String phone, String address) {
        try {
            orderMapper.adminEditAddressPhone(phone, address, orderId);
            return true;
        } catch (Exception e) {
            System.out.println("管理员编辑订单信息时发生异常" + e);
            return false;
        }
    }

    public List<OrderEntity> adminSelByOrdId(String orderId) {
        try {
            return orderMapper.adminSelectByOrderId(orderId);
        } catch (Exception e) {
            return null;
        }
    }


    public boolean adminDeleteOrder(String orderId) {
        try {
            orderMapper.deteteOrder(orderId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
