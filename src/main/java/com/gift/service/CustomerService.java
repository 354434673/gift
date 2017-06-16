package com.gift.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gift.vo.ApplyUser;
import com.gift.vo.Customer;

public interface CustomerService {
	/**
	 * @Title: queryCustomerByEmailAndPhone   
	 * @Description: 根据邮箱和电话查询
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月25日 上午10:34:10
	 * @return: Customer      
	 * @throws
	 */
	Customer queryCustomerByEmailAndPhone(String email, Long phone);
	/**
	 * @Title: addCustomer   
	 * @Description: 发放企业用户账号
	 * @param: @param name
	 * @param: @param password
	 * @param: @param phone
	 * @param: @param email
	 * @param: @param address
	 * @param: @param info
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月24日 下午3:32:57
	 * @return: String      
	 * @throws
	 */
	String addCustomer(String id, Customer customer);
	/**
	 * @Title: userLogin   
	 * @Description: 根据邮箱用户登录 
	 * @param: @param username
	 * @param: @param password
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月27日 下午3:53:06
	 * @return: Customer      
	 * @throws
	 */
	//Customer userLoginByEmail(String email, String password);
	/**
	 * @Title: userLoginByPhone   
	 * @Description: 根据手机用户登录  
	 * @param: @param username
	 * @param: @param password
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月27日 下午4:02:34
	 * @return: Customer      
	 * @throws
	 */
	//Customer userLoginByPhone(Long phone, String password);
	/**
	 * @Title: userLogin   
	 * @Description: 用户登录
	 * @param: @param username
	 * @param: @param password
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月27日 下午5:12:20
	 * @throws
	 */
	String userLogin(String username, String password,HttpServletRequest request);
	/**
	 * @Title: queryAllCustomer   
	 * @Description: 显示所有顾客 
	 * @param: @param map
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月2日 上午11:51:02
	 * @return: List<Customer>      
	 * @throws
	 */
	List<Customer> queryAllCustomer(Map<String, Object> map, Long state);
	/**
	 * @Title: updateCustomerState  
	 * @Description: 更改用户状态
	 * @param: @param id
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月2日 下午4:29:58
	 * @return: Integer      
	 * @throws
	 */
	String updateCustomerState(String id,Long state);
	/**
	 * @Title: resetPwd   
	 * @Description: 重置密码
	 * @param: @param email
	 * @param: @param phone
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月8日 下午4:47:35
	 * @return: String      
	 * @throws
	 */
	String resetPwd(String customerId, String email, Long phone,String userpwd);
	/**
	 * @Title: resetPwdByCustomerId   
	 * @Description: 用户中心页面修改密码 
	 * @param: @param customerId
	 * @param: @param userpwd
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月23日 下午4:05:13
	 * @throws
	 */
	//String resetPwdByCustomerId(String customerId,String userpwd);
	/**
	 * 后台
	 * 获取不同状态下的企业用户
	 * @Title: getCompanyCustomer   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param state
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月7日 下午5:04:00
	 * @return: List<Customer>      
	 * @throws
	 */
	List<Customer> getCompanyCustomer(Long state);
	/**
	 * 后台
	 * @Title: queryByNameOrTelOrEamil   
	 * @Description: 模糊查询
	 * @param: @param map
	 * @param: @param condition
	 * @param: @return
	 * @param: @throws UnsupportedEncodingException  
	 * @author YangNingSheng    
	 * @date 2017年6月13日 下午3:55:51
	 * @return: List<Customer>      
	 * @throws
	 */
	List<Customer> queryByNameOrTelOrEamil(Map<String, Object> map, String condition) throws UnsupportedEncodingException;

	
}
