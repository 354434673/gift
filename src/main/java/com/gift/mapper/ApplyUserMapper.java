package com.gift.mapper;

import com.gift.vo.ApplyUser;
import com.gift.vo.ApplyUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ApplyUserMapper {
	
    int countByExample(ApplyUserExample example);

    int deleteByExample(ApplyUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApplyUser record);

    int insertSelective(ApplyUser record);

    List<ApplyUser> selectByExample(ApplyUserExample example);
    /**
     * 模糊查询
     */
    List<ApplyUser> selectByNameOrTelOrEamil(@Param("condition")String condition,@Param("state")Long state);

    ApplyUser selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") ApplyUser record, @Param("example") ApplyUserExample example);

    int updateByExample(@Param("record") ApplyUser record, @Param("example") ApplyUserExample example);

    int updateByPrimaryKeySelective(ApplyUser record);

    int updateByPrimaryKey(ApplyUser record);
}