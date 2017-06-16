package com.gift.mapper;

import com.gift.vo.GiftItem;
import com.gift.vo.GiftItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftItemMapper {
    int countByExample(GiftItemExample example);

    int deleteByExample(GiftItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftItem record);

    int insertSelective(GiftItem record);

    List<GiftItem> selectByExample(GiftItemExample example);
    
    List<GiftItem> selectByExampleByCustomerId(GiftItemExample example,@Param("customerId")String customerId);
    
    GiftItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftItem record, @Param("example") GiftItemExample example);

    int updateByExample(@Param("record") GiftItem record, @Param("example") GiftItemExample example);

    int updateByPrimaryKeySelective(GiftItem record);

    int updateByPrimaryKey(GiftItem record);
    
    List<GiftItem> selectByCustomerId(String customerId);
}