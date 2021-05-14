package com.abaaba.yuanguang.service.Impl;

import com.abaaba.yuanguang.mapper.AddresseesMapper;
import com.abaaba.yuanguang.pojo.Addressees;
import com.abaaba.yuanguang.service.AddresseesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddresseesServiceImpl implements AddresseesService {

    @Autowired
    AddresseesMapper addresseesMapper;

    @Override
    public List<Addressees> getAllAddressees(int addressees_users) {
        return addresseesMapper.getAllAddressees(addressees_users);
    }

    @Override
    public int deleteAddresseesById(int addressees_id) {
        return addresseesMapper.deleteAddresseesById(addressees_id);
    }

    @Override
    public int addAddressees(Addressees addressees) {
        return addresseesMapper.addAddressees(addressees);
    }

}
