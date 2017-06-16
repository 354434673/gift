package com.gift.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.mapper.GiftContentCategoryMapper;
import com.gift.service.ContentCategoryService;
import com.gift.vo.GiftContentCategory;
import com.gift.vo.GiftContentCategoryExample;
import com.gift.vo.GiftContentCategoryExample.Criteria;
import com.github.pagehelper.PageHelper;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private GiftContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<GiftContentCategory> getContentCategorys(Map<String, Object> map) {
		//实例化查询条件
		GiftContentCategoryExample example = new GiftContentCategoryExample();
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
		List<GiftContentCategory> list = contentCategoryMapper.selectByExample(example);
		return list;
	}
	/**
	 * 获取分类的集合
	 * <p>Title: getContentCategoryList</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月21日 上午9:54:44
	 * @return   
	 * @see com.gift.service.ContentCategoryService#getContentCategoryList()
	 */
	@Override
	public List<GiftContentCategory> getContentCategoryList() {
		GiftContentCategoryExample example = new GiftContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);
		List<GiftContentCategory> list = contentCategoryMapper.selectByExample(example);
		return list;
	}

}
