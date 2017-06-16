package com.gift.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gift.exception.CustomerSessionTimeOutException;
import com.gift.exception.SessionTimeoutException;
import com.gift.vo.Customer;
import com.gift.vo.User;

/**
 * session过期拦截器
 * @author liubin
 */
public class SessionTimeOutInterceptor implements HandlerInterceptor{
	
	 public String[] allowUrls;// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
	 /**
	  * 配置客户的URL请求 
	  */
	 String[] customerUrl = new String[]{"shopPage","customer","applyUser","PersonalCenter","PersonalCenterPage","picture"};
	    
	 public void setAllowUrls(String[] allowUrls) {  
	        this.allowUrls = allowUrls;  
	    }  
	  
	    @Override  
	    public void afterCompletion(HttpServletRequest arg0,  
	            HttpServletResponse arg1, Object arg2, Exception arg3)  
	            throws Exception {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    @Override  
	    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,  
	            Object arg2, ModelAndView arg3) throws Exception {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    @Override  
	    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object arg2) throws Exception {  
	        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
	        if (null != allowUrls && allowUrls.length >= 1)  {
	            for (String url : allowUrls) {  
	                if (requestUrl.contains(url)) {  
	                    return true;  
	                }  
	            }  
	        }
	        User user = (User) request.getSession().getAttribute("user");  
	        Customer customer = (Customer) request.getSession().getAttribute("customer");  
	        if (user != null || customer != null) {  
	            return true; // 返回true，则这个方面调用后会接着调用postHandle(), afterCompletion()  
	        } else if (requestUrl.contains("searchByName")) {
            	return true;
			}else {  
	            // 未登录 跳转到登录页面  
	        	boolean isExist = false;
	        	for (String url : customerUrl) {  
	                if (requestUrl.contains(url)) {
	                	isExist = true;
	                }
	            }
	        	if(isExist){
	        		 throw new CustomerSessionTimeOutException();
	        	}
	        	throw new SessionTimeoutException();// 返回到配置文件中定义的路径  
	        }  
	    }  
	  
	
}
