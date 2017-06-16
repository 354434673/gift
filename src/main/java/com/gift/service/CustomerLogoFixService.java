package com.gift.service;

import java.util.List;
import java.util.Map;

import com.gift.vo.Customer;
import com.gift.vo.CustomerLogoFix;

public interface CustomerLogoFixService {
	
	void addCustomerLogoFix (String pic,Customer customer,String itemId);
	
	List<CustomerLogoFix> getLogoFixs(Map<String, Object> map);
	/**
	 * @Title: getLogoFixsByCustomerId   
	 * @Description: 显示当前顾客拼接logo 
	 * @param: @param customerId
	 * @param: @param state
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月1日 下午3:28:28
	 * @return: List<CustomerLogoFix>      
	 * @throws
	 */
	List<CustomerLogoFix> getLogoFixsByCustomerId(String customerId, Long state, Long page);
	/**
	 * @Title: getTitleCount   
	 * @Description: 获取该用户合成logo条数
	 * @param: @param customerId
	 * @param: @param state
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月6日 上午10:22:04
	 * @return: Integer      
	 * @throws
	 */
	Integer getTitleCount(String customerId, Long state);
	
	List<CustomerLogoFix> getLogoFixsByCondition(Map<String, Object> map,String customerNameOrItemName)throws Exception;
}
