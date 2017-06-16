package com.gift.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gift.commons.JsonToTable;
import com.gift.commons.Printer;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.service.ApplyUserService;
import com.gift.service.UserService;
import com.gift.vo.User;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

/**   
 * 用户
 * @ClassName:  UserController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月10日 下午2:59:13      
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ApplyUserService applyUserService;
	@Autowired
	private JedisClientSingle jedisClient;
	
	/**
	 * 登陆跳转页面
	 * @Title: login   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月10日 下午3:01:58
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	/**
	 * 登录
	 * @Title: loginCheck   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月10日 下午4:20:54
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping(value="/loginCheck.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loginCheck(User loginUser,HttpServletRequest request,Model model){
		//创建返回对象
		ModelAndView modelAndView = new ModelAndView();
		//获取session
		User sessionUser = (User)request.getSession().getAttribute("user");
		//登录的用户信息匹配得到的用户   *要判断返回值对象*
		User userByCondition = null;
		if(loginUser.getName() != null ){
			userByCondition = userService.getUserByCondition(loginUser);
		}
		if(userByCondition != null || sessionUser != null){
			//将用户塞到session中，后期在加redis
			if(sessionUser == null){
				request.getSession().setAttribute("user", userByCondition);
			}
			//获取 未处理的企业用户数量
			int userCount = applyUserService.getApplyUserCount(0L);
			modelAndView.addObject("customerCount", userCount);
			modelAndView.setViewName("home");
		}else{
			Printer.info(loginUser.getName()+"登陆失败");
			request.setAttribute("msg", "账号密码有误");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	/**
	 * 跳转到页面
	 * @Title: usersInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月11日 下午4:37:52
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/usersInfo")
	public ModelAndView usersInfo(){
		return new ModelAndView("basic/usersInfo");
	}
	/**
	 * @throws Exception 
	 * 获取系统用户信息，渲染页面
	 * @Title: info   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月11日 下午5:26:27
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/info")
	@ResponseBody
	public String info(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取页面数据
		Map<String, Object> map = JsonToTable.getObjectConditions(request, response);
		//查询数据库
		List<User> list = userService.getUsers(map);
		//取记录总条数
        PageInfo<User> pageinfo = new PageInfo<>(list);
		int total = (int)pageinfo.getTotal();
      	//封装数据返回
      	JsonToTable.jsonPrintObject(response, list, total);
		return null;
	}
	/**
	 * 添加用户
	 * @Title: addSysUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param user
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月13日 下午3:34:45
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/addSysUser.html"},method={RequestMethod.POST})
	public String addSysUser(User user){
		//注意去service补全数据
		int addUser = userService.addUser(user);
		if(addUser != 0){
			Printer.info("添加了新的系统用户"+user.getName());
		}else{
			Printer.info("添加系统用户失败");
		}
		return "redirect:/user/usersInfo";
	}
	/**
	 * @throws IOException 
	 * 逻辑删除系统用户
	 * @Title: deleteUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月13日 下午3:36:55
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value={"/deleteUser/{id}"},method={RequestMethod.POST})
	@ResponseBody
	public String deleteUser(@PathVariable String id,HttpServletResponse response) throws IOException{
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		int deleteUser = userService.deleteUser(id);
		if(deleteUser != 0){
			jsonObject.put("msg", "OK");
			msg = jsonObject.toString();
			Printer.info("删除系统用户成功");
		}else{
			jsonObject.put("msg", "NO");
			msg = jsonObject.toString();
			Printer.info("删除系统用户失败");
		}
		return msg;
	}
	/**
	 * 退出登录
	 * 删除session
	 * @Title: loginOut   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月2日 上午10:33:18
	 * @throws
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "redirect:/user/login.html";
	}
	
}
