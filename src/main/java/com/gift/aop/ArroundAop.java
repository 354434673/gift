package com.gift.aop;

import java.util.Arrays;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.gift.commons.JSONUtils;
import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.vo.Customer;
import com.gift.vo.CustomerLogoFix;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Component//组件
@Aspect//申明切面
public class ArroundAop {
	@Autowired
	private JedisClientSingle jedisClientSingle;
    	/*
    	 * logo拼接环绕通知
    	 * execution参数:(返回值(*为任意) 包.类.方法.(参数)任意)
    	 */
	@Around("execution(* com.gift.service.impl.CustomerLogoFixServiceImpl.getLogoFixsByCustomerId(..))")
	public Object LogoFixAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
/*    	    	System.out.println("当前执行的目标类:" +proceedingJoinPoint.getTarget());
    	    	System.out.println("当前目标方法的签名:" +proceedingJoinPoint.getSignature().getName());
    	    	System.out.println("当前执行的目标的参数:");*/
/*		System.out.println("环绕通知类---------------------------------");
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object object : args) {
			System.out.println(object);
		}*/
		Object proceed = null;
		
		Object[] args = proceedingJoinPoint.getArgs();//当前执行的目标参数
		
		CustomerLogoFix customerLogoFix = new CustomerLogoFix();
		try {
			String json = jedisClientSingle.hget(args[0]+"logofix",args[2]+"");
			//使用spring框架提供工具类 isEmpty 字符串为空 |字符串为空串返回 true
			if(StringUtils.isEmpty(json)){
			    	Printer.info(args[2]+"当前页无缓存,放入缓存");
			    	
			    	proceed = proceedingJoinPoint.proceed();//执行切入的方法
				//放入redis缓存中
			    	jedisClientSingle.hset(args[0]+"logofix", args[2]+"", JsonUtil.objectToJson(proceed));
			}else{
			    Printer.info(args[2]+"当前页缓存查询成功");
			    //如果存在,获得缓存中的数据并转为原对象
			    proceed = JSONUtils.toList(json, new CustomerLogoFix().getClass());
			}
		} catch (JedisConnectionException e) {
		    	Printer.error("redis宕机");
		    	
			proceed = proceedingJoinPoint.proceed();
		}
		return proceed;
	}
	/*
	 * 更改后清楚缓存
	 */
	@Around("execution(* com.gift.service.impl.CustomerLogoFixServiceImpl.addCustomerLogoFix(..))")
	public void updateLogoFixAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
	    
	    try {
		Printer.info("用户更新,清除缓存");
		//清楚当前用户的缓存
		Customer customer = (Customer) proceedingJoinPoint.getArgs()[1];//强转
		
		jedisClientSingle.del(customer.getId()+"logofix");
		//执行切入的方法
		proceedingJoinPoint.proceed();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
}
