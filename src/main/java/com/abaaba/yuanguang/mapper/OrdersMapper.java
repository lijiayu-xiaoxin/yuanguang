package com.abaaba.yuanguang.mapper;

import com.abaaba.yuanguang.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrdersMapper {

    Orders getAOrdersById(int orders_id);
    List<Orders> getAllOrdersByUsersname(String users_name,int orders_status);
    int deleteAOrdersById(int orders_id);
    int addAOrders(Orders orders);
    List<Orders> getOrdersByUsername(String users_name,int orders_status);
    int changeOrdersStatus(int orders_id,int orders_status);
    int changeOrdersStatusAndDrawback(int orders_id,int orders_status,int orders_drawback);
    int changeOrdersVisible(int orders_id,int orders_visible);
    int changeOrdersAddressees(int orders_id,int orders_addressees);
    List<Orders> getOrdersByAddressees(int addressees_id);
    List<Orders> getOrdersByStatus(int orders_status);

}
