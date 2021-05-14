package com.abaaba.yuanguang.service.Impl;

import com.abaaba.yuanguang.mapper.UsersMapper;
import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

    @Override
    public Users getAUsers(String users_name) {
        return usersMapper.getAUsers(users_name);
    }

    @Override
    public int addAUsers(Users users) {
        return usersMapper.addAUsers(users);
    }

    @Override
    public int changeAUsers(Users users) {
        return usersMapper.changeAUsers(users);
    }

    @Override
    public int delAUsersById(int users_id) {
        return usersMapper.delAUsersById(users_id);
    }

    @Override
    public int changePassById(int users_id, String users_password) {
        return usersMapper.changePassById(users_id,users_password);
    }

}
