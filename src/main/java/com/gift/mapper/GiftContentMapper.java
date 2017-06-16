package com.gift.mapper;

import com.gift.vo.GiftContent;
import com.gift.vo.GiftContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftContentMapper {
    int countByExample(GiftContentExample example);

    int deleteByExample(GiftContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftContent record);

    int insertSelective(GiftContent record);

    List<GiftContent> selectByExample(GiftContentExample example);

    GiftContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftContent record, @Param("example") GiftContentExample example);

    int updateByExample(@Param("record") GiftContent record, @Param("example") GiftContentExample example);

    int updateByPrimaryKeySelective(GiftContent record);

    int updateByPrimaryKey(GiftContent record);
}