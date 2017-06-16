package com.gift.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gift.commons.FinalData;
import com.gift.commons.Printer;
import com.gift.commons.UUIDHexGenerator;
import com.gift.mapper.CustomerMapper;
import com.gift.mapper.LogoMapper;
import com.gift.service.LogoService;
import com.gift.vo.Customer;
import com.gift.vo.Logo;
import com.gift.vo.LogoExample;
import com.gift.vo.LogoExample.Criteria;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class LogoServiceImpl implements LogoService{
    @Autowired
    private LogoMapper logoMapper;
    
    /**
     * 上传logo
     * <p>Title: addLogo</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:26:04
     * @param fi
     * @param request
     * @param logo   
     * @see com.gift.service.LogoService#addLogo(org.springframework.web.multipart.MultipartFile, javax.servlet.http.HttpServletRequest, com.gift.vo.Logo)
     */
    @Override
    public void addLogo(MultipartFile fi, HttpServletRequest request, Logo logo) {

	//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/logo");
	String realPath = "C:/giftupload/logo";
	// 解决window路径的问题
	String mName = fi.getOriginalFilename();
	// 注意拼接文件后缀
	mName = UUIDHexGenerator.get() + mName.substring(mName.lastIndexOf(".")).toLowerCase();
	try {
	    FileUtils.copyInputStreamToFile(fi.getInputStream(), new File(realPath, mName));
	    logo.setId(UUIDHexGenerator.get());
	    logo.setCustomerid(logo.getCustomerid());
	    logo.setCreattime(new Date().getTime());
	    logo.setUpdatetime(new Date().getTime());
	    String logoUrl = "/fileupload/logo" + "/" + mName;
	    logo.setUrl(logoUrl);
	    logo.setState(FinalData.STATE_YES);
	    logo.setStatedescribe(null);
	    logoMapper.insert(logo);
	    Printer.info("上传成功");
	    request.getSession().setAttribute("logo", logoUrl);
	} catch (IOException e) {
	    e.printStackTrace();
	    Printer.error("上传失败");
	}
    }
    
    /**
     * 查询所有logo
     * <p>Title: queryAllLogo</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:26:15
     * @param map
     * @param state
     * @return   
     * @see com.gift.service.LogoService#queryAllLogo(java.util.Map, java.lang.Long)
     */
    @Override
    public List<Logo> queryAllLogo(Map<String, Object> map, Long state) {
	
	LogoExample logoExample = new LogoExample();
	int pageindex=0;
	if(Integer.parseInt(map.get("offset")+"") !=0){
		pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
	}
	pageindex+= 1;
	//分页处理
	PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
	//执行查询
	Criteria createCriteria = logoExample.createCriteria();
	
	List<Logo> selectByExample = logoMapper.selectLogoAndCustomer(logoExample, state);
	
	return selectByExample;
    }
    
    /**
     * 修改图片状态
     * <p>Title: updateLogoState</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:26:27
     * @param id
     * @param stateDescribe
     * @param state
     * @return   
     * @see com.gift.service.LogoService#updateLogoState(java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public String updateLogoState(String id, String stateDescribe,Long state) {
	
	try {
	    Logo selectByPrimaryKey = logoMapper.selectByPrimaryKey(id);
	    
	    selectByPrimaryKey.setState(state);
	    
	    selectByPrimaryKey.setStatedescribe(stateDescribe);
	    
	    int updateByPrimaryKey = logoMapper.updateByPrimaryKey(selectByPrimaryKey);
	    
	    Printer.info("id为:"+id+"的图片状态修改成功");
	    return FinalData.RETURN_YES;
	} catch (Exception e) {
	    e.printStackTrace();
	    Printer.error("id为:"+id+"的图片状态修改失败");
	    return FinalData.RETURN_NO;
	}
    }
    
    /**
     * 根据用户查询logo
     * <p>Title: queryAllLogoByCustomerId</p>   
     * <p>Description: </p>  
     * @author YangNingSheng
     * @date 2017年6月1日 下午2:26:47
     * @param customerId
     * @param state
     * @return   
     * @see com.gift.service.LogoService#queryAllLogoByCustomerId(java.lang.String, java.lang.Long)
     */
    @Override
    public List<Logo> queryAllLogoByCustomerId(String customerId, Long state) {
	
	LogoExample logoExample = new LogoExample();
	
	Criteria createCriteria = logoExample.createCriteria();
	
	createCriteria.andCustomeridEqualTo(customerId);
	
	createCriteria.andStateEqualTo(state);
	
	List<Logo> selectByExample = logoMapper.selectByExample(logoExample);
	
	return selectByExample;
    }

    @Override
    public List<Logo> queryLogoAndCustomerByNameOrTitle(Map<String, Object> map, String condition) throws UnsupportedEncodingException {
	
	int pageindex=0;
	if(Integer.parseInt(map.get("offset")+"") !=0){
		pageindex = Integer.parseInt(map.get("offset")+"")/Integer.parseInt(map.get("limit")+"");
	}
	pageindex+= 1;
	//分页处理
	PageHelper.startPage(pageindex, Integer.parseInt(map.get("limit")+""));
	//执行查询
	condition = URLDecoder.decode(condition,"UTF-8");
	
	List<Logo> selectLogoAndCustomerByNameOrTitle = logoMapper.selectLogoAndCustomerByNameOrTitle("%"+condition+"%", FinalData.STATE_YES);
	
	return selectLogoAndCustomerByNameOrTitle;
    }

}
