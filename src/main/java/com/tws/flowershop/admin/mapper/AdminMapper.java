package com.tws.flowershop.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.admin.entity.AdminEntity;
import com.tws.flowershop.admin.entity.UserTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  18:32
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {

    @Select("SELECT COUNT(*) FROM fs_admin WHERE adminAcc=#{adminAcc} AND adminPWD=#{adminPwd}")
    long findAdmin(@Param("adminAcc") String adminAcc, @Param("adminPwd") String adminPwd);


    /**
     * 多表查询信息
     *
     * @param
     * @return
     */

    @Select("SELECT * FROM fs_client_user")
    List<UserTable> selectManyTable();
}
