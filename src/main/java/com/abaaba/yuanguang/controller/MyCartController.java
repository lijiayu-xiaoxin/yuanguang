package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.GoodsService;
import com.abaaba.yuanguang.service.OrdersService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyCartController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/user/orders/delete/{orders_id}")
    public String usersDeleteGoods(@PathVariable("orders_id") int ordersId){
        ordersService.deleteAOrdersById(ordersId);
        return "redirect:/user/mycart";
    }

    @RequestMapping("/user/orders/buy/{orders_id}")
    public String usersBuyGoods(@PathVariable("orders_id") int orders_id, Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        Orders orders = ordersService.getAOrdersById(orders_id);

        model.addAttribute("usersinfo",users);
        model.addAttribute("orders",orders);
        return "goods/orderspay";
    }


    @RequestMapping("/user/orders/pay/{orders_id}")
    public String usersPayGoods(@PathVariable("orders_id") int orders_id,Model model){
        System.out.println(orders_id);
        Orders orders = ordersService.getAOrdersById(orders_id);
//        如果库存不够的话跳回我的购物车界面
        if (orders.getGoods_inventory()-orders.getOrders_num() < 0) {
            Users users = (Users) SecurityUtils.getSubject().getPrincipal();
            List<Orders> ordersList = ordersService.getAllOrdersByUsersname(users.getUsers_name(),1);
            for (Orders order :ordersList) {
                orders.setOrders_sum(order.getOrders_num() * order.getGoods().getGoods_price());
            }

            model.addAttribute("usersinfo",users);
            model.addAttribute("ordersinfo",ordersList);
            model.addAttribute("inventory","没有足够的库存，请重新购买");
            return "user/mycart";
        } else if ("1".equals(orders.getGoods_exist())) {
            Users users = (Users) SecurityUtils.getSubject().getPrincipal();
            List<Orders> ordersList = ordersService.getAllOrdersByUsersname(users.getUsers_name(),1);
            for (Orders order :ordersList) {
                orders.setOrders_sum(order.getOrders_num() * order.getGoods().getGoods_price());
            }

            model.addAttribute("usersinfo",users);
            model.addAttribute("ordersinfo",ordersList);
            model.addAttribute("inventory","商品已下架请重新购买");
        }
        goodsService.changeGoodsSalesAndInventory(orders.getGoods_num(),orders.getGoods_sales()+orders.getOrders_num(),orders.getGoods_inventory()-orders.getOrders_num());
        ordersService.changeOrdersStatus(orders_id ,3);
        return "redirect:/user/myorder";
    }

    @RequestMapping("/user/orders/cancel/{orders_id}")
    public String usersCancelGoods(@PathVariable("orders_id") int orders_id){
        ordersService.changeOrdersStatus(orders_id,7);
        return "redirect:/user/myorder";
    }
}
