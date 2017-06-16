package com.gift.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.service.ContentCategoryService;
import com.gift.vo.GiftContentCategory;
import com.gift.vo.User;
import com.github.pagehelper.PageInfo;
/**
 * 前台页面主要内容
 * @ClassName:  ContentController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月20日 下午2:18:36
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 跳转到树形主页面
	 * @Title: basicContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月20日 下午2:19:30
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	public ModelAndView basicContent(){
		Printer.info("进入到了首页分类页面");
		return new ModelAndView("basic/contentCategory");
	}
	/**
	 * 获取分类集合
	 * @Title: getContents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年4月21日 上午9:30:02
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/contents.html")
	@ResponseBody
	public ModelAndView getContents(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<GiftContentCategory> list = contentCategoryService.getContentCategorys(map);
		//取记录总条数
        PageInfo<GiftContentCategory> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	
	
}
