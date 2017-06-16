package com.gift.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gift.vo.Customer;
import com.gift.vo.GiftItem;
import com.gift.vo.GiftItemDesc;

public interface ItemService {
	
	void addItem(MultipartFile[] myfile,GiftItem item,HttpServletRequest request)throws IOException ;
	
	List<GiftItem> getItemList(Map<String, Object> map);
	
	List<GiftItem> getItemListByCondition(Map<String, Object> map)throws Exception;
	/**
	 * 权限功能待完善
	 * @Title: getItemLists   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月15日 上午9:43:49
	 * @return: List<GiftItem>      
	 * @throws
	 */
	List<GiftItem> getItemLists(String pageNum, String customerId);
	
	GiftItem getItemById(String id);
	
	void modifyItemById(String id,Long state);
	
	void addItemDesc(List<GiftItemDesc> itemDescs);
	
	List<GiftItemDesc> getItemDescs(String itemId);
	
	void updateItem(GiftItem item);
	
	void syncCache(List<Customer> customers) throws IOException ;
	
	List<GiftItem> getItemListByName(String itemName,HttpServletRequest request);
}
