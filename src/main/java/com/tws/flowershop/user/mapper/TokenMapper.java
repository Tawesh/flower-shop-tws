package com.tws.flowershop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.user.entity.TokenEntity;
import org.apache.ibatis.annotations.*;

/**
 * @Author:Tws
 * @Date:2023/8/17-17:06
 */
@Mapper
public interface TokenMapper extends BaseMapper<TokenEntity> {

    /**
     * 1. 查询token对应的key
     */
    @Select("SELECT * FROM fs_token WHERE token=#{token}")
    public TokenEntity findTokenKey(
            @Param("token") String token
    );

    /**
     * Function Of Method:更新是否到期
     */
    @Update("UPDATE fs_token SET overdue='false' WHERE token=#{token}")
    public Integer updateOverdue(@Param("token") String token);


    /**
     * Function Of Method:自定义添加数据的方法
     */
    @Insert("INSERT INTO fs_token (`key`, token, overdue) VALUES (#{key},#{token},#{overdue})")
    public Integer insertToken(
            @Param("key") String key,
            @Param("token") String token,
            @Param("overdue") String overdue
    );

    @Delete("DELETE FROM fs_token WHERE token=#{token}")
    int deleteToken(@Param("token") String token);

}
