package com.gift.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.commons.FinalData;
import com.gift.commons.JsonUtil;
import com.gift.commons.TreeJson;
import com.gift.mapper.CustomerAndCategoryMapper;
import com.gift.mapper.GiftItemCategoryMapper;
import com.gift.service.SecurityService;
import com.gift.vo.CustomerAndCategory;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemCategoryExample;
import com.gift.vo.GiftItemExample;
import com.gift.vo.GiftItemCategoryExample.Criteria;
@Service
public class SecurityServiceImpl implements SecurityService{
    
    @Autowired
    private GiftItemCategoryMapper giftItemCategoryMapper;
    @Autowired
    private CustomerAndCategoryMapper customerAndCategoryMapper;
    /**
     * 获取该用户的图片权限菜单
     * <p>Title: getItemCategory</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:29:26
     * @param customerId
     * @return   
     * @see com.gift.service.SecurityService#getItemCategory(java.lang.String)
     */
    @Override
    public List<TreeJson> getItemCategory(String customerId) {
	
	GiftItemCategoryExample giftItemCategoryExample = new GiftItemCategoryExample();
	
	GiftItemExample giftItemExample = new GiftItemExample();
	
	Criteria createCriteria = giftItemCategoryExample.createCriteria();
	
	createCriteria.andParentidNotEqualTo(0L);
	
	List<GiftItemCategory> selectByExample = giftItemCategoryMapper.selectByExample(giftItemCategoryExample);
	
	List<CustomerAndCategory> selectAll = customerAndCategoryMapper.selectAll(customerId);
	
	List<TreeJson> list = new ArrayList<TreeJson>();
	for (GiftItemCategory giftItemCategory : selectByExample) {//先获得分类表数据,将数据封装成特定的json格式
	    
	    TreeJson treeJson = new TreeJson();
	    
	    treeJson.setId(giftItemCategory.getId());
	    
	    treeJson.setName(giftItemCategory.getName());
	    
	    treeJson.setpId(giftItemCategory.getParentid());
	    
	    treeJson.setOpen(false);
	    for (CustomerAndCategory customerAndCategory : selectAll) {
		if(giftItemCategory.getId().equals(customerAndCategory.getCategoryId())){//如果相等则有这个权限
		    treeJson.setChecked(true);
		}else{
		    continue;//这里跳出,因为会在第一次比较的时候插入false,出现异常
		}
	    }
	    //将数据封装成zTree的特定json格式
	    list.add(treeJson);
	}
	return list;
    }
    /**
     * 添加图片权限
     * <p>Title: addItemCategory</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:29:36
     * @param customerId
     * @param categoryId
     * @return   
     * @see com.gift.service.SecurityService#addItemCategory(java.lang.String, java.lang.String)
     */
    @Override
    public String addItemCategory(String customerId, String categoryId) {
	CustomerAndCategory queryOne = customerAndCategoryMapper.queryOne(customerId, categoryId);
	if(queryOne == null){//防止重复插入
	    
	    CustomerAndCategory customerAndCategory = new CustomerAndCategory(customerId, categoryId);
	    
	    customerAndCategoryMapper.addCustomerAndCategory(customerAndCategory);
	}
	
	return FinalData.RETURN_YES;
    }

}
