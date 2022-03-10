package com.person.shop_data_schedule.data;

import java.util.Date;

import lombok.Data;

@Data
public class RecommendProdToMemberVO {
    private Integer rptm_seq;
    private String rptm_product_hash;
    private String rptm_member_hash;
    private Date rptm_reg_dt;
    private Double rptm_score;
}
