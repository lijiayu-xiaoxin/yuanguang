package com.abaaba.yuanguang.service.Impl;

import com.abaaba.yuanguang.mapper.OrdersMapper;
import com.abaaba.yuanguang.pojo.Orders;
import com.abaaba.yuanguang.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orders> getAllOrdersByUsersname(String users_name, int orders_status) {
        return ordersMapper.getAllOrdersByUsersname(users_name,orders_status);
    }

    @Override
    public int deleteAOrdersById(int orders_id) {
        return ordersMapper.deleteAOrdersById(orders_id);
    }

    @Override
    public int addAOrders(Orders orders) {
        return ordersMapper.addAOrders(orders);
    }

    @Override
    public List<Orders> getOrdersByUsername(String users_name, int orders_status) {
        return ordersMapper.getOrdersByUsername(users_name,orders_status);
    }

    @Override
    public Orders getAOrdersById(int orders_id) {
        return ordersMapper.getAOrdersById(orders_id);
    }

    @Override
    public int changeOrdersStatus(int orders_id, int orders_status) {
        return ordersMapper.changeOrdersStatus(orders_id,orders_status);
    }

    @Override
    public int changeOrdersStatusAndDrawback(int orders_id, int orders_status, int orders_drawback) {
        return ordersMapper.changeOrdersStatusAndDrawback(orders_id,orders_status,orders_drawback);
    }

    @Override
    public int changeOrdersVisible(int orders_id, int orders_visible) {
        return ordersMapper.changeOrdersVisible(orders_id,orders_visible);
    }

    @Override
    public int changeOrdersAddressees(int orders_id, int orders_addressees) {
        return ordersMapper.changeOrdersAddressees(orders_id,orders_addressees);
    }

    @Override
    public List<Orders> getOrdersByAddressees(int addressees_id) {
        return ordersMapper.getOrdersByAddressees(addressees_id);
    }

    @Override
    public List<Orders> getOrdersByStatus(int orders_status) {
        return ordersMapper.getOrdersByStatus(orders_status);
    }

}
