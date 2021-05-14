package com.abaaba.yuanguang.service;

import com.abaaba.yuanguang.pojo.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryAllGoods();
    Goods queryAGoods(int goods_num);
    int changeGoodsSalesAndInventory(int goods_num,int goods_sales,int goods_inventory);
    int changeGoodsExist(int goods_num);
    int addAGoods(Goods goods);

}
