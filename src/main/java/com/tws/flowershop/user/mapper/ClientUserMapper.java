package com.tws.flowershop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.user.entity.ClientUserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/7  19:43
 */
@Mapper
public interface ClientUserMapper extends BaseMapper<ClientUserEntity> {


    /**
     * 搜索用户
     *
     * @param account  账号
     * @param password 密码
     * @return user对象
     */

    @Select("SELECT * FROM fs_client_user WHERE account=#{account} AND password=#{password}")
    ClientUserEntity searchUser(@Param("account") String account,
                                @Param("password") String password);

    /**
     * 添加用户
     *
     * @param clientUserEntity 用户对象
     */
    @Insert("INSERT INTO fs_client_user VALUES (#{id},#{account},#{password},#{userName},#{phone},#{address},#{registerTime},#{accStatus})")
    int insertUser(ClientUserEntity clientUserEntity);

    /**
     * 查询用户是否存在
     *
     * @param account 账号
     */

    @Select("SELECT COUNT(*) FROM fs_client_user WHERE account=#{account}")
    long userNum(@Param("account") String account);

    @Select("SELECT COUNT(*) FROM fs_client_user WHERE id=#{id}")
    long idNum(@Param("id") String id);

    @Select("SELECT * FROM fs_client_user")
    List<ClientUserEntity> adminFindAll();

    @Update("UPDATE fs_client_user SET password=#{password} WHERE account=#{account}")
    int userForgetPwd(@Param("password") String password, @Param("account") String account);

    /**
     * 管理员使用
     *
     * @param
     * @return
     */

    @Update("UPDATE fs_client_user " +
            "SET account = #{account}, password = #{password},userName=#{userName},phone=#{phone},address=#{address}, accStatus = #{accStatus} " +
            "WHERE id = #{id}")
    int updateUser(ClientUserEntity user);

    @Delete("DELETE FROM fs_client_user WHERE id = #{id}")
    int deleteUserById(String id);


    @Select("SELECT * FROM fs_client_user WHERE id=#{id}")
    ClientUserEntity adminSelectById(@Param("id") String id);


    @Select("SELECT * FROM fs_client_user WHERE account=#{account}")
    ClientUserEntity adminSelectByAcc(@Param("account") String account);
}
