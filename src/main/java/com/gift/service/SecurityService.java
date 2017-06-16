package com.gift.service;

import java.util.List;

import com.gift.commons.TreeJson;

public interface SecurityService {
    /**
     * @Title: getItemCategory   
     * @Description: 获取该用户的图片权限菜单
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月26日 下午3:38:06
     * @return: List<TreeJson>      
     * @throws
     */
    List<TreeJson> getItemCategory(String customerId);
    /**
     * @Title: addItemCategory   
     * @Description: 添加图片权限
     * @param: @param customerId
     * @param: @param categoryId
     * @param: @return  
     * @author YangNingSheng    
     * @date 2017年5月26日 下午3:40:49
     * @return: String      
     * @throws
     */
    String addItemCategory(String customerId,String categoryId);
}
