package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Addressees;
import com.abaaba.yuanguang.pojo.Goods;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.AddresseesService;
import com.abaaba.yuanguang.service.GoodsService;
import com.abaaba.yuanguang.service.OrdersService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    AddresseesService addresseesService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/goods/detail/{goods_num}")
    public String toGoodsDetail(@PathVariable("goods_num") int goodsNum, Model model){
        Goods goods = goodsService.queryAGoods(goodsNum);
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null){
            users = new Users();
        }

        model.addAttribute("usersinfo",users);
        model.addAttribute("goodsinfo",goods);

        return "goods/goodsdetail";
    }

    @RequestMapping("/goods/buygood/{goods_num}")
    public String toBuyPage(@PathVariable("goods_num") int goodsNum,Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        List<Addressees> addresseesList = addresseesService.getAllAddressees(users.getUsers_id());

        model.addAttribute("goods_num",goodsNum);
        model.addAttribute("usersinfo",users);
        model.addAttribute("addresseesinfo",addresseesList);
        return "goods/buyPage";
    }

    @PostMapping("/goods/ordergood")
    public String orderGood(int goods_num,int adressees_users,int orders_num){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str_time = simpleDateFormat.format(date);
        Goods goods = goodsService.queryAGoods(goods_num);
        Orders orders = new Orders(0,goods_num,users.getUsers_id(),1,str_time,adressees_users,orders_num,orders_num*goods.getGoods_price(),"0","1",null,null,null);
        ordersService.addAOrders(orders);
        return "redirect:/user/mycart";
    }

}
