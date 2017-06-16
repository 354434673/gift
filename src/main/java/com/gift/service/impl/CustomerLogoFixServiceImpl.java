package com.gift.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gift.commons.FinalData;
import com.gift.commons.Page;
import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.CustomerLogoFixMapper;
import com.gift.mapper.CustomerMapper;
import com.gift.mapper.GiftItemMapper;
import com.gift.service.CustomerLogoFixService;
import com.gift.vo.Customer;
import com.gift.vo.CustomerLogoFix;
import com.gift.vo.CustomerLogoFixExample;
import com.gift.vo.CustomerLogoFixExample.Criteria;
import com.gift.vo.GiftItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
/**
 * 客户logo和商品图片合成
 *
 * @ClassName:  CustomerLogoFixServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月27日 下午2:04:47
 */
@Service
@Transactional
public class CustomerLogoFixServiceImpl implements CustomerLogoFixService {
	
	@Autowired 
	private CustomerLogoFixMapper logoFixMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private GiftItemMapper itemMapper;
	/**
	 * 读取上传的图片，添加到数据库中
	 * <p>Title: addCustomerLogoFix</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月30日 下午3:31:22
	 * @param pic
	 * @param customer
	 * @param itemId   
	 * @see com.gift.service.CustomerLogoFixService#addCustomerLogoFix(java.lang.String, com.gift.vo.Customer, java.lang.String)
	 */
	@Override
	public void addCustomerLogoFix(String pic,Customer customer,String itemId) {
		//重新拼装下pic地址
		pic = "/fileupload/logofix"+"/"+pic;
		//封装插入对象
		CustomerLogoFix logoFix = new CustomerLogoFix();
		logoFix.setId(UUIDHexGenerator.get());
		logoFix.setBei1("");
		logoFix.setBei2("");
		logoFix.setCreated(System.currentTimeMillis());
		logoFix.setUpdated(System.currentTimeMillis());
		logoFix.setCustomerid(customer.getId());
		logoFix.setItemid(itemId);
		logoFix.setPic(pic);
		logoFix.setType("");
		logoFix.setState(0L);
		logoFixMapper.insert(logoFix);
	}
	/**
	 * 后台
	 * 获取用户合成图片的集合
	 * <p>Title: getLogoFixs</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月30日 下午3:33:48
	 * @param map
	 * @return   
	 * @see com.gift.service.CustomerLogoFixService#getLogoFixs(java.util.Map)
	 */
	@Override
	public List<CustomerLogoFix> getLogoFixs(Map<String, Object> map) {
		CustomerLogoFixExample example = new CustomerLogoFixExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);
		List<CustomerLogoFix> list = logoFixMapper.selectByExample(example);
		//后期要优化sql
		for (CustomerLogoFix customerLogoFix : list) {
			GiftItem item = itemMapper.selectByPrimaryKey(customerLogoFix.getItemid());
			Customer customer = customerMapper.selectByPrimaryKey(customerLogoFix.getCustomerid());
			customerLogoFix.setBei1(item.getTitile());
			customerLogoFix.setBei2(customer.getName());
		}
		return list;
	}
	/**
	 * 查询当前用户的拼接logo
	 * <p>Title: getLogoFixsByCustomerId</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月1日 下午3:48:36
	 * @param customerId
	 * @param state
	 * @return   
	 * @see com.gift.service.CustomerLogoFixService#getLogoFixsByCustomerId(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<CustomerLogoFix> getLogoFixsByCustomerId(String customerId, Long state, Long page) {
	    
	    Page p = new Page();
	    List<CustomerLogoFix> selectByExampleAndItemAndCustomer = 
		    logoFixMapper.selectByExampleAndItemAndCustomer(customerId, state,p.getbeginNum(page),15L);
	    
	    return selectByExampleAndItemAndCustomer;
	}
	/**
	 * 获得数量
	 * <p>Title: getTitleCount</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月6日 上午10:24:16
	 * @param customerId
	 * @param state
	 * @return   
	 * @see com.gift.service.CustomerLogoFixService#getTitleCount(java.lang.String, java.lang.Long)
	 */
	@Override
	public Integer getTitleCount(String customerId, Long state) {
	    
	    CustomerLogoFixExample customerLogoFixExample = new CustomerLogoFixExample();
	    
	    Criteria createCriteria = customerLogoFixExample.createCriteria();
	    
	    createCriteria.andCustomeridEqualTo(customerId);
	    
	    createCriteria.andStateEqualTo(state);
	    
	    int countByExample = logoFixMapper.countByExample(customerLogoFixExample);
	    
	    return countByExample;
	}
	@Override
	public List<CustomerLogoFix> getLogoFixsByCondition(Map<String, Object> map, String customerNameOrItemName) throws Exception {
		CustomerLogoFixExample example = new CustomerLogoFixExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);
		customerNameOrItemName = URLDecoder.decode(customerNameOrItemName,"UTF-8");
		List<CustomerLogoFix> list = logoFixMapper.selectByCondition("%"+customerNameOrItemName+"%");
		//后期要优化sql
		for (CustomerLogoFix customerLogoFix : list) {
			GiftItem item = itemMapper.selectByPrimaryKey(customerLogoFix.getItemid());
			Customer customer = customerMapper.selectByPrimaryKey(customerLogoFix.getCustomerid());
			customerLogoFix.setBei1(item.getTitile());
			customerLogoFix.setBei2(customer.getName());
		}
		return list;
	}

}
