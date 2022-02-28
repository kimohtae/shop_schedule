package com.person.shop_data_schedule.mapper;

import java.util.Date;
import java.util.List;

import com.person.shop_data_schedule.data.PageConnVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    List<PageConnVO> selectPageConnHistory(Date start_dt, Date end_dt);
}
