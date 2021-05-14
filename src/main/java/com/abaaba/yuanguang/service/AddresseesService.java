package com.abaaba.yuanguang.service;

import com.abaaba.yuanguang.pojo.Addressees;

import java.util.List;

public interface AddresseesService {

    List<Addressees> getAllAddressees(int addressees_users);
    int deleteAddresseesById(int addressees_id);
    int addAddressees(Addressees addressees);

}
