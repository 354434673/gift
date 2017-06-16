package com.gift.mapper;

import com.gift.vo.Customer;
import com.gift.vo.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExampleWithBLOBs(CustomerExample example);

    List<Customer> selectByExample(CustomerExample example);
    
    List<Customer> selectAndLogoByExample(CustomerExample example);
    /**
     * @Title: selectAndLogoByExample   
     * @Description: 模糊查询
     * @param: @param example
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年6月13日 下午3:53:30
     * @return: List<Customer>      
     * @throws
     */
    List<Customer> selectByNameOrTelOrEamil(@Param("condition")String condition,@Param("state")Long state);
    
    Customer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExampleWithBLOBs(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKeyWithBLOBs(Customer record);

    int updateByPrimaryKey(Customer record);
}