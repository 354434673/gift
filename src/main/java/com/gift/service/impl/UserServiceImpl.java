/**  
 * 
 * @Title:  UserServiceImpl.java   
 * @Package com.gift.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author kevin  
 * @date 2017年4月10日 下午5:05:32   
 * @version V1.0     
 */ 
package com.gift.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.UserMapper;
import com.gift.service.UserService;
import com.gift.vo.User;
import com.gift.vo.UserExample;
import com.gift.vo.UserExample.Criteria;
import com.github.pagehelper.PageHelper;

/**   
 *
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月10日 下午5:05:32      
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	/**   
	 * <p>Title: getUserByCondition</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月10日 下午5:06:13
	 * @param user
	 * @return   
	 * @see com.gift.service.UserService#getUserByCondition(com.gift.vo.User)   
	 */  
	@Override
	public User getUserByCondition(User user) {
		//实例化查询条件
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		//不能去like
		criteria.andNameEqualTo(user.getName());
		criteria.andPasswordEqualTo(user.getPassword());
		criteria.andStateEqualTo(0L);
		List<User> list = userMapper.selectByExample(example);
		return 0==list.size()?null:list.get(0);
	}

	/**   
	 * 根据条件获取用户集合
	 * <p>Title: getUsers</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月11日 下午5:54:10
	 * @param map
	 * @return   
	 * @see com.gift.service.UserService#getUsers(java.util.Map)   
	 */  
	@Override
	public List<User> getUsers(Map<String, Object> map) {
		//实例化查询条件
		UserExample example = new UserExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		Criteria criteria = example.createCriteria();
		//添加查询条件
		criteria.andStateEqualTo(0L);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	/**   
	 * <p>Title: addUser</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月13日 上午10:49:03
	 * @param user   
	 * @see com.gift.service.UserService#addUser(com.gift.vo.User)   
	 */  
	@Override
	public int addUser(User user) {
		//补全数据
		user.setId(UUIDHexGenerator.get());
		user.setState(0L);
		long time = new Date().getTime();
		user.setCreated(time);
		user.setUptated(time);
		int insert = userMapper.insert(user);
		return insert;
	}
	/**
	 * 
	 * <p>Title: deleteUser</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月14日 上午10:05:35
	 * @param user
	 * @return   
	 * @see com.gift.service.UserService#deleteUser(com.gift.vo.User)
	 */
	@Override
	public int deleteUser(String id) {
		//逻辑删除  更改状态为1
		User user = userMapper.selectByPrimaryKey(id);
		user.setState(1L);
		int primaryKey = userMapper.updateByPrimaryKey(user);
		return primaryKey;
	}
	
	
	
}
