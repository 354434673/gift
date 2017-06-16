package com.gift.mapper;

import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftItemCategoryMapper {
    int countByExample(GiftItemCategoryExample example);

    int deleteByExample(GiftItemCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftItemCategory record);

    int insertSelective(GiftItemCategory record);

    List<GiftItemCategory> selectByExample(GiftItemCategoryExample example);

    GiftItemCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftItemCategory record, @Param("example") GiftItemCategoryExample example);

    int updateByExample(@Param("record") GiftItemCategory record, @Param("example") GiftItemCategoryExample example);

    int updateByPrimaryKeySelective(GiftItemCategory record);

    int updateByPrimaryKey(GiftItemCategory record);
}