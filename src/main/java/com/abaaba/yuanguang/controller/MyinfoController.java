package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Addressees;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.AddresseesService;
import com.abaaba.yuanguang.service.OrdersService;
import com.abaaba.yuanguang.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyinfoController {

    @Autowired
    AddresseesService addresseesService;

    @Autowired
    UsersService usersService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/users/changeaddressees")
    public String changeAddresses(Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        List<Addressees> addresseesList = addresseesService.getAllAddressees(users.getUsers_id());

        model.addAttribute("usersinfo",users);
        model.addAttribute("addresseesinfo",addresseesList);
        return "user/changeaddressees";
    }

    @RequestMapping("/user/addressees/delete/{addressees_id}")
    public String deleteAddresses(@PathVariable("addressees_id") int addressees_id,Model model){
        List<Orders> ordersList = ordersService.getOrdersByAddressees(addressees_id);
        if (ordersList.size()>0){
            Users users = (Users) SecurityUtils.getSubject().getPrincipal();
            List<Addressees> addresseesList = addresseesService.getAllAddressees(users.getUsers_id());

            model.addAttribute("usersinfo",users);
            model.addAttribute("addresseesinfo",addresseesList);
            model.addAttribute("msg","不能删除该收件人信息，因为还有订单与该收件人绑定！");
            return "user/changeaddressees";
        }
        addresseesService.deleteAddresseesById(addressees_id);
        return "redirect:/users/changeaddressees";
    }

    @RequestMapping("/user/addressees/add")
    public String addAddresses(Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();

        model.addAttribute("usersinfo",users);
        return "user/addaddressees";
    }

    @RequestMapping("/user/addressees/postaddressees")
    public String postAddressees(Model model,String addressees_name,String addressees_address,String addressees_phone){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();

        Addressees addressees = new Addressees(0,addressees_name, users.getUsers_id(), addressees_address,addressees_phone);
        addresseesService.addAddressees(addressees);
        model.addAttribute("usersinfo",users);
        return "redirect:/users/changeaddressees";
    }

    @RequestMapping("/user/postchangeinfo")
    public String postChangeInfo(String users_name,int users_age,String users_phone,String users_gender){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();

        users.setUsers_name(users_name);
        users.setUsers_age(users_age);
        users.setUsers_gender(users_gender);
        users.setUsers_phone(users_phone);
        usersService.changeAUsers(users);
        return "redirect:/user/myinfo";
    }

}
