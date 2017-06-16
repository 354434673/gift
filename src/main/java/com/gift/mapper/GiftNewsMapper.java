package com.gift.mapper;

import com.gift.vo.GiftNews;
import com.gift.vo.GiftNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftNewsMapper {
    int countByExample(GiftNewsExample example);

    int deleteByExample(GiftNewsExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftNews record);

    int insertSelective(GiftNews record);

    List<GiftNews> selectByExample(GiftNewsExample example);
    /**
     * 获得最新的前N条数据
     */
    List<GiftNews> selectByExampleForTitle(@Param("num")Integer num);

    GiftNews selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftNews record, @Param("example") GiftNewsExample example);

    int updateByExample(@Param("record") GiftNews record, @Param("example") GiftNewsExample example);

    int updateByPrimaryKeySelective(GiftNews record);

    int updateByPrimaryKey(GiftNews record);
}