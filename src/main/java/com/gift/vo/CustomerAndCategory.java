package com.gift.vo;
/**
 * @ClassName:  CustomerAndCategory   
 * @Description:客户和商品分类中间表
 * @author YangNingSheng
 * @date 2017年5月26日 下午4:34:27
 */
public class CustomerAndCategory {
    /*
     * 客户ID
     */
    private String customerId;
    /*
     * 商品分类Id
     */
    private String categoryId;
    
    public CustomerAndCategory() {
	super();
	// TODO Auto-generated constructor stub
    }
    public CustomerAndCategory(String customerId, String categoryId) {
	super();
	this.customerId = customerId;
	this.categoryId = categoryId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
