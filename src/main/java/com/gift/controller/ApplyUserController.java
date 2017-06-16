package com.gift.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.gift.commons.FinalData;
import com.gift.commons.JSONUtils;
import com.gift.commons.JsonToTable;
import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.commons.SecurityCode;
import com.gift.commons.SecurityImage;
import com.gift.commons.SecurityCode.SecurityCodeLevel;
import com.gift.service.ApplyUserService;
import com.gift.vo.ApplyUser;
import com.gift.vo.User;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSON;
/**
 * 
 * @ClassName:  HomeController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月14日 下午3:41:38
 */
@Controller
@RequestMapping("/applyUser")
public class ApplyUserController {
	@Autowired
	private ApplyUserService applyUserService;
	/**
	 * @Title: groupApply   
	 * @Description: 企业用户申请 
	 * @param: @param applyUser
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月3日 下午3:48:42
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/groupApply.html"},method={RequestMethod.POST})
	public @ResponseBody String groupApply(ApplyUser applyUser){
		
		return applyUserService.groupApply(applyUser);
			
	}
	/**
	 * @Title: queryGroupUser   
	 * @Description: 查询该用户邮箱是否申请过  
	 * @param: @param name
	 * @param: @param groupPhone
	 * @param: @param email
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月19日 下午4:35:09
	 * @return: String
	 * @throws
	 */
	@RequestMapping(value={"/queryGroupUserByEmail.html"},method={RequestMethod.POST})
	public @ResponseBody String queryGroupUserByEmail(String email){
		
		ApplyUser queryGroupUser = applyUserService.queryGroupUserByEmail(email);
		
		return queryGroupUser == null?FinalData.TURE:FinalData.FALSE;
	}
	/**
	 * @Title: queryGroupUserByPhone   
	 * @Description: 查询该用户电话是否申请过    
	 * @param: @param groupPhone
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午3:05:06
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/queryGroupUserByPhone.html"},method={RequestMethod.POST})
	public @ResponseBody String queryGroupUserByPhone(String groupPhone){
		
		ApplyUser queryGroupUser = applyUserService.queryGroupUserByPhone(groupPhone);
		
		return queryGroupUser == null?FinalData.TURE:FinalData.FALSE;
	}
	/**
	 * @Title: queryGroupUserById   
	 * @Description: 根据id来补全数据
	 * @param: @param id
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午4:19:02
	 * @return: ApplyUser      
	 * @throws
	 */
	@RequestMapping(value={"/queryGroupUserById.html"},produces = "text/html;charset=UTF-8")
	public @ResponseBody String queryGroupUserById(String id){
		
		String objectToJson = null;
		try {
			objectToJson = JsonUtil.objectToJson(applyUserService.queryGroupUserById(id));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectToJson;
	}
	/**
	 * @Title: queryGroupAllApply   
	 * @Description: 展示所有申请的企业用户
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年4月21日 下午4:45:05
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/groupApplyList.html")
	public @ResponseBody String queryGroupAllApply(HttpServletRequest request,HttpServletResponse response,Long state){
		//获取页面数据
		try {
			Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
			
			List<ApplyUser> queryAllApplyUser = applyUserService.queryAllApplyUser(map,state);
			//取记录总条数
			PageInfo<ApplyUser> pageInfo = new PageInfo<>(queryAllApplyUser);
			
			int total = (int)pageInfo.getTotal();
			//封装数据返回
			Printer.info("数据正常显示");
			
			JsonToTable.jsonPrintObject(response, queryAllApplyUser, total);
		} catch (Exception e) {
			Printer.error("数据错误");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @Title: queryByNameOrTelOrEamil   
	 * @Description: 模糊查询
	 * @param: @param request
	 * @param: @param response
	 * @param: @param state
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年6月13日 下午3:22:34
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/queryByNameOrTelOrEamil.html")
	public @ResponseBody String queryByNameOrTelOrEamil(HttpServletRequest request,HttpServletResponse response,@RequestParam String condition){
	    //获取页面数据
	    try {
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		
		List<ApplyUser> queryByNameOrTelOrEamil = applyUserService.queryByNameOrTelOrEamil(map, condition);
		//取记录总条数
		PageInfo<ApplyUser> pageInfo = new PageInfo<>(queryByNameOrTelOrEamil);
		
		int total = (int)pageInfo.getTotal();
		//封装数据返回
		Printer.info("数据正常显示");
		
		JsonToTable.jsonPrintObject(response, queryByNameOrTelOrEamil, total);
	    } catch (Exception e) {
		Printer.error("数据错误");
		e.printStackTrace();
	    }
	    return null;
	}
}
