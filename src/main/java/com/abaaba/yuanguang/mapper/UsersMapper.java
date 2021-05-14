package com.abaaba.yuanguang.mapper;

import com.abaaba.yuanguang.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper {

    List<Users> getAllUsers();
    Users getAUsers(String users_name);
    int addAUsers(Users users);
    int changeAUsers(Users users);
    int delAUsersById(int users_id);
    int changePassById(int users_id,String users_password);

}
