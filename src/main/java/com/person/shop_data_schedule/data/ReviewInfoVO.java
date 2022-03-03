package com.person.shop_data_schedule.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewInfoVO {
    private Integer ri_mi_seq;
    private Integer ri_pi_seq;
    private Integer ri_score;
	private Date ri_mod_dt;
    private Integer ri_status;
    private Integer ri_report_cnt;

    @Override
    public String toString(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return ri_mi_seq +","+ ri_pi_seq +","+ ri_score +","+ ri_mod_dt +","+ ri_status +","+ sf.format(ri_report_cnt);
    }
}
