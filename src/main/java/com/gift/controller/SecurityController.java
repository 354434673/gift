package com.gift.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.commons.FinalData;
import com.gift.commons.JsonUtil;
import com.gift.commons.TreeJson;
import com.gift.service.SecurityService;
import com.gift.vo.Customer;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemCategoryExample;
import com.gift.vo.GiftItemCategoryExample.Criteria;
import com.gift.vo.GiftItemExample;

/**
 * @ClassName:  SecurityController   
 * @Description:该类为权限控制类
 * @author YangNingSheng
 * @date 2017年5月26日 上午11:55:21
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;
    
    @RequestMapping(value={"/itemCategory.html"},produces = "text/html;charset=UTF-8")
    public @ResponseBody String getItemCategory(String customerId) throws IOException{
	
	List<TreeJson> itemCategory = securityService.getItemCategory(customerId);
	
	String objectToJson = JsonUtil.objectToJson(itemCategory);
	
	return objectToJson;
    }
	@RequestMapping(value={"/addItemCategory.html"},method={RequestMethod.POST})
	public @ResponseBody String addItemCategory(@RequestParam(value = "categoryId[]") String[] categoryId,String customerId){
		
	    	for (int i = 0; i < categoryId.length; i++) {
	    	    securityService.addItemCategory(customerId, categoryId[i]);
		}
		return FinalData.RETURN_YES;
	}
}
