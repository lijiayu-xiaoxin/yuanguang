package com.abaaba.yuanguang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private int goods_num;
    private String goods_img;
    private String goods_name;
    private String goods_intro;
    private int goods_inventory;
    private int goods_sales;
    private float goods_price;
    private String goods_exist;

}
