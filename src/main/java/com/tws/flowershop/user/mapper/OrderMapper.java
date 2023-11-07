package com.tws.flowershop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.user.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/12  19:59
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

    //添加订单

    /**
     * @param orderEntity 订单对象
     */
    @Insert("INSERT INTO fs_order VALUES (#{orderId},#{itemId},#{userId},#{itemTitle},#{address},#{phone},#{price},#{number},#{total},#{changeDate},#{payStatus},#{orderStatus})")
    int addOrderinfo(OrderEntity orderEntity);


    /**
     * 查询订单
     *
     * @param userId 用户id
     */
    @Select("SELECT * FROm fs_order WHERE userId=#{userId}")
    List<OrderEntity> selByUId(@Param("userId") String userId);

    /**
     * 查询订单
     *
     * @param orderId 订单id
     */
    @Select("SELECT * FROm fs_order WHERE orderId=#{orderid}")
    OrderEntity selByOrdId(@Param("orderId") String orderId);

    /**
     * 修改支付状态
     *
     * @param payNum  支付状态的数字
     * @param orderId 订单id
     */
    @Update("UPDATE fs_order SET payStatus=#{payNum} WHERE orderId=#{orderId}")
    int changePayStatus(@Param("payNum") int payNum, @Param("orderId") String orderId);

    /**
     * 修改订单状态
     * <p>
     * 支付状态的数字
     *
     * @param orderId 订单id
     */
    @Update("UPDATE fs_order SET orderStatus=1 WHERE orderId=#{orderId}")
    int changeOrderStatus(@Param("orderId") String orderId);


    /**
     * 删除订单
     *
     * @param orderId 订单id
     */
    @Delete("DELETE FROM fs_order WHERE orderId=#{orderId}")
    int deteteOrder(@Param("orderId") String orderId);


    /**
     * 管理员
     *
     * @param
     * @return
     */
    @Select("SELECT * FROM fs_order")
    List<OrderEntity> adminFindAll();

    @Select("SELECT COUNT(*) FROM fs_order")
    long adminOrderCount();

    @Select("SELECT * FROM fs_order WHERE orderId=#{orderId} OR userId=#{orderId}")
    List<OrderEntity> adminSelectByOrderId(@Param("orderId") String orderId);

    @Update("UPDATE fs_order SET phone=#{phone},address=#{address} WHERE orderId=#{orderId}")
    int adminEditAddressPhone(@Param("phone") String phone, @Param("address") String address, @Param("orderId") String orderId);
}
