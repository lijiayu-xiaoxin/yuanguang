package com.abaaba.yuanguang.mapper;

import com.abaaba.yuanguang.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {

    List<Goods> queryAllGoods();
    Goods queryAGoods(int goods_num);
    int changeGoodsSalesAndInventory(int goods_num,int goods_sales,int goods_inventory);
    int changeGoodsExist(int goods_num);
    int addAGoods(Goods goods);
    int editGoods(Goods goods);

}
