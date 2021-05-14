package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Goods;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.GoodsService;
import com.abaaba.yuanguang.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UsersService usersService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping({"/login","/login.html"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/user/register")
    public String toRegister(){
        return "user/register";
    }

    @PostMapping("/index")
    public String login(String logname, String logpass, Model model, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(logname,logpass);
        try{
            subject.login(usernamePasswordToken);
            Users users = usersService.getAUsers(logname);

            if ("1".equals(users.getUsers_autho())) {
                return "redirect:/admin/index";
            } else {
                String usersimg = users.getUsers_img();
                String usersimgname = usersimg.substring(0,usersimg.indexOf('\\'));
                String usersimgsubfix = usersimg.substring(usersimg.indexOf('\\')+1);

                String msg = logname + "&" + usersimgname + "&" + usersimgsubfix;
                Cookie cookie = getCookieByName(request,"cookie_user");
                if (cookie == null) {
                    Cookie cookie_user = new Cookie("cookie_user",msg);
                    cookie_user.setMaxAge(24*60*60);
                    cookie_user.setPath("/");
                    response.addCookie(cookie_user);
                } else {
                    response.addCookie(cookie);
                }

                List<Goods> goodsList = goodsService.queryAllGoods();
                model.addAttribute("goodsinfo",goodsList);
                return "/index";
            }

        }catch (UnknownAccountException ex){
            model.addAttribute("msg","用户名错误");
            return "/login";
        }catch (IncorrectCredentialsException ex){
            model.addAttribute("msg","密码错误");
            return "/login";
        }
    }

    @RequestMapping("/admin/index")
    public String toAdminIndex(){
        return "admin/index";
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
