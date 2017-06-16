package com.gift.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.FailedRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.service.ContentService;
import com.gift.service.ItemService;
import com.gift.service.NewsService;
import com.gift.vo.Customer;
import com.gift.vo.GiftContent;
import com.gift.vo.GiftItem;
import com.gift.vo.GiftNews;
import com.github.pagehelper.PageInfo;

import redis.clients.jedis.Jedis;

/**
 * @ClassName:  PageController   
 * @Description:商城页面跳转控制层
 * @author YangNingSheng
 * @date 2017年4月20日 下午2:52:18
 */
@Controller
@RequestMapping("/shopPage")
public class ShopPageController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private JedisClientSingle jedisClientSingle;
	/**
	 * 跳转到主页面
	 * @Title: basic   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月17日 上午9:42:16
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	@ResponseBody
	public ModelAndView basic(){
		ModelAndView modelAndView = new ModelAndView();
		//做一定的查询
		List<GiftContent> homeContents = contentService.getHomeContents();
		List<GiftContent> homePageContents = contentService.getHomePageContents();
		modelAndView.addObject("bannerContents",homeContents);
		modelAndView.addObject("homePageContents",homePageContents);
		modelAndView.setViewName("shop/shophome");
		return modelAndView;
	}
	/**
	 * 跳转到公司简介
	 * @Title: companyInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月17日 上午9:42:37
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/companyInfo.html")
	@ResponseBody
	public ModelAndView companyInfo(){
		return new ModelAndView("shop/companyInfo");
	}
	/**
	 * @Title: registPage   
	 * @Description: 注册跳转页面   
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月18日 下午12:32:10
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/registCheck.html")
	@ResponseBody
	public ModelAndView registPage(){
		
		return new ModelAndView("shop/user/userRegist");
		
	}
	/**
	 * @Title: loginPage   
	 * @Description: 登陆跳转页面
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月18日 下午12:32:13
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/loginCheck.html")
	public ModelAndView loginPage(){
		
		return new ModelAndView("shop/user/userLogin");
		
	}
	/**
	 * @Title: groupApplyUserPage   
	 * @Description: 企业用户申请管理页面 
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:08:24
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/groupApplyUserInfo.html")
	public ModelAndView groupApplyUserPage(){
		
		return new ModelAndView("basic/groupApplyInfo");
		
	}
	/**
	 * @Title: customerManagerPage   
	 * @Description: 客户管理页面
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:08:39
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/customerManager.html")
	public ModelAndView customerManagerPage(){
		
		return new ModelAndView("basic/customerManager");
		
	}
	/**
	 * @Title: customerResetPwd   
	 * @Description: 重置密码  
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月8日 下午3:39:30
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/customerResetPwd.html")
	public ModelAndView customerResetPwd(){
		
		return new ModelAndView("shop/user/resetPwd");
		
	}
	/**
	 * @Title: logoManager   
	 * @Description: 后台企业用户LOGO管理
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月22日 上午10:01:47
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/logoManager.html")
	public ModelAndView logoManager(){
	    
	    return new ModelAndView("basic/logoManager");
	    
	}
	/**
	 * @Title: successCasePage   
	 * @Description: 成功案例页面 
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月12日 下午5:03:38
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/successCasePage.html")
	public ModelAndView successCasePage(){
	    
	    return new ModelAndView("shop/successCase");
	    
	}
	/**
	 * @Title: newMessagePage   
	 * @Description: 最新动态页面 
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月14日 上午10:02:27
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/newMessagePage.html")
	public ModelAndView newMessagePage(){
	    
            	List<GiftNews> newMessage = newsService.getNewMessage();
            	
            	ModelAndView modelAndView = new ModelAndView();
            	
            	modelAndView.addObject("message", newMessage);
            
            	modelAndView.setViewName("shop/newMessage");
            
            	return modelAndView;
	}
	/**
	 * 前台
	 * @Title: articlePage   
	 * @Description: 文章页面
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月14日 下午5:10:42
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/articlePage.html")
	public ModelAndView articlePage(String id){
	    
	    GiftNews newsById = newsService.getNewsById(id);
	    
	    ModelAndView modelAndView = new ModelAndView();
	    
	    modelAndView.addObject("article", newsById);
	    
	    modelAndView.setViewName("shop/article");
	    
	    return modelAndView;
	}
	/**
	 * 
	 * @Title: showItemsPage   
	 * @Description: TODO(跳转到商品页面)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月15日 上午9:30:32
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/showItemsPage.html")
	public ModelAndView showItemsPage(HttpServletRequest request){
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String customerId = null;
		List<GiftItem> list = null;
		int total = 0;
		PageInfo<GiftItem> pageinfo = null;
		if(customer != null){
			try{
				customerId = customer.getId();
				String firstPage = jedisClientSingle.hget("customer:items:"+customerId, "1");
				//如果有缓存
				if(firstPage != null){
					list = JsonUtil.jsonToList(firstPage, GiftItem.class);
					total = Integer.valueOf(jedisClientSingle.get("customer:item:"+customer.getId()));
				}else{
					//该用户没有缓存时候
					list = itemService.getItemLists(null,customerId);
					pageinfo = new PageInfo<>(list);
					total = (int)pageinfo.getTotal();
				}
			}catch(Exception e){
				//redis宕机
				Printer.error("缓存服务器redis没有获取链接");
				list = itemService.getItemLists(null,customerId);
				pageinfo = new PageInfo<>(list);
				total = (int)pageinfo.getTotal();
			}
		}else{
			//无用户登陆时候
			list = itemService.getItemLists(null,customerId);
			pageinfo = new PageInfo<>(list);
			total = (int)pageinfo.getTotal();
		}
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("currentPage",1);
		//向上取整
		modelAndView.addObject("totalPage",Math.ceil((double)total/8));
		modelAndView.addObject("itemLists",list);
		modelAndView.setViewName("shop/item/items");
		return modelAndView;
	}

}
