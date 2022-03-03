package com.person.shop_data_schedule.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingRecordVO {
    private Integer scd_mi_seq;
    private Integer scd_pi_seq;
    private Integer buy_quantity;
    private Double buy_quantity_avg;
    private Integer buy_frequncy;

    @Override
    public String toString(){
        return scd_mi_seq +","+scd_pi_seq +","+buy_quantity +","+buy_quantity_avg +","+buy_frequncy;
    }
}
