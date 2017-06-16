package com.gift.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gift.vo.GiftNews;

public interface NewsService {
	/**
	 * 后台
	 * 添加
	 * @Title: addNews   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param news  
	 * @author kevin    
	 * @date 2017年6月13日 下午11:01:40
	 * @return: void      
	 * @throws
	 */
	void addNews(GiftNews news,HttpServletRequest request);
	
	/**
	 * 后台
	 * 分页获取
	 * @Title: getNews   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param map
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 上午10:38:48
	 * @return: List<GiftNews>      
	 * @throws
	 */
	List<GiftNews> getNews(Map<String, Object> map);
	/**
	 * 后台获取单个信息
	 * 
	 * @Title: getNewsById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 下午4:37:45
	 * @return: GiftNews      
	 * @throws
	 */
	GiftNews getNewsById(String id);
	
	void updateNews(String id,Long state);
	/**
	 * 前台
	 * @Title: getNewMessage   
	 * @Description: 获取最新的文章 
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月14日 下午2:27:34
	 * @return: List<GiftNews>      
	 * @throws
	 */
	List<GiftNews> getNewMessage();
}
