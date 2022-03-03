package com.person.shop_data_schedule.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberProdVO {
    private Integer mph_seq;
    private Integer mph_mi_seq;
    private Integer mph_pi_seq;
    private Double mph_interval;
    private Date mph_reg_dt;

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mph_mi_seq +","+ mph_pi_seq +","+ mph_interval +","+ formatter.format(mph_reg_dt);
    }
}
