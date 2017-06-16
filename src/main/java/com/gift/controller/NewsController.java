package com.gift.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.service.NewsService;
import com.gift.vo.GiftNews;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	/**
	 * 后台
	 * 跳转到页面
	 * @Title: basicHome   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月13日 下午3:28:04
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	public ModelAndView basicHome(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("basic/news");
		return modelAndView;
	}
	/**
	 * 后台
	 * 添加最新动态
	 * @Title: addNews   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param news
	 * @param: @param request
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 上午9:44:06
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/addNews.html")
	public ModelAndView addNews(GiftNews news,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		newsService.addNews(news, request);
		modelAndView.setViewName("basic/news");
		return modelAndView;
	}
	/**
	 * 后台
	 * 获取记录集合 
	 * @Title: getContents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 上午10:17:24
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/contents.html")
	@ResponseBody
	public String getContents(HttpServletRequest request,HttpServletResponse response){
		//获取页面数据
		try{
			Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
			List<GiftNews> list = newsService.getNews(map);
			//取记录总条数
	        PageInfo<GiftNews> pageinfo = new PageInfo<>(list);
			int total = (int)pageinfo.getTotal();
	      	//封装数据返回
	      	JsonToTable.jsonPrintObject(response, list, total);
		}catch(Exception exception){
			exception.printStackTrace();
			Printer.error("查询文章未知错误");
		}
		return null;
	}
	/**
	 * 后台
	 * 获取单个记录信息
	 * @Title: getContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 上午10:41:41
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/getContent/{id}"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getContent(@PathVariable String id){
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		GiftNews news = newsService.getNewsById(id);
		jsonObject.put("msg", news);
		msg = jsonObject.toString();
		return msg;
	}
	@RequestMapping(value={"/getContentByView/{id}"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getContentByView(@PathVariable String id){
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		GiftNews news = newsService.getNewsById(id);
		jsonObject.put("msg", news.getContent());
		msg = jsonObject.toString();
		return msg;
	}
	/**
	 * 后台
	 * 删除（更新状态）
	 * @Title: deleteNew   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月14日 下午4:37:14
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/deleteNew/{id}/{state}")
	public ModelAndView deleteNew(@PathVariable String id,@PathVariable String state){
		ModelAndView modelAndView = new ModelAndView();
		newsService.updateNews(id, Long.parseLong(state));
		modelAndView.setViewName("basic/news");
		return modelAndView;
	}
}
