package com.gift.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 图片合成
 * @ClassName:  ItemPictureFixController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年5月23日 下午2:35:14
 */
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.gift.annotation.Token;
import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.commons.image.ImgInfo;
import com.gift.commons.image.ImgWmark;
import com.gift.commons.image.TextInfo;
import com.gift.service.CustomerLogoFixService;
import com.gift.service.ItemService;
import com.gift.service.LogoService;
import com.gift.vo.Customer;
import com.gift.vo.CustomerLogoFix;
import com.gift.vo.GiftItem;
import com.gift.vo.Logo;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/picture")
public class ItemPictureFixController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private LogoService logoService;
	@Autowired
	private CustomerLogoFixService logoFixService;
	
	/**
	 * 前台
	 * 判断session(customer)是否存在，如果不存在就跳转到登陆页面，登陆成功后返回匹配LOGO界面
	 * @Title: checkCustomer   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月23日 下午2:56:58
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/checkCustomer/{itemId}")
	@Token(save=true)
	public ModelAndView checkCustomer(@PathVariable String itemId,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		if(customer == null){
			Printer.info("无用户登陆，跳转到登陆页面");
			modelAndView.setViewName("shop/user/userLogin");
			modelAndView.addObject("itemId", itemId);
		}else{
			Printer.info("用户登录，跳转到匹配页面");
			//查询商品的信息
			GiftItem item = itemService.getItemById(itemId);
			//查询用户上传的logo
			List<Logo> logos = logoService.queryAllLogoByCustomerId(customer.getId(),0L);
			modelAndView.addObject("item", item);
			modelAndView.addObject("logos", logos.get(0));
			modelAndView.setViewName("shop/item/itemLogoFix");
		}
		return modelAndView;
	}
	/**
	 * 前台
	 * 合成图片
	 * @Title: FixPicture   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemid
	 * @param: @param xsize  点坐标 logo的x - 图片的 x 得到所占图片的比例  
	 * @param: @param ysize		Y轴同理
	 * @param: @param imgsrc
	 * @param: @param imgitem
	 * @param: @param request
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年5月27日 下午4:02:53
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/fixPicture.html"},method={RequestMethod.POST})
	@Token(remove=true)
	public String FixPicture(@RequestParam String itemid,@RequestParam String xsize,@RequestParam String ysize,
			@RequestParam String imgsrc,@RequestParam String imgitem,HttpServletRequest request,final RedirectAttributes redirectAttributes) throws Exception{
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//首先拼接图片路径
		StringBuffer requestURL = request.getRequestURL();
		// 或者前缀 http://localhost:8081
		///fileupload/item/10812f9fe71a4c0c9ab6ea5803a7b858.jpg
		String url = requestURL.toString().substring(0,  requestURL.toString().lastIndexOf("/gift"));
		//商品图片的系统路径
		imgsrc = URLDecoder.decode(imgsrc,"UTF-8");
		imgitem = URLDecoder.decode(imgitem,"UTF-8");
		String baseUrlItemImg =  imgitem.substring(11, imgitem.length());
		baseUrlItemImg = "c:/giftupload"+baseUrlItemImg;
		//读取本地图片，获取图片的尺寸
		File file = new File(baseUrlItemImg);
		BufferedImage sourceImg =ImageIO.read(new FileInputStream(file));   
        //System.out.println(String.format("%.1f",file.length()/1024.0));  
		
		imgsrc = url+imgsrc;
		imgitem = url+imgitem;
		TextInfo textInfo = new TextInfo("", 0, 0);
		double a =((Integer.valueOf(sourceImg.getWidth())).doubleValue())*Double.parseDouble(xsize);
		double b =((Integer.valueOf(sourceImg.getHeight())).doubleValue())*Double.parseDouble(ysize);
		//这里要处理图片的问题(这里图片的精度有问题,double向下转型，会出现精度缺失)
		ImgInfo imgInfo = new ImgInfo(imgsrc, (int)a, (int)b);
		imgInfo.setPathIsUrl(true);
		String imgName = UUIDHexGenerator.get();
        String netName = "/fileupload/logofix"+"/"+imgName+".png";
		ImgWmark.ofUrl(new URL(imgitem)).fontSize(20).text(textInfo).resetG().img(imgInfo).toFile("C:\\giftupload\\logofix\\"+imgName+".png", "png").close();
        //将logo和产品的图片保存。
        logoFixService.addCustomerLogoFix(imgName+".png", customer, itemid);
        //解决form表单提交，页面跳转代参问题
        redirectAttributes.addFlashAttribute("logoFix",netName);
        redirectAttributes.addFlashAttribute("itemid",itemid);
        redirectAttributes.addFlashAttribute("customerId",customer.getId());
		return "redirect:/picture/itemSuccessFix.html";
	}
	/**
	 * 前台
	 * 跳转合成成功页面
	 * @Title: itemSuccessFix   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param redirectAttributes
	 * @param: @param request
	 * @param: @param model
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年6月5日 上午11:31:17
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/itemSuccessFix.html"},method={RequestMethod.GET})
	public String itemSuccessFix(final RedirectAttributes redirectAttributes,HttpServletRequest request,Model model){
	    //RedirectAttributes是Spring mvc 3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转的
		@SuppressWarnings("unchecked")
		Map<String, String> map=(Map<String, String>)RequestContextUtils.getInputFlashMap(request);
	    if(map == null){
	    	//跳转到首页
	    	return "redirect:/shopPage/showItemsPage.html";
	    }
		String logoFix = map.get("logoFix");
		String itemId = map.get("itemid");
		String customerId = map.get("customerId");
		GiftItem item = itemService.getItemById(itemId);
		List<Logo> logos = logoService.queryAllLogoByCustomerId(customerId,0L);
		model.addAttribute("logoFix", logoFix);
		model.addAttribute("item", item);
		model.addAttribute("logos", logos.get(0));
		return "shop/item/itemSuccessFix";
	}
	/**
	 * 后台
	 * 跳转到后台用户logo合成页面
	 * @Title: basic   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月30日 上午10:35:44
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/basic.html")
	public ModelAndView basic(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("basic/logoFix");
		return modelAndView;
	}
	/**
	 * @throws Exception 
	 * 后台
	 * 后去logo合成的页面用户展示
	 * @Title: contents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月30日 下午3:26:02
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/contents.html")
	@ResponseBody
	public String contents(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<CustomerLogoFix> list = logoFixService.getLogoFixs(map);
		//取记录总条数
        PageInfo<CustomerLogoFix> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	/**
	 * 后台
	 * 模糊查询 
	 * @Title: getContentsByCondition   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param customerNameOrItemName
	 * @param: @param request
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception  
	 * @author kevin    
	 * @date 2017年6月13日 上午10:46:37
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/getContentsByCondition.html")
	@ResponseBody
	public String getContentsByCondition(@RequestParam String customerNameOrItemName,HttpServletRequest request,HttpServletResponse response)throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		List<CustomerLogoFix> list = logoFixService.getLogoFixsByCondition(map, customerNameOrItemName);
		//取记录总条数
        PageInfo<CustomerLogoFix> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
}
