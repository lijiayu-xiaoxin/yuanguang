package com.abaaba.yuanguang.mapper;

import com.abaaba.yuanguang.pojo.Addressees;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddresseesMapper {

    List<Addressees> getAllAddressees(int addressees_users);
    int deleteAddresseesById(int addressees_id);
    int addAddressees(Addressees addressees);

}
