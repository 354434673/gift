package com.gift.mapper;

import com.gift.vo.GiftContentCategory;
import com.gift.vo.GiftContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftContentCategoryMapper {
    int countByExample(GiftContentCategoryExample example);

    int deleteByExample(GiftContentCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftContentCategory record);

    int insertSelective(GiftContentCategory record);

    List<GiftContentCategory> selectByExample(GiftContentCategoryExample example);

    GiftContentCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftContentCategory record, @Param("example") GiftContentCategoryExample example);

    int updateByExample(@Param("record") GiftContentCategory record, @Param("example") GiftContentCategoryExample example);

    int updateByPrimaryKeySelective(GiftContentCategory record);

    int updateByPrimaryKey(GiftContentCategory record);
}