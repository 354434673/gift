package com.gift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gift.vo.CustomerAndCategory;

public interface CustomerAndCategoryMapper {
    /**
     * @Title: selectAll   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月26日 下午4:39:34
     * @return: List<CustomerAndCategory>      
     * @throws
     */
    List<CustomerAndCategory> selectAll(@Param("customerId")String customerId);
    /**
     * @Title: addCustomerAndCategory   
     * @Description: 为该用户添加商品权限 
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月26日 下午6:12:49
     * @return: int      
     * @throws
     */
    int addCustomerAndCategory(CustomerAndCategory customerAndCategory);
    /**
     * @Title: queryOne   
     * @Description: 查询,防止重复插入
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月27日 下午3:12:02
     * @return: CustomerAndCategory      
     * @throws
     */
    CustomerAndCategory queryOne(@Param("customerId")String customerId, @Param("categoryId")String categoryId);
}
