package com.gift.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @ClassName:  ItemCategoryController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月4日 上午11:21:07
 */
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonToTable;
import com.gift.service.ItemCategoryService;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.User;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController {
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	/**
	 * 跳到主页面
	 * @Title: basicView   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月4日 上午11:27:22
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	public ModelAndView basicView(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("basic/itemCate");
		return modelAndView;
	}
	/**
	 * 添加产品分类
	 * @Title: addItemCategory   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemCategory
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月5日 下午2:11:34
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/addItemCategory.html"},method={RequestMethod.POST})
	public String addItemCategory(GiftItemCategory itemCategory){
		itemCategoryService.addItemCategory(itemCategory);
		return "redirect:/itemCategory/basic.html";
	}
	/**
	 * @throws Exception 
	 * 获取列表页面
	 * @Title: contents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月5日 下午3:20:47
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping(value={"/contents.html"})
	@ResponseBody
	public ModelAndView contents(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<GiftItemCategory> list = itemCategoryService.getItemCategoryList(map);
		//取记录总条数
        PageInfo<GiftItemCategory> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	
}
