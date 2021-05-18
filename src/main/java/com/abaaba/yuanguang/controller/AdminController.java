package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Goods;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.GoodsService;
import com.abaaba.yuanguang.service.OrdersService;
import com.abaaba.yuanguang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    UsersService usersService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/admin")
    public String toAdminIndex(){
        return "/admin/index";
    }

    @RequestMapping("/touserlist")
    public String toUserList(Model model){
        List<Users> usersList = usersService.getAllUsers();
        model.addAttribute("usersinfo",usersList);
        return "/admin/userlist";
    }

    @RequestMapping("/member-edit/{goods_num}")
    public String toMemberEdit(@PathVariable("goods_num") int goods_num, Model model){
        model.addAttribute("goods_num",goods_num);
        return "/admin/member-edit";
    }

    @RequestMapping("/member-password/{users_id}")
    public String toMemberPassword(@PathVariable("users_id") int users_id,Model model){
        model.addAttribute("users_id",users_id);
        return "/admin/member-password";
    }

    @ResponseBody
    @RequestMapping("/admin/users/del")
    public String adminDelUsers(int id){
        usersService.delAUsersById(id);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/users/delall")
    public String adminDelAllUsers(int[] ids){
        // 这里用sql语句一次性删完可能比较好一点
        for (int i = 0;i<ids.length;i++){
            usersService.delAUsersById(ids[i]);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/user/changepass")
    public String changeUsersPass(String[] data){
        usersService.changePassById(Integer.parseInt(data[0]),data[1]);
        return "";
    }

    @RequestMapping("/togoodlist")
    public String toGoodList(Model model){
        List<Goods> goodsList = goodsService.queryAllGoods();
        model.addAttribute("goodsinfo",goodsList);
        return "/admin/goodlist";
    }

    @RequestMapping("/toaddgood")
    public String toAddGood(){
        return "/admin/addgood";
    }

    @RequestMapping("/toorderlist/{status}")
    public String toOrderList(@PathVariable("status") int status, Model model){
        List<Orders> ordersList = ordersService.getOrdersByStatus(status);
        model.addAttribute("ordersinfo",ordersList);
        model.addAttribute("orderstatus",status);
        return "/admin/orderlist";
    }

    @PostMapping("/admin/orders/search")
    public String adminOrdersSearch(int orders_id,int orders_status,Model model){
        Orders orders = ordersService.getAOrdersById(orders_id);
        if (orders.getOrders_status() == orders_status) {
            List<Orders> ordersList = new ArrayList<>();
            ordersList.add(orders);
            model.addAttribute("ordersinfo",ordersList);
        }
        return "/admin/orderlist";
    }

    @ResponseBody
    @RequestMapping("/admin/goods/del")
    public String adminDelGoods(int num){
        goodsService.changeGoodsExist(num);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/goods/delall")
    public String adminDelAllGoods(Model model,int[] nums){
        for (int i = 0;i<nums.length;i++){
            goodsService.changeGoodsExist(nums[i]);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/delall")
    public String adminDelAllOrders(Model model,int[] ids){
        for (int i = 0;i<ids.length;i++){
            ordersService.changeOrdersVisible(ids[i],0);
        }
        return "";
    }

    @PostMapping("/admin/goods/add")
    public String adminAddGoods(String goods_name,String goods_intro,float goods_price,int goods_inventory,String goods_img){
        Goods goods = new Goods(0,goods_img,goods_name,goods_intro,goods_inventory,0,goods_price,"1");
        goodsService.addAGoods(goods);
        return "redirect:/togoodlist";
    }

    @ResponseBody
    @PostMapping("/admin/goods/edit")
    public String adminEditGoods(int goods_num,String goods_name,String goods_intro,int goods_inventory,float goods_price){
        Goods goods = new Goods(goods_num,"",goods_name,goods_intro,goods_inventory,0,goods_price,"1");
        goodsService.editGoods(goods);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/deliver")
    public String adminOrdersDeliver(int id){
        ordersService.changeOrdersStatus(id,4);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/cancel")
    public String adminOrdersCancel(int id){
        ordersService.changeOrdersStatusAndDrawback(id,7,1);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/confirm")
    public String adminOrdersConfirm(int id){
        ordersService.changeOrdersStatus(id,5);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/return")
    public String adminOrdersReturn(int id){
        ordersService.changeOrdersStatus(id,6);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/back")
    public String adminOrdersBack(int id){
        ordersService.changeOrdersStatusAndDrawback(id,7,1);
        return "";
    }

    @ResponseBody
    @RequestMapping("/admin/orders/drawback")
    public String adminOrdersDrawback(int id){
        ordersService.changeOrdersStatusAndDrawback(id,8,0);
        return "";
    }

    @ResponseBody
    @RequestMapping("uploadgoodimg")
    public Map upload(MultipartFile file){
        String prefix="";
        String dateStr="";
        OutputStream out = null;
        InputStream fileInput = null;
        try{
            if(file!=null){

//                生成唯一UUID
                String uuid = UUID.randomUUID() + "";
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = dateFormat.format(date);

                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
//                最好改成相对路径
                String filepath = "C:\\Users\\Administrator\\IdeaProjects\\yuanguang\\src\\main\\resources\\static\\img\\goodimg\\" + dateStr + "\\" + uuid + "." + prefix;
                File files=new File(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2 = new HashMap<>();
                Map<String,Object> map = new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src",dateStr + "\\" + uuid + "." + prefix);
                return map;
            }
        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
}
