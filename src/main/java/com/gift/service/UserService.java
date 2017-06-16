/**  
 * 
 * @Title:  UserService.java   
 * @Package com.gift.service   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author kevin  
 * @date 2017年4月10日 下午4:40:42   
 * @version V1.0     
 */ 
package com.gift.service;

import java.util.List;
import java.util.Map;

import com.gift.vo.User;

/**   
 * 用户
 * @ClassName:  UserService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月10日 下午4:40:42      
 */
public interface UserService {
	
	User getUserByCondition(User user);
	
	List<User> getUsers(Map<String, Object> map);
	
	int addUser(User user);
	
	int deleteUser(String id);
	
}
