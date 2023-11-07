package com.tws.flowershop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.user.entity.CartEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/26  11:42
 */
@Mapper
public interface CartMapper extends BaseMapper<CartEntity> {

    @Select("SELECT COUNT(*) FROM fs_cart WHERE userId=#{userId}")
    int findUserCart(@Param("userId") String userId);

    @Select("SELECT * FROM fs_cart WHERE userId=#{userId} AND type=#{type}")
    List<CartEntity> findUserCartType(@Param("userId") String userId,
                                      @Param("type") int type);

    @Delete("DELETE FROM fs_cart WHERE cartId=#{cartId}")
    int deleteCartByOne(@Param("cartId") String cartId);

    @Insert("INSERT INTO fs_cart VALUES (#{cartId},#{userId},#{itemId},#{itemTitle},#{itemPrice},#{describe},#{type},#{createTime},#{buyN},#{selected})")
    int insertCartByOne(CartEntity cart);

}
