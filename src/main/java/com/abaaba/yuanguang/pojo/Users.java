package com.abaaba.yuanguang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private int users_id;
    private String users_name;
    private String users_gender;
    private int users_age;
    private String users_password;
    private String users_phone;
    private String users_img;
    private List<Addressees> addresseesList;
    private String users_autho;

}
