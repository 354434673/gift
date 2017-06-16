package com.gift.controller;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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

import com.gift.commons.FinalData;
import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.commons.SecurityCode;
import com.gift.commons.SecurityImage;
import com.gift.commons.SecurityCode.SecurityCodeLevel;
import com.gift.service.CustomerService;
import com.gift.vo.ApplyUser;
import com.gift.vo.Customer;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.Constants;
/**
 * @ClassName:  CustomerController   
 * @Description:customer控制层  
 * @author YangNingSheng
 * @date 2017年4月27日 下午3:18:31
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	/**
	 * 后台添加
	 * @Title: addCustomer   
	 * @Description: 添加用户
	 * @param: @param customer
	 * @param: @param id :这个ID是用来获取企业用户id,用于更改状态的
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月3日 下午3:35:57
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/addCustomer.html"},method={RequestMethod.POST})
	public @ResponseBody String addCustomer(Customer customer,String id,
						@RequestParam(value="info",defaultValue="无描述")String info){
		
/*		if(customer.getInfo() == null||customer.getInfo().equals("")){
			
		}*/
	    	customer.setInfo(info);
	    	
		return customerService.addCustomer(id, customer);
		
	}
	/**
	 * 后台
	 * @Title: queryGroupAllApply   
	 * @Description: 显示所有顾客  
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:05:35
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/customerList.html")
	public @ResponseBody String queryAllCustomer(HttpServletRequest request,HttpServletResponse response, Long state){
		//获取页面数据
		try {
			Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
			
			List<Customer> queryAllCustomer = customerService.queryAllCustomer(map,state);
			//取记录总条数
			PageInfo<Customer> pageInfo = new PageInfo<>(queryAllCustomer);
			
			int total = (int)pageInfo.getTotal();
			//封装数据返回
			Printer.info("数据正常显示");
			
			JsonToTable.jsonPrintObject(response, queryAllCustomer, total);
		} catch (Exception e) {
			Printer.error("数据错误");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/queryByNameOrTelOrEamil.html")
	public @ResponseBody String queryByNameOrTelOrEamil(HttpServletRequest request,HttpServletResponse response, @RequestParam String condition){
	    //获取页面数据
	    try {
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		
		List<Customer> queryByNameOrTelOrEamil = customerService.queryByNameOrTelOrEamil(map, condition);
		//取记录总条数
		PageInfo<Customer> pageInfo = new PageInfo<>(queryByNameOrTelOrEamil);
		
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
	/**
	 * @Title: image   
	 * @Description: 生成验证码
	 * @param: @param response
	 * @param: @param session
	 * @param: @return
	 * @param: @throws Exception  
	 * @author YangNingSheng    
	 * @date 2017年4月25日 下午3:33:56
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/image.pag")
	public String image(HttpServletResponse response,HttpServletRequest request) throws Exception {
		 // 调用工具类的方法，生成验证码字符
		String code = SecurityCode.getSecurityCode(4, SecurityCodeLevel.Medium, false);
				
		HttpSession session = request.getSession();
		
		session.setAttribute("code", code);
		// 根据验证码字符，生成图片
		BufferedImage bi = SecurityImage.createImage(code);
		// 把图片 通过response的输出流输出到client
		ImageIO.write(bi, "JPEG",response.getOutputStream());
				
		return null;
	}
	/**
	 * @Title: userLogin   
	 * @Description: 用户登录
	 * @param: @param username
	 * @param: @param pwd
	 * @param: @param valicode
	 * @param: @param request
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:07:20
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/userLogin.html"},method={RequestMethod.POST})
	public @ResponseBody String userLogin(String username, String pwd, String valicode,HttpServletRequest request){
	    
		/*String code = (String) request.getSession().getAttribute("code");   
		if(code.equalsIgnoreCase(valicode)){
		    try {
			// 身份验证
			UsernamePasswordToken customer = new UsernamePasswordToken(username, pwd);
			// 得到当前Subject，即"用户"
			SecurityUtils.getSubject().login(customer);
			Printer.info("用户:"+username+"登陆成功");
			
			return FinalData.RETURN_YES;
		    } catch (UnknownAccountException e) {
			
			Printer.error("用户未认证");
			return FinalData.RETURN_NO;
		    } catch (IncorrectCredentialsException e) {
			
			Printer.error("密码错误");
			return FinalData.RETURN_NO;
		    }
		}else{
			Printer.error("验证码错误");
			return "2";
		}*/
		String code = (String) request.getSession().getAttribute("code");   
		if(code.equalsIgnoreCase(valicode)){
			return customerService.userLogin(username, pwd, request);
		}else{
			Printer.error("验证码错误");
			return "2";
		}
	}
	/**
	 * @Title: deleCustomer   
	 * @Description: 用户删除(逻辑删除) 
	 * @param: @param id
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:07:30
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/updateCustomerState.html"},method={RequestMethod.POST})
	public @ResponseBody String deleCustomer(String id, Long state){
		
	    return customerService.updateCustomerState(id,state);
	}
	/**
	 * @Title: userLogon   
	 * @Description: 登出,清除session
	 * @param: @param request
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月4日 下午2:07:44
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/userLogout.html")
	public String userLogon(HttpServletRequest request){
	    Printer.info("注销成功");
	    	
	    request.getSession().invalidate();
		
	    return "redirect:/shopPage/basic.html";
	}
	/**
	 * @Title: resetPwd   
	 * @Description: 修改密码  
	 * @param: @param email
	 * @param: @param phone
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月9日 上午10:33:48
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/resetPwd.html"},method={RequestMethod.POST})
	public @ResponseBody String resetPwd(String customerId, String email, Long phone, String userpwd){
		
		return customerService.resetPwd(customerId, email, phone, userpwd);
	}
	/**
	 * @Title: resetPwdByCustomerId   
	 * @Description: 用户中心里修改密码
	 * @param: @param id
	 * @param: @param userpwd
	 * @param: @return  
	 * @author YangNingSheng    
	 * @date 2017年5月23日 下午4:00:47
	 * @return: String      
	 * @throws
	 */
/*	@RequestMapping("/resetPwdById.html")
	public @ResponseBody String resetPwdByCustomerId(String customerId, String userpwd,HttpServletRequest request){
	    
	    String resetPwdByCustomerId = customerService.resetPwdByCustomerId(customerId, userpwd);
	    
	    //修改完后强制用户重新登陆,清空session
	    request.getSession().removeAttribute("customer");;
	    
	    return resetPwdByCustomerId;
	}*/
	
}
