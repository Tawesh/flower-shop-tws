package com.tws.flowershop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tws.flowershop.user.entity.FlowerEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/8/1218:39
 */
@Mapper

public interface FlowerMapper extends BaseMapper<FlowerEntity> {

    /**
     * 获取商品数据，倒序
     *
     * @param number 数据id
     * @return 商品数据
     */
    @Transactional
    @Select("SELECT * FROM fs_flower_list_info WHERE number=#{number}")
    FlowerEntity selectAll(@Param("number") int number);

    @Select("SELECT * FROM fs_flower_list_info WHERE title LIKE #{key} OR introduce LIKE #{key} OR itemLabel LIKE #{key}")
    List<FlowerEntity> userSearch(@Param("key") String key);

    /**
     * 管理员使用
     *
     * @param
     * @return
     */
    @Select("SELECT * FROM fs_flower_list_info")
    List<FlowerEntity> adminFindAll();


    /**
     * 添加产品
     *
     * @param flowerEntity 花信息对象
     * @return int
     */
    @Insert("INSERT INTO fs_flower_list_info VALUES (#{id},#{title},#{price},#{introduce},#{imgUrl},#{sales},#{searchNumber},#{visit},#{itemLabel},#{inventory},#{number})")
    int adminInsertFlowerInfo(FlowerEntity flowerEntity);

    /**
     * 删除产品
     *
     * @param id 商品id
     * @return int
     */
    @Delete("DELETE FROM fs_flower_list_info WHERE id=#{id}")
    int adminDeleteFlower(@Param("id") String id);

    /**
     * 编辑商品=>更新商品
     *
     * @param productEdit 化对象
     * @return
     */

    @Update("UPDATE fs_flower_list_info SET title=#{title},price=#{price},introduce=#{introduce},itemLabel=#{itemLabel} WHERE id=#{id}")
    int adminUpdateFlower(
            @Param("title") String title,
            @Param("price") float price,
            @Param("introduce") String introduce,
            @Param("itemLabel") long itemLabel,
            @Param("id") String id
    );


    /**
     * 查询
     *
     * @param
     * @return
     */

    @Select("SELECT * FROM fs_flower_list_info WHERE title OR id LIKE #{title} AND itemLabel LIKE #{itemLabel}")
    List<FlowerEntity> adminSearchProduct(@Param("title") String title, @Param("itemLabel") String itemLabel);


    @Delete("DELETE FROM fs_flower_list_info WHERE id IN (#{idList})")
    void deleteByIdList(@Param("idList") List<String> idList);


    @Select("SELECT COUNT(*) FROM fs_flower_list_info WHERE id=#{id} ")
    int itemIsExists(@Param("id") String id);

}
