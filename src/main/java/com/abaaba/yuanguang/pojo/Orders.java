package com.abaaba.yuanguang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private int orders_id;
    private int orders_goods;
    private int orders_users;
    private int orders_status;
    private String orders_time;
    private int orders_addressees;
    private int orders_num;
    private float orders_sum;
    private String orders_drawback;
    private String orders_visible;

    private Goods goods;
    private Addressees addressees;
    private Users users;

    public int getGoods_num(){
        return goods.getGoods_num();
    }
    public String getGoods_img(){
        return goods.getGoods_img();
    }
    public String getGoods_name(){
        return goods.getGoods_name();
    }
    public String getGoods_intro(){
        return goods.getGoods_intro();
    }
    public int getGoods_inventory(){
        return goods.getGoods_inventory();
    }
    public int getGoods_sales(){
        return goods.getGoods_sales();
    }
    public float getGoods_price(){
        return goods.getGoods_price();
    }
    public String getGoods_exist() {
        return goods.getGoods_exist();
    }

    public int getAddressees_id(){
        return addressees.getAddressees_id();
    }
    public String getAddressees_name(){
        return addressees.getAddressees_name();
    }
    public int getAddressees_users(){
        return addressees.getAddressees_users();
    }
    public String getAddressees_address(){
        return addressees.getAddressees_address();
    }
    public String getAddressees_phone(){
        return addressees.getAddressees_phone();
    }

    public int getUsers_id() {
        return users.getUsers_id();
    }
    public String getUsers_name() {
        return users.getUsers_name();
    }
    public String getUsers_phone() {
        return users.getUsers_phone();
    }

}
