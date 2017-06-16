package com.gift.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * 首页大广告和详情
 * @ClassName:  ContentController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月21日 上午9:37:10
 */
import org.springframework.web.servlet.ModelAndView;

import com.gift.annotation.Token;
import com.gift.commons.JsonToTable;
import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.service.ContentCategoryService;
import com.gift.service.ContentService;
import com.gift.vo.GiftContent;
import com.gift.vo.GiftContentCategory;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 跳转到主页面
	 * 注意将token带到页面
	 * @Title: basic   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月21日 上午9:45:10
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	@Token(save=true)
	public ModelAndView basic(){
		ModelAndView modelAndView = new ModelAndView();
		List<GiftContentCategory> categoryList = contentCategoryService.getContentCategoryList();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("basic/content");
		return modelAndView;
	}
	/**
	 * 上传banner 
	 * 注意调用方法时就将token去掉
	 * @Title: addBanner   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param myfilesBanner
	 * @param: @param request
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月24日 上午11:45:15
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/addBanner.html"},method={RequestMethod.POST,RequestMethod.GET})
	@Token(remove=true)
	public String addBanner(@RequestParam MultipartFile[] myfilesBanner,HttpServletRequest request) throws Exception{
		try {
			contentService.addContentFileUpLoad(myfilesBanner,request);
		} catch (Exception e) {
			Printer.error("上传失败");
			e.printStackTrace();
		}
		return "redirect:/content/basic.html";
	}
	/**
	 * 上传首页大图
	 * @Title: addHomePage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param myfiles
	 * @param: @param request
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月26日 上午11:57:06
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"addHomePage.html"},method={RequestMethod.POST,RequestMethod.GET})
	@Token(remove=true)
	public String addHomePage(@RequestParam MultipartFile[] myfiles,HttpServletRequest request){
		try {
			contentService.addHomePageFileUpload(myfiles, request);
		} catch (Exception e) {
			Printer.error("上传失败");
			e.printStackTrace();
		}
		//不用考虑token的问题
		return "redirect:/content/basic.html";
	}
	/**
	 * 获取列表
	 * @Title: contents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年4月25日 下午4:56:52
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping(value={"/contents"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView contents(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<GiftContent> list = contentService.getContents(map);
		PageInfo<GiftContent> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	/**
	 * 逻辑删除banner
	 * @Title: deleteBanner   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @param response
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月26日 上午10:29:02
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/deleteBanner/{id}"},method={RequestMethod.POST})
	@ResponseBody
	public String deleteBanner(@PathVariable String id,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		int deleteBanner = contentService.deleteBanner(id);
		if(deleteBanner != 0){
			jsonObject.put("msg", "OK");
			msg = jsonObject.toString();
			Printer.info("删除banner成功");
		}else{
			jsonObject.put("msg", "NO");
			msg = jsonObject.toString();
			Printer.info("删除banner失败");
		}
		return msg;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @Title: getContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月27日 下午4:25:37
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/getContent/{id}"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  getContent(@PathVariable String id) throws IOException{
		GiftContent content = contentService.getGiftContentById(id);
		String jsonString = JsonUtil.objectToJson(content);
		return jsonString;
	}
	/**
	 * 修改信息
	 * @Title: modifyContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param content
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月28日 下午3:08:01
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/modifyContent.html"})
	public String modifyContent(GiftContent content){
		contentService.updateGiftContent(content);
		return "redirect:/content/basic.html";
	}
	
}
