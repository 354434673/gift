package com.gift.mapper;

import com.gift.vo.GiftItemDesc;
import com.gift.vo.GiftItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftItemDescMapper {
    int countByExample(GiftItemDescExample example);

    int deleteByExample(GiftItemDescExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftItemDesc record);

    int insertSelective(GiftItemDesc record);

    List<GiftItemDesc> selectByExample(GiftItemDescExample example);

    GiftItemDesc selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftItemDesc record, @Param("example") GiftItemDescExample example);

    int updateByExample(@Param("record") GiftItemDesc record, @Param("example") GiftItemDescExample example);

    int updateByPrimaryKeySelective(GiftItemDesc record);

    int updateByPrimaryKey(GiftItemDesc record);
    
    void lotDeleteByItemId(List<String> ids);
}