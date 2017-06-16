package com.gift.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.GiftNewsMapper;
import com.gift.service.NewsService;
import com.gift.vo.GiftNews;
import com.gift.vo.GiftNewsExample;
import com.gift.vo.GiftNewsExample.Criteria;
import com.gift.vo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private GiftNewsMapper newsMapper;
	
	/**
	 * 后台
	 * 添加新闻
	 * <p>Title: addNews</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月14日 上午9:09:40
	 * @param news   
	 * @see com.gift.service.NewsService#addNews(com.gift.vo.GiftNews)
	 */
	@Override
	public void addNews(GiftNews news,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if(StringUtil.isNotEmpty(news.getId())){
			//更新数据
			news.setUpdated(System.currentTimeMillis());
			newsMapper.updateByPrimaryKeySelective(news);
		}else{
			//封装数据
			news.setId(UUIDHexGenerator.get());
			news.setAuther(user.getName());
			news.setAutherid(user.getId());
			news.setCreated(System.currentTimeMillis());
			news.setUpdated(System.currentTimeMillis());
			news.setState(1L);
			newsMapper.insert(news);
		}
	}
	
	/**
	 * 后台
	 * 获取集合数据
	 * <p>Title: getNews</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月14日 上午10:10:29
	 * @param map
	 * @return   
	 * @see com.gift.service.NewsService#getNews(java.util.Map)
	 */
	@Override
	public List<GiftNews> getNews(Map<String, Object> map) {
		//实例化查询
		GiftNewsExample example = new GiftNewsExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		//执行查询
		Criteria criteria = example.createCriteria();
		example.setOrderByClause(" updated desc ");
		//添加查询条件
		//criteria.andStateEqualTo(0L);
		List<GiftNews> list = newsMapper.selectByExample(example);
		return list;
	}
	/**
	 * 后台
	 * 获取单个记录
	 * <p>Title: getNewsById</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月14日 上午10:39:55
	 * @param id
	 * @return   
	 * @see com.gift.service.NewsService#getNewsById(java.lang.String)
	 */
	@Override
	public GiftNews getNewsById(String id) {
		
		return newsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 后台
	 * 更新状态
	 * <p>Title: updateNews</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月14日 下午4:41:05
	 * @param id
	 * @param state   
	 * @see com.gift.service.NewsService#updateNews(java.lang.String, java.lang.Long)
	 */
	@Override
	public void updateNews(String id, Long state) {
		GiftNews news = new GiftNews();
		news.setId(id);
		news.setState(state);
		newsMapper.updateByPrimaryKeySelective(news);
	}

	/**
	 * 前台
	 * 获得最新的N条数据
	 * <p>Title: getNewMessage</p>   
	 * <p>Description: </p>  
	 * @author YangNingSheng
	 * @date 2017年6月14日 下午2:36:10
	 * @return   
	 * @see com.gift.service.NewsService#getNewMessage()
	 */
	@Override
	public List<GiftNews> getNewMessage() {
	    
	    List<GiftNews> selectByExampleForTitle = newsMapper.selectByExampleForTitle(5);
	    
	    return selectByExampleForTitle;
	}


}
