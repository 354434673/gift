package com.gift.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;


import net.sf.json.JSONObject;

/**
 * 封装bootstrapTable 需要的固定格式json数据
 * @ClassName:  JsonToTable   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author kevin
 * @date 2017年4月11日 下午5:37:03
 */
public class JsonToTable {
	/**
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * 获取流中的数据
	 * @Title: getObjectConditions   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return  
	 * @author kevin    
	 * @date 2017年4月11日 下午5:41:58
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public static Map<String, Object> getObjectConditions(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//创建返回值对象
		Map<String, Object> map = new HashMap<String, Object>();
		//读取流数据  固定写法
		BufferedReader bufr = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		StringBuilder sBuilder = new StringBuilder("");
        String temp = "";
        while((temp = bufr.readLine()) != null){
            sBuilder.append(temp);
         }
        bufr.close();
        String json = sBuilder.toString();
		JSONObject object = JSONObject.fromObject(json);
		//参数固定
        String limit= object.getString("limit");//通过此方法获取前端数据
        String offset= object.getString("offset");
        map.put("limit", limit);
        map.put("offset", offset);
		return map;
	}
	
	/**
	 * 输出
	 * @Title: jsonPrintObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param response
	 * @param: @param list
	 * @param: @param total  
	 * @author kevin    
	 * @date 2017年4月11日 下午5:41:13
	 * @return: void      
	 * @throws
	 */
	public static void  jsonPrintObject(HttpServletResponse response,List<?> list,int total){
		try {
			String score = null;
    		JSONObject jsonObject=null;
    		String scorejson = null;
    		if(list!=null && total!=0){
    			for(int i = 0;i<list.size();i++){
    				response.setContentType("text/html;charset=UTF-8"); 
					ObjectMapper objectMapper = new ObjectMapper();
					scorejson = objectMapper.writeValueAsString(list); 
					jsonObject = new JSONObject();
					jsonObject.put("rows", scorejson);//需要的数据  定义死的
					jsonObject.put("total", total);
					score = jsonObject.toString();
    			}
    		}else{
        		jsonObject = new JSONObject();
        		jsonObject.put("rows", "[]");
    			jsonObject.put("total", total);
    			score = jsonObject.toString();
        	}
    		response.getWriter().write(score);
    		response.getWriter().flush();
    		response.getWriter().close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
