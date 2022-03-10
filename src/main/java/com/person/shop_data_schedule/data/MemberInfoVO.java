package com.person.shop_data_schedule.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoVO {
    private Integer mi_seq;
    private String mi_email;
    private Integer mi_gen;
    private Integer mi_grade;

    @Override
    public String toString(){
        return mi_seq +","+ mi_email +","+ mi_gen +","+ mi_grade;
    }
}
