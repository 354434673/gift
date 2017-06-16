package com.gift.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gift.commons.Base64Util;
import com.gift.commons.FinalData;
import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.ApplyUserMapper;
import com.gift.mapper.CustomerMapper;
import com.gift.service.CustomerService;
import com.gift.vo.ApplyUser;
import com.gift.vo.ApplyUserExample;
import com.gift.vo.Customer;
import com.gift.vo.CustomerExample;
import com.gift.vo.CustomerExample.Criteria;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ApplyUserMapper applyUserMapper;
	/**
	 * 根据邮箱和电话查询
	 * <p>Title: queryCustomerByEmailAndPhone</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:31:01
	 * @param email
	 * @param phone
	 * @return   
	 * @see com.gift.service.CustomerService#queryCustomerByEmailAndPhone(java.lang.String, java.lang.Long)
	 */
	@Override
	public Customer queryCustomerByEmailAndPhone(String email, Long phone) {
		CustomerExample customerExample = new CustomerExample();
		Criteria createCriteria = customerExample.createCriteria();
		createCriteria.andEmailEqualTo(email);
		
		createCriteria.andPhoneEqualTo(phone);
		
		List<Customer> selectByExample = customerMapper.selectByExample(customerExample);
		
		if(selectByExample.size()== 0){
			Printer.info("邮箱为:"+email+",电话为"+phone+"的用户不存在");
			
			return null;
		}else{
			Printer.info("邮箱为:"+email+",电话为"+phone+"的用户存在");
			
			return selectByExample.get(0);
		}
	}
	/**
	 * 发放企业用户账号
	 * <p>Title: addCustomer</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:31:10
	 * @param id
	 * @param customer
	 * @return   
	 * @see com.gift.service.CustomerService#addCustomer(java.lang.String, com.gift.vo.Customer)
	 */
	@Override
	public String addCustomer(String id, Customer customer) {
		Customer queryCustomerByEmailAndPhone = this.queryCustomerByEmailAndPhone(customer.getEmail(), customer.getPhone());
		
		if(queryCustomerByEmailAndPhone == null){
			customer.setId(UUIDHexGenerator.get());
			try {
				String passBase64 = Base64Util.encryptBASE64(customer.getPwd()+FinalData.SALT);
				
				customer.setPwd(passBase64);
			} catch (Exception e) {
				e.printStackTrace();
			}
			customerMapper.insert(customer);
			/**
			 * 添加后将企业申请用户改为账号发放状态
			 */
			ApplyUser selectByPrimaryKey = applyUserMapper.selectByPrimaryKey(id);
			
			selectByPrimaryKey.setState(FinalData.STATE_NO);
			
			applyUserMapper.updateByPrimaryKey(selectByPrimaryKey);
			
			Printer.info("用户名为:"+customer.getName()+",手机号为"+customer.getPhone()+",邮箱为:"+customer.getEmail()+"的用户注册成功");
			
			return FinalData.RETURN_YES;
		}else{
			Printer.info("用户名为:"+customer.getName()+",手机号为"+customer.getPhone()+",邮箱为:"+customer.getEmail()+"的用户存在");
			
			return FinalData.RETURN_NO;
		}
	}

	/**
	 *  用户登录
	 * <p>Title: userLogin</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:31:20
	 * @param username
	 * @param password
	 * @param request
	 * @return   
	 * @see com.gift.service.CustomerService#userLogin(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String userLogin(String username, String password,HttpServletRequest request) {
		
		Customer userLoginByEmail = userLoginByEmail(username, password);//先通过邮箱匹配
		
		Customer userLoginByPhone = null;//再通过手机匹配
		if(userLoginByEmail == null){//邮箱不存在
			
			Printer.error("邮箱为:"+username+"的用户登录失败,不存在");
			
			userLoginByPhone = userLoginByPhone(Long.parseLong(username), password);//再通过手机匹配
			
			if(userLoginByPhone == null){//手机不存在
				Printer.error("手机号为:"+username+"的用户登录失败,,不存在");
				return FinalData.RETURN_NO;
			}else if(userLoginByPhone.getState() == FinalData.STATE_NO){//如果用户状态为1,则用户为不可用状态
			    	Printer.error("手机号为:"+username+"的不可用");
			    	return FinalData.RETURN_CUSTOMER_NO;
			}else{
				Printer.info("该手机登录成功");
				request.getSession().setAttribute("customer", userLoginByPhone);
				return FinalData.RETURN_YES;
			}
		}else if(userLoginByEmail.getState() == FinalData.STATE_NO){//如果用户状态为1,则用户为不可用状态
		    	Printer.error("邮箱为:"+username+"的不可用");
		    	return FinalData.RETURN_CUSTOMER_NO;
		}else{
			Printer.info("该邮箱登录成功");
			request.getSession().setAttribute("customer", userLoginByEmail);
			return FinalData.RETURN_YES;
		}
	}
	/**
	 * 显示所有顾客 
	 * <p>Title: queryAllCustomer</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:31:47
	 * @param map
	 * @param state
	 * @return   
	 * @see com.gift.service.CustomerService#queryAllCustomer(java.util.Map, java.lang.Long)
	 */
	@Override
	public List<Customer> queryAllCustomer(Map<String, Object> map, Long state) {
		
		CustomerExample customerExample = new CustomerExample();
		
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		Criteria criteria = customerExample.createCriteria();
		
		criteria.andStateEqualTo(state);//
		
		List<Customer> selectByExample = customerMapper.selectByExample(customerExample);
		
		return selectByExample;
	}
	/**
	 * 更改用户状态
	 * <p>Title: updateCustomerState</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:32:05
	 * @param id
	 * @param state
	 * @return   
	 * @see com.gift.service.CustomerService#updateCustomerState(java.lang.String, java.lang.Long)
	 */
	@Override
	public String updateCustomerState(String id, Long state) {
		
		Customer selectByPrimaryKey = customerMapper.selectByPrimaryKey(id);
		
		if(selectByPrimaryKey == null){
			Printer.error("用户id为:"+id+"的用户不存在");
			
			return FinalData.RETURN_NO;
		}else{
			selectByPrimaryKey.setState(state);
			
			customerMapper.updateByPrimaryKey(selectByPrimaryKey);
			
			return FinalData.RETURN_YES;
		}
	}
	/**
	 * 重置密码
	 * <p>Title: resetPwd</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:32:44
	 * @param customerId
	 * @param email
	 * @param phone
	 * @param userpwd
	 * @return   
	 * @see com.gift.service.CustomerService#resetPwd(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public String resetPwd(String customerId, String email, Long phone, String userpwd) {
		
		CustomerExample customerExample = new CustomerExample();
		
		Criteria createCriteria = customerExample.createCriteria();
		
		String passBase64 = null;
		try {
		    passBase64 = Base64Util.encryptBASE64(userpwd+FinalData.SALT);
		} catch (Exception e1) {
		    e1.printStackTrace();
		    Printer.error("修改失败");
		    return FinalData.RETURN_NO;
		}
		if(customerId == null){//如果id为空表示用户未登陆,通过邮箱和电话修改密码
		    
		    createCriteria.andEmailEqualTo(email);
		    
		    createCriteria.andPhoneEqualTo(phone);
		    
		    List<Customer> selectByExample = customerMapper.selectByExample(customerExample);
		    
		    if(selectByExample.size() == 0){
			return FinalData.RETURN_NO;
		    }else{
			selectByExample.get(0).setPwd(passBase64);
			
			customerMapper.updateByPrimaryKey(selectByExample.get(0));
			
			Printer.info("根据邮箱为:"+email+",电话为:"+phone+"的用户修改密码成功");
			
			return FinalData.RETURN_YES;
		    }
		    
		}else{//不为空则表示用户登录后修改密码
		    Customer selectByPrimaryKey = customerMapper.selectByPrimaryKey(customerId);
		    
		    selectByPrimaryKey.setPwd(passBase64);
		    
		    customerMapper.updateByPrimaryKey(selectByPrimaryKey);
		    
		    Printer.info("根据id为:"+customerId+"的用户修改密码成功");
		    
		    return FinalData.RETURN_YES;
		}
	}
/*
	@Override
	public String resetPwdByCustomerId(String customerId, String userpwd) {
	    
		Customer selectByPrimaryKey = customerMapper.selectByPrimaryKey(customerId);
		
		try {
		    String passBase64 = Base64Util.encryptBASE64(userpwd+FinalData.SALT);
		    
		    selectByPrimaryKey.setPwd(passBase64);
		    
		    customerMapper.updateByPrimaryKey(selectByPrimaryKey);
		    
		    Printer.info("修改成功");
		    
		    return FinalData.RETURN_YES;
		} catch (Exception e) {
		    e.printStackTrace();
		    Printer.error("修改失败");
		    return FinalData.RETURN_NO;
		}
	}*/
	/**
	 * 用于登陆时通过不同方式验证
	 * ---------------------------分割线---------------------------------------------
	 */
	private Customer userLoginByEmail(String username, String password) {
		CustomerExample customerExample = new CustomerExample();
		
		Criteria createCriteria = customerExample.createCriteria();
		
		createCriteria.andEmailEqualTo(username);
		try {
			createCriteria.andPwdEqualTo(Base64Util.encryptBASE64(password+FinalData.SALT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Customer> selectByExample = customerMapper.selectByExample(customerExample);
		
		if(selectByExample.size()== 0){
			//Printer.info("邮箱为:"+username+"的用户不存在");
			
			return null;
		}else{
			//Printer.info("邮箱为:"+username+"的用户登录成功");
			
			return selectByExample.get(0);
		}
	}
	private Customer userLoginByPhone(Long phone, String password) {
		
		CustomerExample customerExample = new CustomerExample();
		
		Criteria createCriteria = customerExample.createCriteria();
		
		createCriteria.andPhoneEqualTo(phone);
		try {
			String pwd = Base64Util.encryptBASE64(password+FinalData.SALT);
			
			createCriteria.andPwdEqualTo(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Customer> selectByExample = customerMapper.selectByExample(customerExample);
		
		if(selectByExample.size()== 0){
			//Printer.error("手机为:"+phone+"的用户不存在");
			
			return null;
		}else{
			//Printer.info("手机为:"+phone+"的用户登录成功");
			
			return selectByExample.get(0);
		}
	}
	//---------------------------------------------------------
	/**
	 * 获取不同状态下的企业用户
	 * <p>Title: getCompanyCustomer</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月7日 下午5:05:12
	 * @param state
	 * @return   
	 * @see com.gift.service.CustomerService#getCompanyCustomer(java.lang.Long)
	 */
	@Override
	public List<Customer> getCompanyCustomer(Long state) {
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		List<Customer> list = customerMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<Customer> queryByNameOrTelOrEamil(Map<String, Object> map, String condition)
		throws UnsupportedEncodingException {
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		condition = URLDecoder.decode(condition,"UTF-8");
		
		List<Customer> selectByNameOrTelOrEamil = customerMapper.selectByNameOrTelOrEamil("%"+condition+"%", FinalData.STATE_YES);
	
		return selectByNameOrTelOrEamil;
	}
}
