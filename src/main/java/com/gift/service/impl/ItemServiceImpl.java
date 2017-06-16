package com.gift.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gift.commons.JsonUtil;
import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.mapper.GiftItemDescMapper;
import com.gift.mapper.GiftItemMapper;
import com.gift.service.ItemService;
import com.gift.vo.Customer;
import com.gift.vo.GiftItem;
import com.gift.vo.GiftItemDesc;
import com.gift.vo.GiftItemDescExample;
import com.gift.vo.GiftItemExample;
import com.gift.vo.GiftItemExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private GiftItemMapper itemMapper;
	@Autowired
	private GiftItemDescMapper itemDescMapper;
	@Autowired
	private JedisClientSingle jedisClientSingle;
	
	/**
	 * 添加商品
	 * <p>Title: addItem</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月9日 下午2:12:27
	 * @param item   
	 * @throws IOException 
	 * @see com.gift.service.ItemService#addItem(com.gift.vo.GiftItem)
	 */
	@Override
	public void addItem(MultipartFile[] myfile,GiftItem item,HttpServletRequest request) throws IOException {
		//上传图片
		int index = 0;
		String pic1 = null,pic2= null,pic3= null,pic4 = null;
		for (MultipartFile multipartFile : myfile) {
			//前台已经验证了文件的必须性
			if (!multipartFile.isEmpty()) {
				index ++;
				//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/item");
				String realPath = "C:/giftupload/item";
				String mName = multipartFile.getOriginalFilename();
				mName = UUIDHexGenerator.get()+mName.substring(mName.lastIndexOf(".")).toLowerCase();
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,mName));
				switch (index) {
				case 1:
					pic1 = "/fileupload/item"+"/"+mName;
					break;
				case 2:
					pic2 = "/fileupload/item"+"/"+mName;
					break;
				case 3:
					pic3 = "/fileupload/item"+"/"+mName;
					break;
				case 4:
					pic4 = "/fileupload/item"+"/"+mName;
					break;
				default:
					break;
				}
			}
		}
		
		item.setId(UUIDHexGenerator.get());
		item.setPrice("0");
		item.setBarcode(000000L);//条形码
		item.setItemnum(1000L);  //数量
		item.setState(0L);       //新增  未上架
		item.setCreated(System.currentTimeMillis());
		item.setUpdated(System.currentTimeMillis());
		item.setPic(pic1);
		item.setPic2(pic2);
		item.setPic3(pic3);
		item.setPic4(pic4);
		int insert = itemMapper.insert(item);
		if(insert != 1){
			Printer.info("上传失败");
		}
	}
	/**
	 * 获取集合
	 * <p>Title: getItemList</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月9日 下午5:34:55
	 * @param map
	 * @return   
	 * @see com.gift.service.ItemService#getItemList(java.util.Map)
	 */
	@Override
	public List<GiftItem> getItemList(Map<String, Object> map) {
		GiftItemExample example = new GiftItemExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		List<Long> states = new ArrayList<Long>();
		states.add(0L);
		states.add(1L);
		criteria.andStateIn(states);
		
		List<GiftItem> list = itemMapper.selectByExample(example);
		return list;
	}
	/**
	 * 
	 * <p>Title: getItemListByCondition</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月15日 上午9:39:45
	 * @param map
	 * @return
	 * @throws Exception   
	 * @see com.gift.service.ItemService#getItemListByCondition(java.util.Map)
	 */
	@Override
	public List<GiftItem> getItemListByCondition(Map<String, Object> map) throws Exception {
		GiftItemExample example = new GiftItemExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		//criteria.andStateEqualTo(0L);
		String title = (String) map.get("title");
		String categoryId = (String) map.get("categoryId");
		if(!"0".equals(title)){
			//解决中文乱码
			title = URLDecoder.decode(title,"UTF-8");
			criteria.andTitileLike("%"+title+"%");
		}
		if(!"0".equals(categoryId)){
			criteria.andCategoryIdEqualTo(categoryId);
		}
		List<GiftItem> list = itemMapper.selectByExample(example);
		return list;
	}
	/**
	 * 未加权限的获取
	 * <p>Title: getItemLists</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月15日 上午9:44:56
	 * @return   
	 * @see com.gift.service.ItemService#getItemLists()
	 */
	@Override
	public List<GiftItem> getItemLists(String pageNum, String customerId) {
		GiftItemExample example = new GiftItemExample();
		int pageindex=1;
		if("".equals(pageNum) || pageNum == null){
			pageindex=1;
		}else{
			pageindex = Integer.parseInt(pageNum);
		}
		//分页处理
		PageHelper.startPage(pageindex, 8);
		Criteria criteria = example.createCriteria();
		//拿到上架 状态为1的的商品
		List<GiftItem> list = null;
		if(customerId != null && customerId != ""){
			criteria.andStateEqualTo(1L);
			list = itemMapper.selectByExampleByCustomerId(example, customerId);
		}else{
			criteria.andStateEqualTo(2L);
			list = itemMapper.selectByExample(example);
		}
		return list;
	}
	/**
	 * 根据id获取商品的详细信息
	 * <p>Title: getItemById</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月15日 上午11:21:42
	 * @param id
	 * @return   
	 * @see com.gift.service.ItemService#getItemById(java.lang.String)
	 */
	@Override
	public GiftItem getItemById(String id) {
		
		return itemMapper.selectByPrimaryKey(id);
	}
	/**
	 * 更新商品的状态
	 * <p>Title: modifyItemById</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月15日 下午5:34:42
	 * @param id
	 * @param state   
	 * @see com.gift.service.ItemService#modifyItemById(java.lang.String, java.lang.Long)
	 */
	@Override
	public void modifyItemById(String id, Long state) {
		GiftItem giftItem = itemMapper.selectByPrimaryKey(id);
		giftItem.setState(state);
		giftItem.setUpdated(System.currentTimeMillis());
		itemMapper.updateByPrimaryKey(giftItem);
	}
	/**
	 * 添加商品描述
	 * 因为商品描述（多个，分为多条数据进行存储，但没有进行排序，所以进行批量删除操作，在重新添加）
	 * <p>Title: addItemDesc</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月16日 上午11:27:40
	 * @param itemDescModels   
	 * @see com.gift.service.ItemService#addItemDesc(java.util.List)
	 */
	@Override
	public void addItemDesc(List<GiftItemDesc> itemDescModels) {
		//保证商品的id是一个全局变量
		String itemId = itemDescModels.get(0).getItemId();
		//先批量删除 。需要重写mapper文件
		List<String> list = new ArrayList<String>();
		list.add(itemId);
		itemDescMapper.lotDeleteByItemId(list);
		for (GiftItemDesc giftItemDesc : itemDescModels) {
			/*if(giftItemDesc.getItemId() != null && giftItemDesc.getItemId() != ""){
				itemId = giftItemDesc.getItemId();
			}*/
			if (giftItemDesc.getContent() != null && giftItemDesc.getContent() != "") {
				giftItemDesc.setId(UUIDHexGenerator.get());
				giftItemDesc.setTitile("");
				giftItemDesc.setState(0L);
				giftItemDesc.setCreated(System.currentTimeMillis());
				giftItemDesc.setUpdated(System.currentTimeMillis());
				giftItemDesc.setItemId(itemId);
				itemDescMapper.insert(giftItemDesc);
			}
		}
	}
	/**
	 * 返回描述的集合  然后在controller进行封装
	 * @Title: getItemDescs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param itemId
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年5月16日 下午2:52:22
	 * @return: List<GiftItemDesc>      
	 * @throws
	 */
	@Override
	public List<GiftItemDesc> getItemDescs(String itemId) {
		GiftItemDescExample example = new GiftItemDescExample();
		com.gift.vo.GiftItemDescExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(itemId);
		List<GiftItemDesc> list = itemDescMapper.selectByExample(example);
		return list;
	}
	/**
	 * 更新商品信息
	 * <p>Title: updateItem</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年5月23日 上午10:01:29
	 * @param item   
	 * @see com.gift.service.ItemService#updateItem(com.gift.vo.GiftItem)
	 */
	@Override
	public void updateItem(GiftItem item) {
		itemMapper.updateByPrimaryKeySelective(item);
	}
	/**
	 * 后台
	 * 同步缓存
	 * <p>Title: syncCache</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @throws Exception 
	 * @date 2017年6月7日 下午2:55:04   
	 * @see com.gift.service.ItemService#syncCache()
	 */
	@Override
	public void syncCache(List<Customer> customers) throws IOException {
		for (Customer customer : customers) {
			//删除 customer  key对应的id
			jedisClientSingle.del(customer.getId());
			jedisClientSingle.del(customer.getId()+"1");
			List<GiftItem> list = itemMapper.selectByCustomerId(customer.getId());
			if(list.size() != 0){
				//商品数量大于8需要判断
				if(list.size() > 8){
					int index = 1;
					int i = 1;
					List<GiftItem> pushList = new ArrayList<GiftItem>();
						for (GiftItem giftItem : list) {
							pushList.add(giftItem);
							if(i == 1){
								jedisClientSingle.set("customer:item:"+customer.getId(), "1");
							}else{
								jedisClientSingle.incr("customer:item:"+customer.getId());
							}
							//如果大于8 就redis放一次
							if(i>=(index*8)){
								index++;
								//注意 ：这个时候放redis index要减1
								jedisClientSingle.hset("customer:items:"+customer.getId(), index-1+"",JsonUtil.objectToJson(pushList));
								pushList = new ArrayList<GiftItem>();
							}else if(i == list.size()){
								//将结果放到redis中(注意 这个时候的index已经加过1了)
								jedisClientSingle.hset("customer:items:"+customer.getId(), index+"", JsonUtil.objectToJson(pushList));
							}
							i++;
						}
				}else{
					jedisClientSingle.set("customer:item:"+customer.getId(), "1");
					//如果结果小于8直接将结果放到redis中
					jedisClientSingle.hset("customer:items:"+customer.getId(), 1+"", JsonUtil.objectToJson(list));
				}
			}
		}
	}
	/**
	 * 后台
	 * 根据name获取商品集合  返回前台页面
	 * <p>Title: getItemListByName</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年6月9日 下午4:32:45
	 * @param itemName
	 * @return   
	 * @see com.gift.service.ItemService#getItemListByName(java.lang.String)
	 */
	@Override
	public List<GiftItem> getItemListByName(String itemName,HttpServletRequest request) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		List<GiftItem> list = null;
		GiftItemExample example = new GiftItemExample();
		Criteria criteria = example.createCriteria();
		try {
			if(itemName != null && itemName != ""){
				criteria.andTitileLike("%"+itemName+"%");
				list = itemMapper.selectByExample(example);
			}else{
				//从redis中取
				String firstPage = jedisClientSingle.hget("customer:items:"+customer.getId(), "1");
				if(StringUtil.isNotEmpty(firstPage)){
					list = JsonUtil.jsonToList(firstPage, GiftItem.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

}
