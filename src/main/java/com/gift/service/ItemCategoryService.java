package com.gift.service;

import java.util.List;
import java.util.Map;

import com.gift.vo.GiftItemCategory;

public interface ItemCategoryService {
	
	void addItemCategory(GiftItemCategory itemCategory);
	
	List<GiftItemCategory> getItemCategoryList(Map<String, Object> map);
	
	List<GiftItemCategory> getItemCategoryListByParentId(Long parentId);
	
	
	
}
