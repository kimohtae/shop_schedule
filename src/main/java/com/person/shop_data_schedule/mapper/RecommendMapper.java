package com.person.shop_data_schedule.mapper;

import java.util.List;

import com.person.shop_data_schedule.data.MemberHashDataVO;
import com.person.shop_data_schedule.data.ProductHashDataVO;
import com.person.shop_data_schedule.data.RecommendProdToMemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendMapper {
    public void insertMemberHashData(List<MemberHashDataVO> list) ;
    public void insertProductHashData(List<ProductHashDataVO> list);
    public void insertRecommendHashData(List<RecommendProdToMemberVO> list);
}
