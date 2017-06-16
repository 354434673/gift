package com.gift.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.GiftItemCategoryMapper;
import com.gift.service.ItemCategoryService;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemCategoryExample;
import com.gift.vo.GiftItemCategoryExample.Criteria;
import com.github.pagehelper.PageHelper;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
	
	@Autowired
	private GiftItemCategoryMapper itemCategoryMapper;
	
	/**
	 * 添加
	 * <p>Title: addItemCategory</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月5日 下午2:18:04
	 * @param itemCategory   
	 * @see com.gift.service.ItemCategoryService#addItemCategory(com.gift.vo.GiftItemCategory)
	 */
	@Override
	public void addItemCategory(GiftItemCategory itemCategory) {
		//补全信息
		itemCategory.setId(UUIDHexGenerator.get());
		itemCategory.setCreated(System.currentTimeMillis());
		itemCategory.setIsparent(1L);//不是父类
		itemCategory.setUptated(System.currentTimeMillis());
		itemCategory.setState(0L);
		itemCategory.setSortorder(0L);
		itemCategoryMapper.insert(itemCategory);
	}
	/**
	 * 获取列表
	 * <p>Title: getItemCategoryList</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月5日 下午3:27:29
	 * @param map
	 * @return   
	 * @see com.gift.service.ItemCategoryService#getItemCategoryList(java.util.Map)
	 */
	@Override
	public List<GiftItemCategory> getItemCategoryList(Map<String, Object> map) {
		//实例化查询条件
		GiftItemCategoryExample example = new GiftItemCategoryExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);
		criteria.andIdNotEqualTo("1");
		example.setOrderByClause(" parentid asc ");
		List<GiftItemCategory> list = itemCategoryMapper.selectByExample(example);
		return list;
	}
	/**
	 * 根据 父ID 查询此 分类下的集合
	 * <p>Title: getItemCategoryListByParentId</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月8日 上午11:03:35
	 * @param parentId
	 * @return   
	 * @see com.gift.service.ItemCategoryService#getItemCategoryListByParentId(java.lang.Long)
	 */
	@Override
	public List<GiftItemCategory> getItemCategoryListByParentId(Long parentId) {
		List<GiftItemCategory> list = null;
		GiftItemCategoryExample example = new GiftItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);   //未删除
		if("1".equals(parentId+"")){
			criteria.andParentidEqualTo(parentId);
		}else{
			criteria.andParentidEqualTo(parentId);
			criteria.andIsparentEqualTo(1L); //不是父
		}
		list = itemCategoryMapper.selectByExample(example);
		
		return list;
	}
	
	

}
