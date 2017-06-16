package com.gift.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gift.vo.GiftContent;

public interface ContentService {
	
	void addContentFileUpLoad(MultipartFile[] myfilesBanner,HttpServletRequest request) throws Exception;
	
	List<GiftContent> getContents(Map<String, Object> map);
	
	int deleteBanner(String id);
	
	List<GiftContent> getHomeContents();
	
	List<GiftContent> getHomePageContents();
	
	void addHomePageFileUpload(MultipartFile[] myfiles,HttpServletRequest request)  throws IOException;
	
	GiftContent getGiftContentById(String id);
	
	void updateGiftContent(GiftContent content);
	
}
