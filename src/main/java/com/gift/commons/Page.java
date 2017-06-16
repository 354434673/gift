package com.gift.commons;

public class Page {
    private Long title;//总条数
    private Long page;//当前页数
    /*
     * 获得总页数
     */
    public Long getPageNum(Long title){
	return title/FinalData.PAGE_NUM;
    }
    /*
     * 开始条数
     */
    public Long getbeginNum(Long page){
	return (page-1)*FinalData.PAGE_NUM;
    }
    
}	
