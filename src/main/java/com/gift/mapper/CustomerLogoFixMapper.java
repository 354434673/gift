package com.gift.mapper;

import com.gift.vo.CustomerLogoFix;
import com.gift.vo.CustomerLogoFixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerLogoFixMapper {
    int countByExample(CustomerLogoFixExample example);

    int deleteByExample(CustomerLogoFixExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerLogoFix record);

    int insertSelective(CustomerLogoFix record);

    List<CustomerLogoFix> selectByExample(CustomerLogoFixExample example);
    
    List<CustomerLogoFix> selectByExampleAndItemAndCustomer(@Param("customerId")String customerId, 
	    			@Param("state")Long state,@Param("begin")Long begin,@Param("pageNum")Long pageNum);

    CustomerLogoFix selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerLogoFix record, @Param("example") CustomerLogoFixExample example);

    int updateByExample(@Param("record") CustomerLogoFix record, @Param("example") CustomerLogoFixExample example);

    int updateByPrimaryKeySelective(CustomerLogoFix record);

    int updateByPrimaryKey(CustomerLogoFix record);
    /**
     * 新增sql
     * 模糊查询
     * @Title: selectByCondition   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param example
     * @param: @return  
     * @author kevin    
     * @date 2017年6月13日 上午10:35:39
     * @return: List<CustomerLogoFix>      
     * @throws
     */
    List<CustomerLogoFix> selectByCondition(String customerNameOrItemName);
    
}