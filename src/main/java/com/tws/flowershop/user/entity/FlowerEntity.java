package com.tws.flowershop.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/10  15:16
 */
@Data
@TableName("fs_flower_list_info")
public class FlowerEntity {
    private String id;
    private String title;
    private float price;
    private String imgUrl;
    private String introduce;
    private String sales="0";
    private long searchNumber=0;
    private long visit=1;
    private long itemLabel;
    private long inventory=9999;
    private long number;
}
