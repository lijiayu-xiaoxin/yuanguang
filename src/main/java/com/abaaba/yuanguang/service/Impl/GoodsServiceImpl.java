package com.abaaba.yuanguang.service.Impl;

import com.abaaba.yuanguang.mapper.GoodsMapper;
import com.abaaba.yuanguang.pojo.Goods;
import com.abaaba.yuanguang.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryAllGoods() {
        return goodsMapper.queryAllGoods();
    }

    @Override
    public Goods queryAGoods(int goods_num) {
        return goodsMapper.queryAGoods(goods_num);
    }

    @Override
    public int changeGoodsSalesAndInventory(int goods_num, int goods_sales, int goods_inventory) {
        return goodsMapper.changeGoodsSalesAndInventory(goods_num,goods_sales,goods_inventory);
    }

    @Override
    public int changeGoodsExist(int goods_num) {
        return goodsMapper.changeGoodsExist(goods_num);
    }

    @Override
    public int addAGoods(Goods goods) {
        return goodsMapper.addAGoods(goods);
    }

    @Override
    public int editGoods(Goods goods) {
        return goodsMapper.editGoods(goods);
    }

}
