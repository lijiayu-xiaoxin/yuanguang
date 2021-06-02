package com.abaaba.yuanguang.service;

import com.abaaba.yuanguang.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {

    List<Users> getAllUsers();
    Users getAUsers(String users_name);
    int addAUsers(Users users);
    int changeAUsers(Users users);
    int delAUsersById(int users_id);
    int delAllUsersById(Map map);
    int changePassById(int users_id,String users_password);

}
