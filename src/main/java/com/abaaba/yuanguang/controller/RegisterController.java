package com.abaaba.yuanguang.controller;

import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {

    @Autowired
    UsersService usersService;

    @PostMapping("/toregister")
    public String toRegister(Users users){
        usersService.addAUsers(users);
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("uploaduserimg")
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

                String filepath = "src\\main\\resources\\static\\img\\userimg\\" + dateStr + "\\" + uuid + "." + prefix;
                File files=new File(filepath);
                System.out.println(files.getPath());
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                File newFile = new File(files.getAbsolutePath());
                System.out.println(newFile.getPath());
                file.transferTo(newFile);
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
