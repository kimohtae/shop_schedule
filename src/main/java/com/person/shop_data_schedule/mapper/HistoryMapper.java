package com.person.shop_data_schedule.mapper;

import java.util.Date;
import java.util.List;

import com.person.shop_data_schedule.data.MemberProdVO;
import com.person.shop_data_schedule.data.PageConnVO;
import com.person.shop_data_schedule.data.ReviewInfoVO;
import com.person.shop_data_schedule.data.ShoppingRecordVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    List<PageConnVO> selectPageConnHistory(Date start_dt, Date end_dt);
    List<MemberProdVO> selectMemberProdHistory(Date start_dt, Date end_dt);
    List<ReviewInfoVO> selectReviewInfo(Date start_dt, Date end_dt);
    List<ShoppingRecordVO> selectShoppingRecord(Date start_dt, Date end_dt, Integer status);
}
