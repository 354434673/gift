package com.gift.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * 
 * @ClassName:  ItemController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月5日 下午4:12:15
 */
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonToTable;
import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.service.CustomerService;
import com.gift.service.ItemCategoryService;
import com.gift.service.ItemService;
import com.gift.vo.Customer;
import com.gift.vo.GiftItem;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemDesc;
import com.gift.vo.GiftItemDescModel;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/item")
public class ItemController {
	/**
	 * 商品分类
	 */
	@Autowired
	private ItemCategoryService itemCategoryService;
	/**
	 * 商品
	 */
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CustomerService cuseromerService;
	
	@Autowired
	private JedisClientSingle jedisClientSingle;
	/**
	 * 封装多级列表 返回页面
	 * @Title: basic   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月9日 下午2:06:05
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	public ModelAndView basic(){
		ModelAndView modelAndView = new ModelAndView();
		//实例化树形参数
		List<Map<String, List<GiftItemCategory>>> categoryList = new ArrayList<Map<String, List<GiftItemCategory>>>();
		List<GiftItemCategory> categoryMainList = itemCategoryService.getItemCategoryListByParentId(1L);
		for (GiftItemCategory itemCategory : categoryMainList) {
			Map<String, List<GiftItemCategory>> projectMap = new HashMap<String, List<GiftItemCategory>>();
			List<GiftItemCategory> list = itemCategoryService.getItemCategoryListByParentId(Long.parseLong(itemCategory.getId()));
			projectMap.put(itemCategory.getName(),list);
			categoryList.add(projectMap);
		}
		//设置视图
		modelAndView.setViewName("basic/giftitem");
		modelAndView.addObject("categoryList", categoryList);
		return modelAndView;
	}
	/**
	 * 添加商品  
	 * service封装数据
	 * @Title: addItem   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param item
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月9日 下午2:09:33
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/addItem.html"},method={RequestMethod.POST})
	public String addItem(@RequestParam("myfileItem") MultipartFile[] myfileItem, GiftItem item,HttpServletRequest request){
		try {
			itemService.addItem(myfileItem, item, request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/item/basic.html";
	}
	
	/**
	 * 获取集合并返回数据
	 * 
	 * @Title: getItemList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年5月9日 下午6:00:17
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/contents.html")
	@ResponseBody
	public String getItemList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<GiftItem> list = itemService.getItemList(map);
		//取记录总条数
        PageInfo<GiftItem> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	/**
	 * 刷新后台页面
	 * @Title: getItemListByCondition   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param title
	 * @param: @param categoryId
	 * @param: @param request
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年5月15日 上午10:00:01
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/getItemListByCondition/{title}/{categoryId}"},method={RequestMethod.POST})
	@ResponseBody
	public String getItemListByCondition( @PathVariable String title,@PathVariable String categoryId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		map.put("title",title );
		map.put("categoryId",categoryId);
		List<GiftItem> list = itemService.getItemListByCondition(map);
		//取记录总条数
        PageInfo<GiftItem> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	/**
	 * @throws IOException 
	 * 后台
	 * 获取商品的参数  用于修改商品参数
	 * @Title: getItem   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemId
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月22日 下午2:10:33
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/getItem/{itemId}"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getItem(@PathVariable String itemId) throws IOException{
		GiftItem item = itemService.getItemById(itemId);
		String jsonString = JsonUtil.objectToJson(item);
		return jsonString;
	}
	/**
	 * @throws IOException 
	 * 后台
	 * 更新商品信息（*后期可以加上商品编号等等信息*）
	 * @Title: modifyItem   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param item
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月23日 上午9:59:33
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/modifyItem.html")
	public  String  modifyItem(GiftItem item) throws IOException{
		itemService.updateItem(item);
		//更新商品数据，更新缓存
		this.syncCache();
		return "redirect:/item/basic.html";
	}
	/**
	 * 前台
	 * 获取商品参数，跳转到商品详情页面
	 * @Title: showItemById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月15日 上午10:12:29
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/showItem/{id}")
	public ModelAndView showItemById(@PathVariable String id){
		
		ModelAndView modelAndView = new ModelAndView();
		//商品信息
		GiftItem item = itemService.getItemById(id);
		modelAndView.addObject("item", item);
		//商品详情
		List<GiftItemDesc> descs = itemService.getItemDescs(id);
		modelAndView.addObject("itemDescs", descs);
		modelAndView.setViewName("shop/item/item");
		return modelAndView;
	}
	/**
	 * 前台
	 * 分页获取商品  
	 * @Title: showItemsByPageNum   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param pageNum
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月19日 下午3:42:23
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/showItemsByPageNum/{pageNum}")
	public ModelAndView showItemsByPageNum(@PathVariable String pageNum,HttpServletRequest request){
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String customerId = null;
		List<GiftItem> list = null;
		int total = 0;
		ModelAndView modelAndView = new ModelAndView();
		if(customer != null ){
			customerId = customer.getId();
			String firstPage = jedisClientSingle.hget("customer:items:"+customerId, pageNum);
			if(firstPage != null){
				list = JsonUtil.jsonToList(firstPage, GiftItem.class);
				total = Integer.valueOf(jedisClientSingle.get("customer:item:"+customer.getId()));
			}else{
				list = itemService.getItemLists(pageNum,null);
				PageInfo<GiftItem> pageinfo = new PageInfo<>(list);
				total = (int)pageinfo.getTotal();
			}
		}else{
			list = itemService.getItemLists(pageNum,null);
			PageInfo<GiftItem> pageinfo = new PageInfo<>(list);
			total = (int)pageinfo.getTotal();
		}
		modelAndView.addObject("currentPage",Integer.parseInt(pageNum));
		//向上取整
		modelAndView.addObject("totalPage",Math.ceil((double)total/8));
		modelAndView.addObject("itemLists",list);
		modelAndView.setViewName("shop/item/items");
		return modelAndView;
	}
	/**
	 * @throws IOException 
	 * 后台
	 * 修改商品   上架  下架状态
	 * @Title: modifyState   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @param state
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月15日 下午4:58:46
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/modifyState/{id}/{state}")
	@ResponseBody
	public String modifyState(@PathVariable String id,@PathVariable Long state) throws IOException{
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		itemService.modifyItemById(id, state);
		//更新商品状态  更新缓存
		this.syncCache();
		jsonObject.put("msg", "OK");
		msg = jsonObject.toString();
		return msg;
	}
	/**
	 * 后台
	 * 接收多个参数
	 * @Title: modifyItemDesc   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemDescs
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月16日 上午10:51:06
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping(value={"/modifyItemDesc.html"},method={RequestMethod.POST})
	public ModelAndView modifyItemDesc(GiftItemDescModel itemDescs){
		List<GiftItemDesc> descs = itemDescs.getItemDescs();
		if(descs == null || descs.size() <=0){
			Printer.error("无数据");
		}else {
			itemService.addItemDesc(descs);
		}
		return new ModelAndView("/item/basic.html");
	}
	/**
	 * 后台
	 * 查询详细的配置集合  返回json数据
	 * @Title: getItemDescs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemId
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月16日 下午2:24:37
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/getItemDescs/{itemId}"},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getItemDescs(@PathVariable String itemId){
		List<GiftItemDesc> descs = itemService.getItemDescs(itemId);
		GiftItemDescModel descModel = new GiftItemDescModel();
		descModel.setItemDescs(descs);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", descModel);
		return jsonObject.toString();
	}
	/**
	 * @throws Exception 
	 * 后台
	 * 同步缓存
	 * @Title: syncCache   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月7日 下午2:49:42
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/syncCache.html"})
	@ResponseBody
	public String syncCache() throws IOException{
		List<Customer> customers = cuseromerService.getCompanyCustomer(0L);
		itemService.syncCache(customers);
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		jsonObject.put("msg", 200);
		msg = jsonObject.toString();
		return msg;
	}
	/**
	 * 前台
	 * 查询商品
	 * @Title: searchByName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemName
	 * @param: @param request
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月12日 上午9:38:07
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/searchByName.html")
	public ModelAndView searchByName(@RequestParam String itemName,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<GiftItem> list = itemService.getItemListByName(itemName, request);
		PageInfo<GiftItem> pageinfo = new PageInfo<>(list);
		int total = 0;
		modelAndView.addObject("currentPage",Integer.parseInt("1"));
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//向上取整
		try{
			if(customer != null){
				total = Integer.parseInt(jedisClientSingle.get("customer:item:"+customer.getId()));
				modelAndView.setViewName("shop/item/items");
			}else{
				modelAndView.setViewName("shop/user/userLogin");
			}
		}catch(Exception e){
			total = (int)pageinfo.getTotal();
		}
		modelAndView.addObject("totalPage",Math.ceil((double)total/8));
		modelAndView.addObject("itemLists",list);
		return modelAndView;
	}
	
}
