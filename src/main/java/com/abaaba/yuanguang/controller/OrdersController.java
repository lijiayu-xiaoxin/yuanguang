package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Addressees;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.AddresseesService;
import com.abaaba.yuanguang.service.OrdersService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    AddresseesService addresseesService;

    @ResponseBody
    @RequestMapping("/myorder/table/orders/status/{status}")
    public String getOrdersByUsername(@PathVariable("status") int status){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        JSONObject result = new JSONObject();
        List<Orders> ordersList = ordersService.getOrdersByUsername(users.getUsers_name(),status);
        result.put("code",0);
        result.put("mag","");
        result.put("count",1000);
        result.put("data",ordersList);
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/myorder/table/orders/changesd")
    public String changeOrdersStatusAndDrawback(String data,int status,int drawback){
        JSONObject json_data = (JSONObject) JSON.parse(data);
        ordersService.changeOrdersStatusAndDrawback(Integer.parseInt(json_data.getString("orders_id")),status,drawback);
        return "";
    }

    @ResponseBody
    @RequestMapping("/myorder/table/orders/changes")
    public String changeOrdersStatus(String data,int status){
        JSONObject json_data = (JSONObject) JSON.parse(data);
        ordersService.changeOrdersStatus(Integer.parseInt(json_data.getString("orders_id")),status);
        return "";
    }

    @ResponseBody
    @RequestMapping("/myorder/table/orders/changev")
    public String changeOrdersVisible(String data,int visible){
        JSONObject json_data = (JSONObject) JSON.parse(data);
        ordersService.changeOrdersVisible(Integer.parseInt(json_data.getString("orders_id")),visible);
        return "";
    }

    @RequestMapping("/user/change/{addressees_id}/{orders_id}")
    public String getChangeAddressees(@PathVariable("addressees_id") Integer addressees_id,@PathVariable("orders_id") Integer orders_id, Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        List<Addressees> addresseesList = addresseesService.getAllAddressees(users.getUsers_id());

        model.addAttribute("usersinfo",users);
        model.addAttribute("addresseesinfo",addresseesList);
        model.addAttribute("addressees_id",addressees_id);
        model.addAttribute("orders_id",orders_id);
        return "user/changeordersaddressees";
    }

    @PostMapping("/user/change")
    public String postChangeAddressees(Integer addressees_id,Integer orders_id){
        ordersService.changeOrdersAddressees(orders_id,addressees_id);
        return "redirect:/user/myorder";
    }

}
