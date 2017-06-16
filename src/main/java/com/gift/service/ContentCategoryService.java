package com.gift.service;

/**
 * 首页分类serviceI
 * @ClassName:  ContentCategoryService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月20日 下午4:43:17
 */
import java.util.List;
import java.util.Map;

import com.gift.vo.GiftContentCategory;
public interface ContentCategoryService {
	
	List<GiftContentCategory> getContentCategorys(Map<String, Object> map);
	
	List<GiftContentCategory> getContentCategoryList();
	
}
