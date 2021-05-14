package com.abaaba.yuanguang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addressees {

    private int addressees_id;
    private String addressees_name;
    private int addressees_users;
    private String addressees_address;
    private String addressees_phone;

}
