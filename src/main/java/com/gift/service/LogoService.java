package com.gift.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gift.vo.Customer;
import com.gift.vo.Logo;


/**
 * @ClassName:  LogoService   
 * @Description:LOGO业务层
 * @author YangNingSheng
 * @date 2017年5月18日 下午2:40:49
 */
public interface LogoService {
    /**
     * @Title: addLogo   
     * @Description: 上传LOGO
     * @param: @param fi
     * @param: @param request
     * @param: @param logo
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月18日 下午2:41:55
     * @throws
     */
    void addLogo(MultipartFile fi,HttpServletRequest request,Logo logo);
    /**
     * @Description: 后台显示所有LOGO
     * @param: @param map
     * @param: @param state
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月22日 下午2:07:38
     * @return: List<Logo>      
     * @throws
     */
    List<Logo> queryAllLogo(Map<String, Object> map, Long state);
    /**
     * @Title: queryAllLogoByCustomerId   
     * @Description: 前台显示当前登陆用户LOGO 
     * @param: @param customerId
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月23日 上午10:43:15
     * @return: List<Logo>      
     * @throws
     */
    List<Logo> queryAllLogoByCustomerId(String customerId, Long state);
    /**
     * @throws UnsupportedEncodingException 
     * 后台
     * @Title: queryLogoAndCustomerByNameOrTitle   
     * @Description: 模糊查询
     * @param: @param condition
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年6月13日 下午4:55:21
     * @return: List<Logo>      
     * @throws
     */
    List<Logo> queryLogoAndCustomerByNameOrTitle(Map<String, Object> map, String condition) throws UnsupportedEncodingException;
    /**
     * @Title: updateLogoState   
     * @Description: 更改状态  
     * @param: @param id
     * @param: @param state
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月22日 下午4:10:12
     * @return: String      
     * @throws
     */
    String updateLogoState(String id, String stateDescribe, Long state);
}
