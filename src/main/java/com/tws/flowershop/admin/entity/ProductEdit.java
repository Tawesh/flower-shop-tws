package com.tws.flowershop.admin.entity;

import lombok.Data;

/**
 * @author TaoWeiShu
 * @date: 2023/10/18  16:21
 */
@Data

public class ProductEdit {
    private String id;
    private String title;
    private float price;
    private String introduce;
    private long itemLabel;

}
