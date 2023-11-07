package com.tws.flowershop.user.service;

import com.tws.flowershop.admin.entity.ProductEdit;
import com.tws.flowershop.user.entity.FlowerEntity;
import com.tws.flowershop.user.mapper.FlowerMapper;
import com.tws.flowershop.user.service.impl.FlowerImpl;
import jakarta.annotation.Resource;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TaoWeiShu
 * @date: 2023/10/10  15:57
 */
@Service
public class FlowerService {

    @Resource
    private FlowerMapper flowerMapper;

    @Resource
    private FlowerImpl flowerList;

    /**
     * @param 查询数据的条数
     */
    @Transactional
    public FlowerImpl getData(int start, int end) {
        try {
            ArrayList<FlowerEntity> flowerEntities = new ArrayList<>();
            if (start < end) {
                for (int i = start; i <= end; i++) {
                    FlowerEntity flowerListEntities = flowerMapper.selectAll(i);
                    flowerEntities.add(flowerListEntities);
                }
            }

            flowerList.setMessage("success");
            flowerList.setData(flowerEntities);
        } catch (Exception e) {
            flowerList.setMessage("fail");
            flowerList.setData(null);
        }
        return flowerList;
    }

    public List<FlowerEntity> userSearch(String key) {
        try {
            return flowerMapper.userSearch("%" + key + "%");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按照商品id查询数据
     *
     * @param id 商品id
     */
    public FlowerImpl getFlowerInfoById(String id) {
        try {
            FlowerEntity flowerEntity = flowerMapper.selectById(id);
            if (flowerEntity != null) {
                flowerList.setMessage("success");
                List<FlowerEntity> goodsList = new ArrayList<>();
                goodsList.add(flowerEntity);
                flowerList.setData(goodsList);
            }

        } catch (Exception e) {
            flowerList.setMessage("fail");
            flowerList.setData(null);
        }
        return flowerList;
    }


    /**
     * 管理员使用
     *
     * @param
     * @return
     */

    public List<FlowerEntity> adminFindAll() {
        try {
            return flowerMapper.adminFindAll();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 添加商品
     *
     * @param
     * @return
     */
    public boolean adminAddProduct(FlowerEntity flowerEntity) {
        try {
            int i = flowerMapper.adminInsertFlowerInfo(flowerEntity);
            return true;
        } catch (Exception e) {
            System.out.println("add product exits exception +" + e);
            return false;
        }
    }

    /**
     * 编辑产品
     *
     * @param
     * @return
     */

    @Transactional
    public boolean adminEditProduct(ProductEdit flowerEntity) {
        try {
            flowerMapper.adminUpdateFlower(
                    flowerEntity.getTitle(),
                    flowerEntity.getPrice(),
                    flowerEntity.getIntroduce(),
                    flowerEntity.getItemLabel(),
                    flowerEntity.getId()
            );
            return true;
        } catch (MyBatisSystemException e) {
            System.out.println("管理员编辑商品时出现异常+" + e);
            return false;
        }
    }

    /**
     * 删除产品
     *
     * @param
     * @return
     */
    public boolean adminDeleteProduct(String itemId) {
        try {
            int i = flowerMapper.adminDeleteFlower(itemId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询产品
     *
     * @param
     * @return
     */
    public List<FlowerEntity> adminSearchProduct(String title, String label) {
        try {
            return flowerMapper.adminSearchProduct("%" + title + "%", "%" + label + "%");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 批量删除数据
     *
     * @param
     * @return
     */
    public boolean adminDeleteByIdList(List<String> idList) {
        try {
            flowerMapper.deleteByIdList(idList);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean itemIsExists(String itemId) {
        return flowerMapper.itemIsExists(itemId) == 1;
    }
}
