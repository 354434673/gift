package com.gift.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.GiftContentMapper;
import com.gift.service.ContentService;
import com.gift.vo.GiftContent;
import com.gift.vo.GiftContentExample;
import com.gift.vo.GiftContentExample.Criteria;
import com.github.pagehelper.PageHelper;
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private GiftContentMapper contentMapper;
	
	/**
	 * 上传图片，得到上传的路径，并且要注意图片的数量
	 * <p>Title: addContentFileUpLoad</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月24日 下午2:03:43
	 * @param myfilesBanner   
	 * @see com.gift.service.ContentService#addContentFileUpLoad(org.springframework.web.multipart.MultipartFile[])
	 */
	@Override
	public void addContentFileUpLoad(MultipartFile[] myfilesBanner,HttpServletRequest request) throws Exception {
		//遍历循环上传  插入  
		for (MultipartFile multipartFile : myfilesBanner) {
			if(!multipartFile.isEmpty()){
				//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				String realPath = "c:/giftupload/banner";
				
				//解决window路径的问题
				String mName = multipartFile.getOriginalFilename();
				//注意拼接文件后缀
				mName = UUIDHexGenerator.get()+mName.substring(mName.lastIndexOf(".")).toLowerCase();
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,mName));
				//封装对象
				GiftContent content = new GiftContent();
				content.setId(UUIDHexGenerator.get());
				content.setCategoryid(1L);
				content.setTitle("banner");
				content.setTitledesc("banner");
				content.setUrl("");
				content.setPic("/fileupload/banner"+"/"+mName);
				content.setPic2("");
				content.setContent("");
				content.setUpdated(new Date().getTime());
				content.setCreated(new Date().getTime());
				content.setState(0L);
				//执行插入动作
				int insert = contentMapper.insert(content);
				if(insert == 0){
					Printer.error(mName+"上传失败");
				}
			}
		}
	}
	/**
	 * 获取内容列表
	 * <p>Title: getContents</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月25日 下午5:15:58
	 * @param map
	 * @return   
	 * @see com.gift.service.ContentService#getContents(java.util.Map)
	 */
	@Override
	public List<GiftContent> getContents(Map<String, Object> map) {
		GiftContentExample example = new GiftContentExample();
		int pageindex=0;
		if(Integer.parseInt(map.get("offset")+"") !=0){
			pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
		}
		pageindex+= 1;
		//分页处理
		PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0L);
		example.setOrderByClause("pic2 asc");
		List<GiftContent> list = contentMapper.selectByExample(example);
		return list;
	}
	/**
	 * 逻辑删除banner ,改banner的状态
	 * <p>Title: deleteBanner</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月26日 上午10:31:59
	 * @param id
	 * @return   
	 * @see com.gift.service.ContentService#deleteBanner(java.lang.String)
	 */
	@Override
	public int deleteBanner(String id) {
		GiftContent giftContent = contentMapper.selectByPrimaryKey(id);
		giftContent.setState(1L);
		int primaryKey = contentMapper.updateByPrimaryKey(giftContent);
		return primaryKey;
	}
	/**
	 * 
	 * <p>Title: getHomeContents</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月26日 上午10:53:41
	 * @return   
	 * @see com.gift.service.ContentService#getHomeContents()
	 */
	@Override
	public List<GiftContent> getHomeContents() {
		GiftContentExample example = new GiftContentExample();
		Criteria criteria = example.createCriteria();
		//状态为0
		criteria.andStateEqualTo(0L);
		//首页的banner categoryid 写死
		criteria.andCategoryidEqualTo(1L);
		List<GiftContent> selectByExample = contentMapper.selectByExample(example);
		return selectByExample;
	}
	/**
	 * 上传图片 注意顺序
	 * <p>Title: addHomePageFileUpload</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月26日 下午2:59:20
	 * @param myfiles
	 * @param request   
	 * @throws IOException 
	 * @see com.gift.service.ContentService#addHomePageFileUpload(org.springframework.web.multipart.MultipartFile[], javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void addHomePageFileUpload(MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
		//遍历循环上传  插入
		int index = 0;
		for (MultipartFile multipartFile : myfiles) {
			index++;
			if(!multipartFile.isEmpty()){
				//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/shop");
				String realPath = "c:/giftupload/homepage";
				//解决window路径的问题
				String mName = multipartFile.getOriginalFilename();
				//注意拼接文件后缀
				mName = UUIDHexGenerator.get()+mName.substring(mName.lastIndexOf(".")).toLowerCase();
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,mName));
				//封装对象
				GiftContent content = new GiftContent();
				content.setId(UUIDHexGenerator.get());
				content.setCategoryid(2L);
				content.setTitle("首页大图");
				content.setTitledesc("首页大图");
				content.setUrl("");
				content.setPic("/fileupload/homepage"+"/"+mName);
				content.setPic2(index+"");
				content.setContent("");
				content.setUpdated(new Date().getTime());
				content.setCreated(new Date().getTime());
				content.setState(0L);
				//执行插入动作(*在插入首页大图时候要将之前的大图替换掉。*)
				this.updateHomeContent(index);
				int insert = contentMapper.insert(content);
				if(insert == 0){
					Printer.error(mName+"上传失败");
				}
			}
		}
	}
	/**
	 * 查询首页大图，注意查询顺序
	 * <p>Title: getHomePageContents</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月26日 下午3:57:33
	 * @return   
	 * @see com.gift.service.ContentService#getHomePageContents()
	 */
	@Override
	public List<GiftContent> getHomePageContents() {
		GiftContentExample example = new GiftContentExample();
		Criteria criteria = example.createCriteria();
		//状态为0
		criteria.andStateEqualTo(0L);
		//首页的banner categoryid 写死
		criteria.andCategoryidEqualTo(2L);
		example.setOrderByClause("pic2 asc");
		List<GiftContent> selectByExample = contentMapper.selectByExample(example);
		return selectByExample;
	}
	/**
	 * 上传大图时，将原大图的状态对应的下标  更改状态
	 * @Title: updateHomeContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param index  
	 * @author kevin    
	 * @date 2017年4月27日 上午11:06:23
	 * @return: void      
	 * @throws
	 */
	public void updateHomeContent(int index){
		GiftContentExample example = new GiftContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andPic2EqualTo(index+"");
		List<GiftContent> list = contentMapper.selectByExample(example);
		GiftContent giftContent = list.get(0);
		giftContent.setState(1L);
		contentMapper.updateByPrimaryKey(giftContent);
	}
	/**
	 * 获取单个对象
	 * <p>Title: getGiftContentById</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月27日 下午4:31:28
	 * @param id
	 * @return   
	 * @see com.gift.service.ContentService#getGiftContentById(java.lang.String)
	 */
	@Override
	public GiftContent getGiftContentById(String id) {
		
		return contentMapper.selectByPrimaryKey(id);
	}
	/**
	 * 更新content内容
	 * 注意中文乱码
	 * <p>Title: updateGiftContent</p>   
	 * <p>Description: </p>  
	 * @author kevin
	 * @date 2017年4月28日 下午3:34:02
	 * @param content   
	 * @see com.gift.service.ContentService#updateGiftContent(com.gift.vo.GiftContent)
	 */
	@Override
	public void updateGiftContent(GiftContent content) {
		GiftContent giftContent = contentMapper.selectByPrimaryKey(content.getId());
		giftContent.setTitle(content.getTitle());
		giftContent.setTitledesc(content.getTitledesc());
		giftContent.setUrl(content.getUrl());
		giftContent.setUpdated(System.currentTimeMillis());
		contentMapper.updateByPrimaryKey(giftContent);
	}

}
