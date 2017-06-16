package com.gift.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gift.commons.Base64Util;
import com.gift.commons.FinalData;
import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.ApplyUserMapper;
import com.gift.mapper.CustomerMapper;
import com.gift.service.ApplyUserService;
import com.gift.vo.ApplyUser;
import com.gift.vo.ApplyUserExample;
import com.gift.vo.User;
import com.gift.vo.ApplyUserExample.Criteria;
import com.gift.vo.Customer;
import com.gift.vo.CustomerExample;
import com.github.pagehelper.PageHelper;
/**
 * @ClassName:  ShopServiceImpl   
 * @Description:企业用户申请业务实现层
 * @author YangNingSheng
 * @date 2017年4月20日 下午2:29:09
 */
@Service
@Transactional
public class ApplyUserServiceImpl implements ApplyUserService{
	@Autowired
	private ApplyUserMapper applyUserMapper;
	
	/**
	 * 企业用户申请
	 * <p>Title: groupApply</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:27:15
	 * @param applyUser
	 * @return   
	 * @see com.gift.service.ApplyUserService#groupApply(com.gift.vo.ApplyUser)
	 */
	@Override
	public String groupApply(ApplyUser applyUser) {
		
		ApplyUser queryGroupUserByEmailAndPhone = this.queryGroupUserByEmailAndPhone(applyUser.getEmail(), applyUser.getPhone());
		
		if(queryGroupUserByEmailAndPhone == null ){
			applyUser.setId(UUIDHexGenerator.get());
			
			/**
			 * 1:为已经发放过账号
			 * 0:未发放过账号
			 */
			applyUser.setState(FinalData.STATE_YES);
				
			applyUserMapper.insert(applyUser);
				
			Printer.info("用户:"+applyUser.getName()+"申请成功");
				
			return FinalData.RETURN_YES;
		}else{
			Printer.info("用户:"+applyUser.getName()+"重复申请");
			
			return FinalData.RETURN_NO;
		}
	}
	/**
	 * 根据手机查询该用户 
	 * <p>Title: queryGroupUserByPhone</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:27:36
	 * @param groupPhone
	 * @return   
	 * @see com.gift.service.ApplyUserService#queryGroupUserByPhone(java.lang.String)
	 */
	@Override
	public ApplyUser queryGroupUserByPhone(String groupPhone) {
		
		ApplyUserExample applyUserExample = new ApplyUserExample();
		
		Criteria createCriteria = applyUserExample.createCriteria();
		
		createCriteria.andPhoneEqualTo(groupPhone);
		
		List<ApplyUser> selectByExample = applyUserMapper.selectByExample(applyUserExample);
		
		if(selectByExample.size()== 0){
			Printer.info("该:"+groupPhone+" 手机号不存在,可注册");
			
			return null;
		}else{
			Printer.info("该:"+groupPhone+" 手机号已存在");
			
			return selectByExample.get(0);
		}
		
	}
	
	/**
	 *  根据邮箱查询该用户 
	 * <p>Title: queryGroupUserByEmail</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:28:11
	 * @param email
	 * @return   
	 * @see com.gift.service.ApplyUserService#queryGroupUserByEmail(java.lang.String)
	 */
	@Override
	public ApplyUser queryGroupUserByEmail(String email) {
		
		ApplyUserExample applyUserExample = new ApplyUserExample();
		
		Criteria createCriteria = applyUserExample.createCriteria();
		
		createCriteria.andEmailEqualTo(email);
		
		List<ApplyUser> selectByExample = applyUserMapper.selectByExample(applyUserExample);
		
		if(selectByExample.size()== 0){
			Printer.info("该:"+email+" 邮箱不存在,可注册");
			
			return null;
		}else{
			Printer.info("该:"+email+" 邮箱已存在");
			
			return selectByExample.get(0);
		}
	}
	/**
	 * 根据id查询
	 * <p>Title: queryGroupUserById</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:28:17
	 * @param id
	 * @return   
	 * @see com.gift.service.ApplyUserService#queryGroupUserById(java.lang.String)
	 */
	@Override
	public ApplyUser queryGroupUserById(String id) {
		
		ApplyUser selectByPrimaryKey = applyUserMapper.selectByPrimaryKey(id);
		
		if(selectByPrimaryKey == null){
			Printer.info("id为:"+id+"的用户不存在");
			return null;
		}else {
			Printer.info("id为:"+id+"的用户存在");
			return selectByPrimaryKey;
		}
	}
	/**
	 * 防止重复插入
	 * <p>Title: queryGroupUserByEmailAndPhone</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:28:51
	 * @param email
	 * @param groupPhone
	 * @return   
	 * @see com.gift.service.ApplyUserService#queryGroupUserByEmailAndPhone(java.lang.String, java.lang.String)
	 */
	@Override
	public ApplyUser queryGroupUserByEmailAndPhone(String email,String groupPhone) {
		
		ApplyUserExample applyUserExample = new ApplyUserExample();
		
		Criteria createCriteria = applyUserExample.createCriteria();
		
		createCriteria.andEmailEqualTo(email);
		
		createCriteria.andPhoneEqualTo(groupPhone);
		
		List<ApplyUser> selectByExample = applyUserMapper.selectByExample(applyUserExample);
		
		if(selectByExample.size()== 0){
			Printer.info("该用户不存在");
			
			return null;
		}else{
			Printer.info("该用户存在");
			
			return selectByExample.get(0);
		}
		
	}
	/**
	 * 查询全部企业申请用户
	 * <p>Title: queryAllApplyUser</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午2:29:01
	 * @param map
	 * @param state
	 * @return   
	 * @see com.gift.service.ApplyUserService#queryAllApplyUser(java.util.Map, java.lang.Long)
	 */
	@Override
	public List<ApplyUser> queryAllApplyUser(Map<String, Object> map, Long state) {
		
		ApplyUserExample applyUserExample = new ApplyUserExample();
		
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		Criteria criteria = applyUserExample.createCriteria();
		
		criteria.andStateEqualTo(state);
		
		List<ApplyUser> selectByExample = applyUserMapper.selectByExample(applyUserExample);
		
		return selectByExample;
	}
	/**
	 * 获取未处理的企业用户数量
	 * <p>Title: getApplyUserCount</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月6日 上午11:02:30
	 * @param state
	 * @return   
	 * @see com.gift.service.ApplyUserService#getApplyUserCount(java.lang.Long)
	 */
	@Override
	public int getApplyUserCount(Long state) {
		ApplyUserExample applyUserExample = new ApplyUserExample();
		Criteria createCriteria = applyUserExample.createCriteria();
		createCriteria.andStateEqualTo(state);
		int countByExample = applyUserMapper.countByExample(applyUserExample);
		return countByExample;
	}
	/**
	 * 模糊查询
	 * <p>Title: queryByNameOrTelOrEamil</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月13日 下午3:21:49
	 * @param map
	 * @param condition
	 * @return   
	 * @throws UnsupportedEncodingException 
	 * @see com.gift.service.ApplyUserService#queryByNameOrTelOrEamil(java.util.Map, java.lang.String)
	 */
	@Override
	public List<ApplyUser> queryByNameOrTelOrEamil(Map<String, Object> map, String condition) throws UnsupportedEncodingException {
		
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		condition = URLDecoder.decode(condition,"UTF-8");
		
		List<ApplyUser> selectByNameOrTelOrEamil = applyUserMapper.selectByNameOrTelOrEamil("%"+condition+"%",FinalData.STATE_YES);
		
		return selectByNameOrTelOrEamil;
	}
}
