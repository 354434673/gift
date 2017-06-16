package com.gift.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.gift.vo.ApplyUser;
import com.gift.vo.Customer;

/**
 * @ClassName:  ShopService   
 * @Description:企业用户申请业务层
 * @author YangNingSheng
 * @date 2017年4月19日 下午3:14:15
 */
public interface ApplyUserService {
	/**
	 * @Title: groupApply   
	 * @Description: 企业用户申请账号
	 * @param: @param name
	 * @param: @param groupPhone
	 * @param: @param email
	 * @param: @param applyReason
	 * @param: @param address  
	 * @author YangNingSheng    
	 * @date 2017年4月19日 下午3:19:20
	 * @return: int      
	 * @throws
	 */
	String groupApply(ApplyUser applyUser);
	/**
	 * @Title: queryGroupUser   
	 * @Description: 根据手机查询该用户 
	 * @param: @param groupPhone
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月20日 下午2:27:26
	 * @return: ApplyUser      
	 * @throws
	 */
	ApplyUser queryGroupUserByPhone(String groupPhone);
	/**
	 * @Title: queryGroupUserByEmail   
	 * @Description: 根据邮箱查询该用户 
	 * @param: @param email
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午3:06:52
	 * @return: ApplyUser      
	 * @throws
	 */
	ApplyUser queryGroupUserByEmail(String email);
	/**
	 * @Title: queryGroupUserByEmailAndPhone   
	 * @Description: 防止重复插入
	 * @param: @param email
	 * @param: @param groupPhone
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午3:18:40
	 * @return: ApplyUser      
	 * @throws
	 */
	ApplyUser queryGroupUserByEmailAndPhone(String email, String groupPhone);
	/**
	 * @Title: queryGroupUserById   
	 * @Description: 根据id查询
	 * @param: @param id
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午4:15:08
	 * @return: ApplyUser      
	 * @throws
	 */
	ApplyUser queryGroupUserById(String id);
	/**
	 * @Title: queryAllApplyUser   
	 * @Description: 查询全部企业申请用户
	 * @param: @param map
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月20日 下午2:39:59
	 * @return: List<ApplyUser>      
	 * @throws
	 */
	List<ApplyUser> queryAllApplyUser(Map<String, Object> map, Long state);
	/**
	 * @throws UnsupportedEncodingException 
	 * @Title: queryByNameOrTelOrEamil   
	 * @Description: 模糊查询
	 * @param: @param map
	 * @param: @param condition
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月13日 下午3:20:47
	 * @return: List<ApplyUser>      
	 * @throws
	 */
	List<ApplyUser> queryByNameOrTelOrEamil(Map<String, Object> map, String condition) throws UnsupportedEncodingException;
	
	int getApplyUserCount(Long state);
}
