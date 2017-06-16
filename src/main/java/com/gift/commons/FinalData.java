package com.gift.commons;
/**
 * @ClassName:  FinalData   
 * @Description:常量数据
 * @author YangNingSheng
 * @date 2017年4月19日 下午5:05:01
 */
public class FinalData {
	public static final Long STATE_YES = 0L;//正常状态/账号未发放状态
	
	public static final Long STATE_NO = 1L;//非正常状态/账号发放状态
	
	public static final String RETURN_YES = "0";//数据正常
	
	public static final String RETURN_NO = "1";//数据异常
	
	public static final String RETURN_CUSTOMER_NO = "3";//不可用(用户状态等,后续补全)
	/**
	 * 用户ajax验证返回true或者false
	 */
	public static final String TURE = "true";//正常
	
	public static final String FALSE = "false";//异常
	
	public static final String SALT = "SALT1357";//用于加密的盐
	
	public static final Long PAGE_NUM = 15L;//每页显示条数为15
	
	public static final String IMAGE_VALIDATA = "RANDOMVALIDATECODEKEY";////验证码session key
	
}
