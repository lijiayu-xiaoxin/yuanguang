package com.abaaba.yuanguang.service;

import com.abaaba.yuanguang.pojo.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersService {

    List<Orders> getAllOrdersByUsersname(String users_name,int orders_status);
    int deleteAOrdersById(int orders_id);
    int addAOrders(Orders orders);
    List<Orders> getOrdersByUsername(String users_name,int orders_status);
    Orders getAOrdersById(int orders_id);
    int changeOrdersStatus(int orders_id,int orders_status);
    int changeOrdersStatusAndDrawback(int orders_id,int orders_status,int orders_drawback);
    int changeOrdersVisible(int orders_id,int orders_visible);
    int changeAllOrdersVisible(Map map);
    int changeOrdersAddressees(int orders_id,int orders_addressees);
    List<Orders> getOrdersByAddressees(int addressees_id);
    List<Orders> getOrdersByStatus(int orders_status);

}
