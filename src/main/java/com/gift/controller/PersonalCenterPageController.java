package com.gift.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonUtil;
import com.gift.mapper.CustomerLogoFixMapper;
import com.gift.service.CustomerLogoFixService;
import com.gift.service.LogoService;
import com.gift.vo.CustomerLogoFix;
import com.gift.vo.Logo;

/**
 * 
 * @ClassName:  PersonalCenterPageController   
 * @Description: 个人中心
 * @author YangNingSheng
 * @date 2017年5月16日 下午2:09:44
 */
@Controller
@RequestMapping("/PersonalCenterPage")
public class PersonalCenterPageController {
    @Autowired
    private LogoService logoService;
    @Autowired
    private CustomerLogoFixService customerLogoFixService;
    @Autowired 
    private CustomerLogoFixMapper logoFixMapper;
    
    /**
     * @Title: mainPage   
     * @Description: 个人中心主页面
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月16日 下午2:10:15
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/mainPage.html")
    public ModelAndView mainPage(){
	return new ModelAndView("shop/user/personalCenter/main");
    }
    /**
     * @Title: correlationLogoPage   
     * @Description: 上传LOGO 
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月16日 下午2:11:22
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/correlationLogo.html")
    public ModelAndView correlationLogoPage(){
	return new ModelAndView("shop/user/personalCenter/correlationLogo");
    }
    /**
     * @Title: updatePwdPage   
     * @Description: 个人中心修改密码页面 
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月19日 下午3:01:52
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/updatePwd.html")
    public ModelAndView updatePwdPage(){
	return new ModelAndView("shop/user/personalCenter/updatePwd");
    }
    /**
     * @Title: Logo   
     * @Description: 前台企业用户LOGO管理 
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月22日 上午10:02:29
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/myLogo.html")
    public ModelAndView myLogoPage(String customerId,Long state){
		
	ModelAndView modelAndView = new ModelAndView();
	
	List<Logo> queryAllLogoByCustomerId = logoService.queryAllLogoByCustomerId(customerId,state);
	
	modelAndView.addObject("title",queryAllLogoByCustomerId.size());
	
	modelAndView.addObject("logo", queryAllLogoByCustomerId);
	
	modelAndView.setViewName("shop/user/personalCenter/myLogo");
	
	return modelAndView;
    }
    /**
     * @Title: LogoFixPage   
     * @Description: 前台企业用户LOGO拼接管理 
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月22日 上午10:02:29
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/myLogoFix.html")
    public ModelAndView myLogoFixPage(String customerId,Long state, @RequestParam(value="page",defaultValue="1")Long page){
	
	ModelAndView modelAndView = new ModelAndView();
	
	List<CustomerLogoFix> logoFixsByCustomerId = customerLogoFixService.getLogoFixsByCustomerId(customerId, state, page);

	Integer titleCount = customerLogoFixService.getTitleCount(customerId, state);
	
	modelAndView.addObject("title",titleCount);
	
	modelAndView.addObject("logoFix", logoFixsByCustomerId);
	
	modelAndView.setViewName("shop/user/personalCenter/myLogoFix");
	
	return modelAndView;
    }
    /**
     * @throws IOException 
     * @Title: myLogoFixPageForAjax   
     * @Description: 用于分页的异步查询 
     * @param: @param customerId
     * @param: @param state
     * @param: @param page
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年6月6日 上午10:39:17
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping(value = "/myLogoFixForAjax.html",produces = "text/html;charset=UTF-8")
    public @ResponseBody String myLogoFixPageForAjax(String customerId,Long state, Long page) throws IOException{
	
	ModelAndView modelAndView = new ModelAndView();
	
	List<CustomerLogoFix> logoFixsByCustomerId = customerLogoFixService.getLogoFixsByCustomerId(customerId, state, page);
	
	String objectToJson = JsonUtil.objectToJson(logoFixsByCustomerId);
	
	return objectToJson;
    }
    /**
     * @Title: Logo   
     * @Description: 前台用户已经删除的LOGO
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月22日 上午10:02:29
     * @return: ModelAndView      
     * @throws
     */
    @RequestMapping("/deleLogo.html")
    public ModelAndView myDeleLogopage(String customerId,Long state){
	
	ModelAndView modelAndView = new ModelAndView();
	
	List<Logo> queryAllLogoByCustomerId = logoService.queryAllLogoByCustomerId(customerId,state);
	
	modelAndView.addObject("title",queryAllLogoByCustomerId.size());
	
	modelAndView.addObject("logo", queryAllLogoByCustomerId);
	
	modelAndView.setViewName("shop/user/personalCenter/deleLogo");
	
	return modelAndView;
    }

}
