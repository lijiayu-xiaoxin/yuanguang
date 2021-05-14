package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Addressees;
import com.abaaba.yuanguang.pojo.Goods;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.AddresseesService;
import com.abaaba.yuanguang.service.GoodsService;
import com.abaaba.yuanguang.service.OrdersService;
import com.abaaba.yuanguang.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    UsersService usersService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    AddresseesService addresseesService;

    @RequestMapping({"/","/index"})
    public String getIndex(HttpServletRequest request, Model model){
        Cookie cookie = getCookieByName(request,"cookie_user");
        if (cookie != null) {
            String cookie_value = cookie.getValue();
            String logname = cookie_value.substring(0,cookie_value.indexOf("&"));
            Users users = usersService.getAUsers(logname);

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(logname,users.getUsers_password());
            subject.login(usernamePasswordToken);
        }

        List<Goods> goodsList = goodsService.queryAllGoods();
        model.addAttribute("goodsinfo",goodsList);
        return "index";
    }

    @RequestMapping("/user/myinfo")
    public String toMyInfo(Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        List<Addressees> addresseesList = addresseesService.getAllAddressees(users.getUsers_id());

        model.addAttribute("usersinfo",users);
        model.addAttribute("addresseesinfo",addresseesList);
        return "user/myinfo";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "/login";
    }

    @RequestMapping("/user/mycart")
    public String toMyCart(Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        List<Orders> ordersList = ordersService.getAllOrdersByUsersname(users.getUsers_name(),1);
        for (Orders orders :ordersList) {
            orders.setOrders_sum(orders.getOrders_num() * orders.getGoods().getGoods_price());
        }

        model.addAttribute("usersinfo",users);
        model.addAttribute("ordersinfo",ordersList);
        return "user/mycart";
    }

    @RequestMapping("/user/myorder")
    public String toMyOrder(Model model){
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("usersinfo",users);
        return "user/myorder";
    }

    public Cookie getCookieByName(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie :cookies){
                if (cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
