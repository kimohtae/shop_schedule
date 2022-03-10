package com.person.shop_data_schedule.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfoVO {
    private Integer pi_seq;
    private String pi_name;
    private String cate_name;
    private String mfi_name;

    @Override
    public String toString(){
        return pi_seq +"|"+ pi_name +"|"+ cate_name +"|"+ mfi_name;
    }
}
