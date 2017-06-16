package com.gift.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gift.commons.FinalData;
import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.service.LogoService;
import com.gift.vo.Customer;
import com.gift.vo.Logo;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: PersonalCenterPageController
 * @Description: 个人中心
 * @author YangNingSheng
 * @date 2017年5月16日 下午2:09:44
 */
@Controller
@RequestMapping("/PersonalCenter")
public class PersonalCenterController {
    @Autowired
    private LogoService logoService;

    /**
     * @Title: upload   
     * @Description: 上传logo
     * @param: @param fi
     * @param: @param request
     * @param: @param logo
     * @param: @return
     * @param: @throws IllegalStateException
     * @param: @throws IOException  
     * @author YangNingSheng    
     * @date 2017年5月22日 下午2:13:00
     * @return: String      
     * @throws
     */
    @RequestMapping("uploadLogo.html")
    public String upload(MultipartFile fi, HttpServletRequest request, Logo logo)
	    throws IllegalStateException, IOException {

	logoService.addLogo(fi, request, logo);

	return "redirect:/PersonalCenterPage/correlationLogo.html";

    }
    /**
     * @Title: updataState   
     * @Description: 下架图片
     * @param: @param id
     * @param: @param stateDescribe 状态描述(下架原因)
     * @param: @param state
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月23日 下午3:58:59
     * @return: String      
     * @throws
     */
    @RequestMapping("updataState.html")
    public  @ResponseBody String updataState(String id,String stateDescribe, Long state){
	
	String updateLogoState = logoService.updateLogoState(id, stateDescribe, state);
	
	return updateLogoState;
    }
    @RequestMapping("getLogoTitle.html")
    public  @ResponseBody String getLogoTitleByCustomer(String customerId){
	
	List<Logo> queryAllLogoByCustomerId = logoService.queryAllLogoByCustomerId(customerId, 0L);
	
	Integer size = queryAllLogoByCustomerId.size();
	
	return size >= 5 ? FinalData.RETURN_NO:FinalData.RETURN_YES;
    }
    
    /**
     * @Title: queryAllLogo   
     * @Description: 显示所有LOGO
     * @param: @param request
     * @param: @param response
     * @param: @param state
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月23日 下午3:59:32
     * @return: String      
     * @throws
     */
    @RequestMapping("/queryAllLogo.html")
    public @ResponseBody String queryAllLogo(HttpServletRequest request, HttpServletResponse response, Long state) {
	// 获取页面数据
	try {
	    Map<String, Object> map = JsonToTable.getObjectConditions(request, response);

	    List<Logo> queryAllLogo = logoService.queryAllLogo(map, state);
	    // 取记录总条数
	   PageInfo<Logo> pageInfo = new PageInfo<>(queryAllLogo);

	    int total = (int) pageInfo.getTotal();
	    // 封装数据返回
	    Printer.info("数据正常显示");

	    JsonToTable.jsonPrintObject(response, queryAllLogo, total);
	} catch (Exception e) {
	    Printer.error("数据错误");
	    e.printStackTrace();
	}
	return null;
    }
    /**
     * 后台
     * @Title: queryLogoAndCustomerByNameOrTitle   
     * @Description: 模糊查询
     * @param: @param request
     * @param: @param response
     * @param: @param condition
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年6月13日 下午4:58:43
     * @return: String      
     * @throws
     */
    @RequestMapping("/queryLogoAndCustomerByNameOrTitle.html")
    public @ResponseBody String queryLogoAndCustomerByNameOrTitle(HttpServletRequest request, HttpServletResponse response,@RequestParam String condition) {
	// 获取页面数据
	try {
	    Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
	    
	    List<Logo> queryLogoAndCustomerByNameOrTitle = logoService.queryLogoAndCustomerByNameOrTitle(map, condition);
	    // 取记录总条数
	    PageInfo<Logo> pageInfo = new PageInfo<>(queryLogoAndCustomerByNameOrTitle);
	    
	    int total = (int) pageInfo.getTotal();
	    // 封装数据返回
	    Printer.info("数据正常显示");
	    
	    JsonToTable.jsonPrintObject(response, queryLogoAndCustomerByNameOrTitle, total);
	} catch (Exception e) {
	    Printer.error("数据错误");
	    e.printStackTrace();
	}
	return null;
    }

}
