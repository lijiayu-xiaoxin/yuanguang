package com.abaaba.yuanguang.service;

import com.abaaba.yuanguang.pojo.Users;

import java.util.List;

public interface UsersService {

    List<Users> getAllUsers();
    Users getAUsers(String users_name);
    int addAUsers(Users users);
    int changeAUsers(Users users);
    int delAUsersById(int users_id);
    int changePassById(int users_id,String users_password);

}
