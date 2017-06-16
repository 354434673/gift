package com.gift.mapper;

import com.gift.vo.Logo;
import com.gift.vo.LogoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogoMapper {
    int countByExample(LogoExample example);

    int deleteByExample(LogoExample example);

    int deleteByPrimaryKey(String id);

    int insert(Logo record);

    int insertSelective(Logo record);

    List<Logo> selectByExample(LogoExample example);
    
    List<Logo> selectLogoAndCustomer(LogoExample example, @Param("state")Long state);
    
    List<Logo> selectLogoAndCustomerByNameOrTitle(@Param("condition")String condition, @Param("state")Long state);

    Logo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Logo record, @Param("example") LogoExample example);

    int updateByExample(@Param("record") Logo record, @Param("example") LogoExample example);

    int updateByPrimaryKeySelective(Logo record);

    int updateByPrimaryKey(Logo record);
}